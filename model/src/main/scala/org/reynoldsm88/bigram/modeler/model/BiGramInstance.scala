package org.reynoldsm88.bigram.modeler.model

import java.util.UUID

import scala.beans.BeanInfo

@BeanInfo
case class BiGramInstance( root : String, stem : String ) {
    val id : String = UUID.randomUUID().toString
}
