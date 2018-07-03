package org.reynoldsm88.bigram.modeler.text.sources

import org.apache.spark.rdd.RDD

trait TextSource {

    def lines( data : RDD[ String ] ) : RDD[ String ]

    protected def filterBy( data : RDD[ String ], criteria : String ) : RDD[ String ] = {
        data.filter( line => line.contains( criteria ) )
    }

    protected def splitForPattern( data : RDD[ String ], pattern : String, index : Int ) : RDD[ String ] = {
        data.map( line => line.split( pattern )( index ).trim )
    }

    def tokenCount( data : RDD[ String ] ) : Long = {
        lines( data ).map( line => line.split( " " ).toList )
                     .map( list => (list, list.size) )
                     .reduceByKey( _ + _ )
                     .map(tuple => tuple._2 )
                     .reduce( _ + _ )
    }
}