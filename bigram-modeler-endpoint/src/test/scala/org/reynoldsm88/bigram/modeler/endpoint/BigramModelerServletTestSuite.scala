package org.reynoldsm88.bigram.modeler.endpoint

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.reynoldsm88.bigram.modeler.mock.MockBigramExtactor
import org.scalatest.{FlatSpecLike, Ignore}
import org.scalatra.test.scalatest.ScalatraSuite

//TODO - @michael -deal with this later
@Ignore
class BigramModelerServletTestSuite extends ScalatraSuite with FlatSpecLike {

    protected implicit lazy val jsonFormats : Formats = DefaultFormats.withBigDecimal

    addServlet( new BigramModelerServlet( new DummyBigramExtactor() ), "/*" )

    "Get request" should "return http ok for now" in {
        //@formatter:off
        get( "/" ) {
            status should equal( 200 )
            val response = parse( body ).extract[ JobStarted ]
            response.jobNumber should equal( 0 )
        }
       //@formatter:on
    }
}
