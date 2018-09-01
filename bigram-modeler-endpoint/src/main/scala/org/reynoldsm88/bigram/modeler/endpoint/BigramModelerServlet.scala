package org.reynoldsm88.bigram.modeler.endpoint

import org.scalatra.{Ok, ScalatraServlet}

class BigramModelerServlet extends ScalatraServlet {
    get( "/" ) {
                   Ok( "michael" )
               }
}