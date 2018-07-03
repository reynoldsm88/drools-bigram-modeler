package org.reynoldsm88.bigram.modeler.model

import scala.beans.BeanInfo

@BeanInfo
case class BiGram( root : String, stem : String, count : Long ) {}

object BiGram {
    def parse( string : String ) : BiGram = {
        val elems = string.split( "," )
        BiGram( elems( 0 ), elems( 1 ), elems( 2 ).toLong )
    }

    def serialize( b : BiGram ) : String = b.root + "," + b.stem + "," + b.count

}
