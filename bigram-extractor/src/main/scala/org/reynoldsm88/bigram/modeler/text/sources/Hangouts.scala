package org.reynoldsm88.bigram.modeler.text.sources

import org.apache.spark.rdd.RDD

/**
  * Parser for turning a google hangouts conversation
  *
  * @param username - param for filtering for only one specific user
  */
class Hangouts( val username : Option[ String ] ) extends TextSource {

    override def lines( data : RDD[ String ] ) : RDD[ String ] = {
        val hangoutsFormatUsername = "<" + username.get + ">"

        if ( !username.isEmpty ) {
            splitForPattern( filterBy( data, username.get ), hangoutsFormatUsername, 1 ).map( line => "<s> " + line + " </s>" )
        }
        else {
            splitForPattern( data, hangoutsFormatUsername, 1 ).map( line => "<s> " + line + " </s>" )
        }
    }
}