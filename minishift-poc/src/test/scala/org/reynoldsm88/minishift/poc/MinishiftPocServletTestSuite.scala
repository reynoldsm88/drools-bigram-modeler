package org.reynoldsm88.minishift.poc

import org.scalatest.FlatSpecLike
import org.scalatra.test.scalatest.ScalatraSuite

class MinishiftPocServletTestSuite extends ScalatraSuite with FlatSpecLike {

    addServlet( classOf[ MinishiftPocServlet ], "/*" )

    "Get request" should "return http ok for now" in {
        for ( i <- 0 to 5 ) println( "++++++++++++++++++++++++++++++++++++++" )
        get( "/" ) {
                       status should equal( 200 )
                   }
    }
}
