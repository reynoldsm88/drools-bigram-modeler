package org.reynoldsm88.bigram.modeler

import com.holdenkarau.spark.testing.{RDDComparisons, SharedSparkContext}
import org.reynoldsm88.bigram.modeler.config.{JobConfig, RulesDescriptor, SourceDescriptor}
import org.reynoldsm88.bigram.modeler.model.{BiGram, LangModel}
import org.scalatest.FlatSpec

class BigramExtractorTestSuite extends FlatSpec with SharedSparkContext with RDDComparisons {

    private val testDataRootDir = System.getProperty( "user.dir" ) + "/bigram-extractor/src/test/resources/data"

    "Bigram Extractor" should "filter, parse, and add sentence boundaries an example Google Hangouts file" in {

        val expectedLangModel : LangModel = {
            val bigrams : Set[ BiGram ] = Set( BiGram( "a test", "</s>", 11L ), BiGram( "this is", "a", 11L ), BiGram( "is a", "test", 11L ), BiGram( "<s> this", "is", 11L ) )
            LangModel( 1, bigrams )
        }

        val jobConfig : JobConfig = {
            val metadata = Map( "username" -> "Michael Reynolds" )
            val rules = RulesDescriptor( "org.reynoldsm88", "bigram-rules", "0.0.1-SNAPSHOT" )
            val sources = List( SourceDescriptor( testDataRootDir + "/input/hangouts-sample-input.txt", "hangouts", metadata ) )
            JobConfig( "hangouts-bigram-extractor", rules, sources )
        }

        val extractor : SparkDroolsBigramExtractor = new SparkDroolsBigramExtractor( jobConfig, sc ) with ClasspathRulesProvider
        val langModel : LangModel = extractor.buildLangModel()
        assert( expectedLangModel == langModel )
    }
}