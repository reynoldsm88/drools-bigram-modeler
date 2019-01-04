package org.reynoldsm88.bigram.modeler.endpoint

import org.scalatest.FlatSpecLike
import org.scalatra.test.scalatest.ScalatraSuite

class BigramModelerServletTestSuite extends ScalatraSuite with FlatSpecLike {

    addServlet( classOf[ BigramModelerServlet ], "/*" )

    "Get request" should "return http ok for now" in {
        //@formatter:off
        get( "/" ) {
            status should equal( 200 )
            body should equal( "michael" )
        }
        //@formatter:on
    }
}
