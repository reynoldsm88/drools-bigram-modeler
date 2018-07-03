package org.reynoldsm88.bigram.modeler.rules;

import java.util.UUID

import org.kie.api.KieServices
import org.kie.api.runtime.ClassObjectFilter
import org.reynoldsm88.bigram.modeler.model.{BiGram, Sentence}
import org.scalatest.FlatSpec

import scala.collection.mutable.ListBuffer


class BigramRulesTestSuite extends FlatSpec {

    "The rules" should "produce the expected bi-grams" in {
        val ksession = KieServices.Factory.get.newKieClasspathContainer().newKieSession( "bigram-ksession" )

        val sentence1 : Sentence = Sentence( UUID.randomUUID().toString, "<s> this is my sentenece </s>" )
        val sentence2 : Sentence = Sentence( UUID.randomUUID().toString, "<s> this is my other normal sentence </s>" )
        ksession.insert( sentence1 )
        ksession.insert( sentence2 )

        val rulesFired = ksession.fireAllRules()
        val i = ksession.getObjects( new ClassObjectFilter( classOf[ BiGram ] ) ).iterator()
        val resultsBuffer = ListBuffer[ BiGram ]()

        while ( i.hasNext ) {
            resultsBuffer += i.next().asInstanceOf[ BiGram ]
        }

        ksession.dispose()

        val results = resultsBuffer.toList

        val expected = List( BiGram( "my sentenece", "</s>", 1 ),
                             BiGram( "is my", "sentenece", 1 ),
                             BiGram( "normal sentence", "</s>", 1 ),
                             BiGram( "is my", "other", 1 ),
                             BiGram( "other normal", "sentence", 1 ),
                             BiGram( "this is", "my", 2 ),
                             BiGram( "<s> this", "is", 2 ),
                             BiGram( "my other", "normal", 1 ) )

        expected.foreach( x => assert( results.contains( x ) ) )
        assert( rulesFired == 10 )


    }

    "The rules" should "remove sentences with http links" in {
        val ksession = KieServices.Factory.get.newKieClasspathContainer().newKieSession( "bigram-ksession" )

        val sentence1 : Sentence = Sentence( UUID.randomUUID().toString, "<s> this is my sentenece </s>" )
        val sentence2 : Sentence = Sentence( UUID.randomUUID().toString, "<s> this is my link https://reddit.com </s>" )
        val sentence3 : Sentence = Sentence( UUID.randomUUID().toString, "<s> this is my  other link sentence http://google.com </s>" )
        val sentence4 : Sentence = Sentence( UUID.randomUUID().toString, "<s> this is my other normal sentence </s>" )
        ksession.insert( sentence1 )
        ksession.insert( sentence2 )
        ksession.insert( sentence3 )
        ksession.insert( sentence4 )

        val rulesFired = ksession.fireAllRules()
        val i = ksession.getObjects( new ClassObjectFilter( classOf[ BiGram ] ) ).iterator()
        val resultsBuffer = ListBuffer[ BiGram ]()

        while ( i.hasNext ) {
            resultsBuffer += i.next().asInstanceOf[ BiGram ]
        }

        val results = resultsBuffer.toList

        ksession.dispose()

        val expected = List( BiGram( "my sentenece", "</s>", 1 ),
                             BiGram( "is my", "sentenece", 1 ),
                             BiGram( "normal sentence", "</s>", 1 ),
                             BiGram( "is my", "other", 1 ),
                             BiGram( "other normal", "sentence", 1 ),
                             BiGram( "this is", "my", 2 ),
                             BiGram( "<s> this", "is", 2 ),
                             BiGram( "my other", "normal", 1 ) )

        expected.foreach( x => assert( results.contains( x ) ) )
        assert( rulesFired == 12 )
    }
}