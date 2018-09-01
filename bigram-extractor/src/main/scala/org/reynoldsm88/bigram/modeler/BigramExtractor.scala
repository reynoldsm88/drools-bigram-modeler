package org.reynoldsm88.bigram.modeler

import org.apache.spark.SparkContext
import org.reynoldsm88.bigram.modeler.config.JobConfig
import org.reynoldsm88.bigram.modeler.model.LangModel

trait BigramExtractor {
    def buildLangModel( config : JobConfig, spark : SparkContext ) : LangModel
}
