package org.reynoldsm88.bigram.modeler.endpoint

import org.reynoldsm88.bigram.modeler.BigramExtractor
import org.reynoldsm88.bigram.modeler.config.JobConfig
import org.reynoldsm88.bigram.modeler.model.BiGram

// TODO - @michael - mockito causes classpath issues, using hard coded mocks for now but need to look
// into the Incompatible Class change errors
class DummyBigramExtactor extends BigramExtractor {
    override def extractBigrams( jobConfig : JobConfig ) : Set[ BiGram ] = Set( BiGram( "foo foo", "bar", 1 ) )
}