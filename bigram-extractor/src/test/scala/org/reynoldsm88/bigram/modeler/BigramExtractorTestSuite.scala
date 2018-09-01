package org.reynoldsm88.bigram.modeler

import com.holdenkarau.spark.testing.{RDDComparisons, SharedSparkContext}
import org.reynoldsm88.bigram.modeler.config.{JobConfig, RulesDescriptor, SourceDescriptor}
import org.reynoldsm88.bigram.modeler.model.{BiGram, LangModel}
import org.scalatest.FlatSpec

class BigramExtractorTestSuite extends FlatSpec with SharedSparkContext with RDDComparisons {

    private val testDataRootDir = System.getProperty( "user.dir" ) + "/bigram-extractor/src/test/resources/data"

    "Bigram Extractor" should "filter, parse, and add sentence boundaries an example Google Hangouts file" in {

        val expectedLangModel : LangModel = {
            val bigrams : Set[BiGram] = {

            }
        }

        val jobConfig : JobConfig = {
            val metadata = Map( "username" -> "Michael Reynolds" )
            val rules = RulesDescriptor( "org.reynoldsm88", "bigram-rules", "0.0.1-SNAPSHOT" )
            val sources = List( SourceDescriptor( testDataRootDir + "/input/hangouts-sample-input.txt", "hangouts", metadata ) )
            JobConfig( "hangouts-bigram-extractor", rules, sources )
        }

        val extractor : SparkDroolsBigramExtractor = new SparkDroolsBigramExtractor( jobConfig, sc )
        val actual = extractor.loadSources( jobConfig )
        val expected = sc.textFile( testDataRootDir + "/output/hangouts-sample-expected.txt" )
        assertRDDEquals( expected, actual )
    }
}