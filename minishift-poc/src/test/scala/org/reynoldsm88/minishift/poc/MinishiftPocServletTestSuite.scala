package org.reynoldsm88.minishift.poc

import org.scalatest.FlatSpecLike
import org.scalatra.test.scalatest.ScalatraSuite

class MinishiftPocServletTestSuite extends ScalatraSuite with FlatSpecLike {

    addServlet( classOf[ MinishiftPocServlet ], "/*" )

    "Get request" should "return http ok for now" in {
        //@formatter:off
        get( "/" ) {
            status should equal( 200 )
            body should equal( "michael" )
        }
        //@formatter:on
    }
}
