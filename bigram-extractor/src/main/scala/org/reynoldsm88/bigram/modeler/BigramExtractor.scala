package org.reynoldsm88.bigram.modeler

import java.util.UUID

import org.apache.spark.SparkContext
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.kie.api.{KieBase, KieServices}
import org.reynoldsm88.bigram.modeler.config.JobConfig
import org.reynoldsm88.bigram.modeler.model.{BiGram, Sentence}
import org.reynoldsm88.bigram.modeler.text.sources.Hangouts

class BigramExtractor( val jobConfig : JobConfig, val spark : SparkContext ) {

    def rules( group : String, artifact : String, version : String ) : KieBase = {
        //TODO - would like to get a way to load via KieScanner
        KieServices.Factory.get.newKieClasspathContainer().getKieBase
    }

    def loadSources( config : JobConfig ) : RDD[ String ] = {
        var lines : RDD[ String ] = spark.emptyRDD[ String ]
        jobConfig.sources.map( sourceDescr => {
            if ( sourceDescr.resourceType == "hangouts" ) {
                println( "Using hangouts source : " + sourceDescr.uri )
                val hangoutsSrc = new Hangouts( Some( sourceDescr.metadata( "username" ) ) )
                lines = spark.union( lines, hangoutsSrc.lines( spark.textFile( sourceDescr.uri ) ) )
            }
        } )

        lines
    }

    @throws( classOf[ Exception ] )
    def run( ) : Unit = {
        // kbase is the only Kie API object that can be serialized as a whole, this prevents us from being able to specify the ksession though
        val kbase : Broadcast[ KieBase ] = spark.broadcast( rules( jobConfig.rules.group, jobConfig.rules.artifact, jobConfig.rules.version ) )
        val data : RDD[ String ] = loadSources( jobConfig )

        //@formatter:off
        val bigrams : RDD[ BiGram ] = spark.parallelize( data.map( s => Sentence( UUID.randomUUID.toString, s ) )
                                                             .map( sentence => RulesEvaluator.execute( kbase.value, "bigram-ksession", sentence, classOf[ BiGram ] ) )
                                                             .fold( List[ BiGram ]() )( ( list, bigram ) => list ++ bigram ) )
                                                             .asInstanceOf[ RDD[ BiGram ] ]

        //        // spark API deals much more naturally with tuples for various functions, can convert back to strongly typed model afterword
        val consolidatedBiGrams : RDD[ BiGram ] = bigrams.map( bigram => ((bigram.root, bigram.stem), bigram.count) )
                                                         .groupByKey()
                                                         .map( values => BiGram( values._1._1, values._1._2, values._2.sum ) ) // there has to be a better way to do this

        //@formatter:on
        consolidatedBiGrams.collect().foreach( println )
    }
}