package org.reynoldsm88.bigram.modeler.endpoint

import javax.servlet.ServletContext
import org.apache.spark.{SparkConf, SparkContext}
import org.reynoldsm88.bigram.modeler.{BigramExtractor, ClasspathRulesProvider, SparkDroolsBigramExtractor}
import org.scalatra.LifeCycle

class ScalatraInit extends LifeCycle {

    override def init( context : ServletContext ) {
        val spark : SparkContext = new SparkContext( new SparkConf().setAppName( "Spark + Drools Bigram Modeler" ) )
        val extractor : BigramExtractor = new SparkDroolsBigramExtractor( spark ) with ClasspathRulesProvider
        context.mount( new BigramModelerServlet( extractor ), "/*" )
    }
}
