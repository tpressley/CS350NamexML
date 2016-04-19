package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class TestIntegration {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testArffCreation() {
		String trainingMaterials = "<NER>! -- r 1 - (N- 1) + $2 N- 1)</NER>\n<NER>\"*</NER>\n<NER>\"China's Pursuit for World Power Status : Is the Transformation of the</NER>";
		Trainer trainer = new Trainer();
		ArrayList<Token> tokens = new ArrayList<Token>();
		LinkedList<Shingle> shingles = new LinkedList<Shingle>();
		tokens = Trainer.tokenize(trainingMaterials);
		
		fail("Not yet implemented");
	}

}
