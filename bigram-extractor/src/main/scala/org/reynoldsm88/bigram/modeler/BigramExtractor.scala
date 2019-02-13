package org.reynoldsm88.bigram.modeler

import org.reynoldsm88.bigram.modeler.config.JobConfig
import org.reynoldsm88.bigram.modeler.model.BiGram

trait BigramExtractor {
    def extractBigrams( jobConfig : JobConfig ) : Set[ BiGram ]
}
