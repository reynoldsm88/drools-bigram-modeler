package org.reynoldsm88.bigram.modeler.config

import com.owlike.genson.defaultGenson._
import org.scalatest.{FlatSpec, Ignore}

import scala.io.Source

@Ignore // TODO - this can probably be nixed since we don't need Genson anymore thanks 2 Scalatra
class JobConfigTestSuite extends FlatSpec {

    "Job Config parsing" should "produce the equivalent job config from JSON file" in {
        val expectedConfig : JobConfig = {
            val metadata = Map( "username" -> "Michael Reynolds" )
            val rules = RulesDescriptor( "org.reynoldsm88", "bigram-rules", "0.0.1-SNAPSHOT" )
            val sources = List( SourceDescriptor( "hdfs://localhost:9000/etc/data/hangouts/chris-hangouts.txt", "hangouts", Map( "username" -> "Michael Reynolds" ) ) )
            JobConfig( "hangouts-bigram-extractor", rules, sources )
        }

        val jobConfigJson = Source.fromFile( System.getProperty( "user.dir" ) + "/bigram-extractor/src/test/resources/config/test-job-config.json" ).mkString
        val actualConfig = fromJson[ JobConfig ]( jobConfigJson )

        assert( expectedConfig == actualConfig )
    }

}
