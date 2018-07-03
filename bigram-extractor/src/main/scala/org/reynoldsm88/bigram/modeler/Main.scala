package org.reynoldsm88.bigram.modeler

import com.owlike.genson.defaultGenson._
import org.apache.spark.{SparkConf, SparkContext}
import org.reynoldsm88.bigram.modeler.config.JobConfig

import scala.io.Source

object Main {

    def main( args : Array[ String ] ) : Unit = {
        val conf = new SparkConf().setAppName( "bigram-extractor" )
        val spark = new SparkContext( conf )

        val jobConfig = fromJson[ JobConfig ]( Source.fromFile( args( 0 ) ).mkString )
        println( jobConfig )

        new BigramExtractor( jobConfig, spark ).run()

        spark.stop()
    }

}