package org.reynoldsm88.bigram.modeler.endpoint

import javax.servlet.ServletContext
import org.scalatra.LifeCycle

class ScalatraInit extends LifeCycle {

    override def init( context : ServletContext ) {
        context.mount( classOf[ BigramModelerServlet ], "/*" )
    }

}
