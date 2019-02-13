package org.reynoldsm88.bigram.modeler.model

import scala.beans.BeanInfo

@BeanInfo
case class BiGram( root : String, stem : String, count : Long ) {
    def next( ) : String = root.split( " " )( 1 ) + " " + stem
}