package org.reynoldsm88.bigram.modeler.endpoint

import org.apache.spark.SparkContext
import org.reynoldsm88.bigram.modeler.BigramExtractor
import org.scalatest.FlatSpecLike
import org.scalatra.test.scalatest.ScalatraSuite

class BigramModelerServletTestSuite extends ScalatraSuite with FlatSpecLike {

    addServlet( classOf[ BigramModelerServlet ], "/*" )

    "Get request" should "return http ok for now" in {

        val sc : SparkContext =

//        val testBigramExtractor : BigramExtractor

        //@formatter:off
        get( "/" ) {
            status should equal( 200 )
            body should equal( "michael" )
        }
        //@formatter:on
    }
}
