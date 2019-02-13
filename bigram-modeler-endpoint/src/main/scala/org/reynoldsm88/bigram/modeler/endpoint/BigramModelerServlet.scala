package org.reynoldsm88.bigram.modeler.endpoint

import org.json4s.{DefaultFormats, Formats}
import org.reynoldsm88.bigram.modeler.BigramExtractor
import org.reynoldsm88.bigram.modeler.config.JobConfig
import org.reynoldsm88.bigram.modeler.model.{BiGram, LangModel}
import org.scalatra._
import org.scalatra.json._
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

class BigramModelerServlet( extractor : BigramExtractor ) extends ScalatraServlet with JacksonJsonSupport {

    protected implicit lazy val jsonFormats : Formats = DefaultFormats.withBigDecimal

    private val LOG : Logger = LoggerFactory.getLogger( classOf[ BigramModelerServlet ] )
    var jobCount = 0
    var langModel : Option[ LangModel ] = None

    //@formatter:off
    before() {
        contentType = formats( "json" )
    }

    get( "/model" ) {
        if ( langModel.isEmpty ) {
            NotFound( "{message:\"no lang model available, might still be processing\"}" )
        }
        else {
            Ok( langModel.get )
        }
    }

    post( "/execute" ) {
        val jobConfig : JobConfig = parsedBody.extract[ JobConfig ]
        val process = Future {
            val bigrams : Set[BiGram] = extractor.extractBigrams( jobConfig )
            langModel = Some( LangModel( jobCount, bigrams ) )
            jobCount += jobCount + 1
        }

        process.onComplete {
            case Success( value ) => LOG.info( s"Language Model version $jobCount built successfully" )
            case Failure( e ) => e.printStackTrace()
        }
        Ok( JobStarted( jobCount, "The process was started" ) )
    }
    //@formatter:on
}