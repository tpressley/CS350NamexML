package edu.odu.cs.cs350.namex;

//import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.odu.cs.cs350.namex.Librarian;
import edu.odu.cs.cs350.namex.Trainer;

import java.util.*;

public class TestTrainer {

	/**
	 * User story #860 Tests to see if the function properly tokenizes the
	 * output by comparing to the output when using StringTokenizer User Story
	 * #847 As a Trainer I want the PNE to convert tokens into a set of symbols
	 * and identifiers
	 */
	@Test
	public void testTokenize() {
		/*
		 * ArrayList<String> tokenizedOutput = new ArrayList(); Scanner scanner
		 * = new Scanner(new BufferedReader(new StringReader(
		 * "<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>"
		 * )));
		 * 
		 * scanner.useDelimiter("\\s|(?=\\p{Punct})"); while (scanner.hasNext())
		 * { tokenizedOutput.add(scanner.next()); }
		 */

		Trainer trainer = new Trainer();
		Token token = new Token();

		token.setLexeme("Oh");
		token.setPartOfSpeech("other");
		token.setLexical("capitalized");
		token.setKillWord(0);
		ArrayList<Token> tokenList = trainer.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>");
		assertEquals("capitalized", tokenList.get(1).getLexical());
		assertFalse("verb" == tokenList.get(1).getPartOfSpeech());
		assertTrue(trainer.getTokenCount(tokenList.get(0), tokenList) < 1);
	}

	/**
	 * User story #859 The parsing should have the same output as the Stanford
	 * Natural Language parser
	 */
	@Test
	public void testParse() {
		Trainer trainer = new Trainer();
		ArrayList<Token> tokenizedText = trainer.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"a $400 blender can\'t handle something this hard!\"</NER>");
		trainer.parse(tokenizedText);
		assertEquals(tokenizedText.get(11).getPartOfSpeech(), "article");
		assertEquals(tokenizedText.get(0).getLexical(), "punct");
	}

	/**
	 * User story #858, #857 The total token count for any token should be equal
	 * to the number of times that token appears in the input
	 */
	@Test
	public void testGetTokenCount() {
		Trainer trainer = new Trainer();
		ArrayList<Token> tokenizedText = trainer.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>");

		assertEquals(3, trainer.getTokenCount(tokenizedText.get(2), tokenizedText));
	}

	/**
	 * The trainer should probably collect training materials upon creation
	 */
	@Test
	public void testTrainer() {
		Trainer trainer = new Trainer();
		assertTrue(trainer.getTrainingData() != null);
	}

	// User Story #851 As a Trainer, I want the program to properly prepare data
	// to train the learning machine.
	@Test
	public void testGetTrainingData() {

		Trainer t1 = new Trainer();
		assertTrue(t1.getTrainingData() != null);

		Trainer t2 = new Trainer();
		assertTrue(t2.getTrainingData() != null);

		String in = "this is a test.";

		assertEquals(t1.prepareData(in), t2.prepareData(in));
	}

	// User Story #853 As Trainer, I want to use existing data to train the
	// learning machine
	@Test
	public void testTrainLM() {

		Trainer t1 = new Trainer();
		Trainer t2 = new Trainer();
		Librarian lib;

		try {
			lib = new Librarian();

			String s = "We were crossing the George Washington Bridge.";
			String ss = "It was an honor to be accepted to George Mason University.";
			String sss = "Dr.Carson wrote this book.";

			assertTrue(t1.trainLM(s));
			assertTrue(t2.trainLM(s));
			assertTrue(t2.trainLM(ss));
			assertTrue(t1.trainLM(sss) && lib.markPersonalNames(sss).equals("<PER>Dr.Carson</PER>"));
			assert (t1.trainLM(ss) == t2.trainLM(ss));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void testSaveLoadLM() throws Exception {
		Trainer t1 = new Trainer();
		Trainer t2 = new Trainer();
		t2.prepareData("<NER>\"Oh, no,\" she\'s saying, \"a $400 blender can\'t handle something this hard!\"</NER>");
		t2.SaveLM("saved/testTrainer");
		t1.LoadLM("saved/testTrainer");
		assertEquals(t1, t2);

	}

}