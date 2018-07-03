package org.reynoldsm88.bigram.modeler.model

import java.util.{List => JList}

import scala.beans.BeanInfo
import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

@BeanInfo
case class Sentence( id : String, text : String ) {

    val NGRAM_SIZE = 2

    def biGrams( ) : JList[ BiGramInstance ] = {
        biGramsScala().asJava
    }

    def biGramsScala( ) : List[ BiGramInstance ] = {

        val tokens : List[ String ] = text.split( " " ).toList
        val bigrams : ListBuffer[ BiGramInstance ] = new ListBuffer[ BiGramInstance ]()

        for ( i <- 0 until ( tokens.size - NGRAM_SIZE ) ) {
            val root = tokens.slice( i, i + NGRAM_SIZE ).mkString( " " )
            val stem = tokens( i + NGRAM_SIZE )
            bigrams += BiGramInstance( root, stem )
        }

        bigrams.toList
    }
}