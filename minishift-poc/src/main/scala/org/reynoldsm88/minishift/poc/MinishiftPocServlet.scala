package org.reynoldsm88.minishift.poc

import org.scalatra.{Ok, ScalatraServlet}

class MinishiftPocServlet extends ScalatraServlet {
    get( "/" ) {
                   Ok( "" )
               }
}
