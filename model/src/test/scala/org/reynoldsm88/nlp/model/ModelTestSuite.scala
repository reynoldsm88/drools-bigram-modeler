package org.reynoldsm88.bigrm.modeler.model

import java.util.UUID

import org.reynoldsm88.bigram.modeler.model.{BiGram, BiGramInstance, Sentence}
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

    "BiGram parsing" should "work :)" in {
        val data = "one two,three,1"
        val expected = BiGram( "one two", "three", 1 )
        val actual = BiGram.parse( data )

        assert( expected == actual )
    }

    "BiGram serialization" should "work :)" in {
        val data = BiGram( "one two", "three", 1 )
        val expected = "one two,three,1"
        val actual = BiGram.serialize( data )

        assert( expected == actual )
    }
}