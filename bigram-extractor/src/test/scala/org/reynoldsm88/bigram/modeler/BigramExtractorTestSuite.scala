package org.reynoldsm88.bigram.modeler

import com.holdenkarau.spark.testing.{RDDComparisons, SharedSparkContext}
import org.apache.spark.SparkContext
import org.reynoldsm88.bigram.modeler.config.{JobConfig, RulesDescriptor, SourceDescriptor}
import org.reynoldsm88.bigram.modeler.model.BiGram
import org.scalatest.FlatSpec

class BigramExtractorTestSuite extends FlatSpec with SharedSparkContext with RDDComparisons {

    private val testDataRootDir = System.getProperty( "user.dir" ) + "/bigram-extractor/src/test/resources/data"

    "Bigram Extractor" should "filter, parse, and add sentence boundaries an example Google Hangouts file" in {
        val extractor : BigramExtractor = new SparkDroolsBigramExtractor( sc ) with ClasspathRulesProvider
        val expected = Set( BiGram( "a test", "</s>", 11L ), BiGram( "this is", "a", 11L ), BiGram( "is a", "test", 11L ), BiGram( "<s> this", "is", 11L ) )

        val jobConfig : JobConfig = {
            val metadata = Map( "username" -> "Michael Reynolds" )
            val rules = RulesDescriptor( "org.reynoldsm88", "bigram-rules", "0.0.1-SNAPSHOT" )
            val sources = List( SourceDescriptor( testDataRootDir + "/input/hangouts-sample-input.txt", "hangouts", metadata ) )
            JobConfig( "hangouts-bigram-extractor", rules, sources )
        }

        val actual : Set[ BiGram ] = extractor.extractBigrams( jobConfig )
        assert( expected == actual )
    }
}