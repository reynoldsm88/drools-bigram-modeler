package org.reynoldsm88.bigram.modeler.endpoint

import org.apache.spark.SparkContext
import org.mockito.ArgumentMatchersSugar
import org.reynoldsm88.bigram.modeler.SparkDroolsBigramExtractor
import org.scalatest.FlatSpecLike
import org.scalatra.test.scalatest.ScalatraSuite
import org.mockito.ArgumentMatchersSugar._
import org.scalatest.mockito.MockitoSugar

class BigramModelerServletTestSuite extends ScalatraSuite with ArgumentMatchersSugar with MockitoSugar with FlatSpecLike {

    addServlet( classOf[ BigramModelerServlet ], "/*"  )

    "Get request" should "return http ok for now" in {

        //        val sc : SparkContext = {
        //
        //        }

        //        val testBigramExtractor : BigramExtractor

        //@formatter:off
        get( "/" ) {
            status should equal( 200 )
            body should equal( "michael" )
        }
        //@formatter:on
    }
}
