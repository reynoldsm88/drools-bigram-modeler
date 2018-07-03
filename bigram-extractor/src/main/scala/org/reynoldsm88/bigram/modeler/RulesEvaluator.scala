package org.reynoldsm88.bigram.modeler

import org.kie.api.runtime.ClassObjectFilter
import org.kie.api.{KieBase, KieServices}

import scala.collection.mutable.ListBuffer

object RulesEvaluator extends {

    private val kservices : KieServices = KieServices.Factory.get

    def execute( kbase : KieBase, session : String, fact : Any, resultType : Class[ _ ] ) : List[ Any ] = {
        // TODO would really rather be able to specify the ksession here

        val ksession = kbase.newKieSession()
        ksession.insert( fact )
        ksession.fireAllRules()

        val resultsBuffer = new ListBuffer[ Any ]
        val i = ksession.getObjects( new ClassObjectFilter( resultType ) ).iterator()

        while ( i.hasNext ) resultsBuffer += i.next

        resultsBuffer.toList
    }

}