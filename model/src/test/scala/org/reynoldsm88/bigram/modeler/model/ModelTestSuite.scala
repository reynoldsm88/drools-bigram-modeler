package org.reynoldsm88.bigram.modeler.model

import java.util.UUID

import org.scalatest.FlatSpec

class ModelTestSuite extends FlatSpec {

    "Sentences" should "produce accurate bigram instances" in {
        val example = "<s> this is my sentence </s>"
        val sentence = Sentence( UUID.randomUUID().toString, example )

        val expected = List( BiGramInstance( "<s> this", "is" ),
                             BiGramInstance( "this is", "my" ),
                             BiGramInstance( "is my", "sentence" ),
                             BiGramInstance( "my sentence", "</s>" ) )

        val actual = sentence.biGramsScala()
        assert( expected.exists( a => actual.exists( b => a.root == b.root && a.stem == b.stem ) ) )
    }

    "Bigrams" should "be able to get the root of the next Bigram" in {
        val bigram : BiGram = BiGram( "foo foo", "bar", 1 )

        assert( "foo bar" == bigram.next )

    }

}