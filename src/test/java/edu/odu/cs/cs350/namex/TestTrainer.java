package edu.odu.cs.cs350.namex;

//import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;
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
		/*ArrayList<String> tokenizedOutput = new ArrayList();
		Scanner scanner = new Scanner(new BufferedReader(new StringReader(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>")));

		scanner.useDelimiter("\\s|(?=\\p{Punct})");
		while (scanner.hasNext()) {
			tokenizedOutput.add(scanner.next());
		}*/

		Trainer trainer = new Trainer();
		Token token = new Token();
		token.setText("Oh");
		token.setArticle(false);
		token.setCapitalized(true);
		token.setKillWord(false);
		assertEquals(token.getText(), trainer.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>").get(1).getText());
		assertEquals(token.isCapitalized(), trainer.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>").get(1).isCapitalized());
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
		assertTrue(tokenizedText.get(11).isArticle());
		assertTrue(tokenizedText.get(0).isPunctuation());
	}

	/**
	 * User story #858, #857 The total token count for any token should be equal
	 * to the number of times that token appears in the input
	 */
	@Test
	public void testGetTokenCount() {
		Trainer trainer = new Trainer();
		trainer.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>");
		assertEquals(3, trainer.getTokenCount(","));
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
		Librarian lib = new Librarian();

		String s = "We were crossing the George Washington Bridge.";
		String ss = "It was an honor to be accepted to George Mason University.";
		String sss = "Dr.Carson wrote this book.";

		assertTrue(t1.trainLM(s));
		assertTrue(t2.trainLM(s));
		assertTrue(t2.trainLM(ss));
		assertTrue(t1.trainLM(sss) && lib.markPERtag(sss).equals("<PER>Dr.Carson</PER>"));
		assert (t1.trainLM(ss) == t2.trainLM(ss));

	}

	public void testLoadLM() {
		Trainer t1 = new Trainer();
		String fileLoc = "";// declare file location

		assertTrue(t1.LoadLM(fileLoc));

	}

	public void testSaveLM() {
		Trainer t1 = new Trainer();
		String fileLoc = "";// declare file location

		assertTrue(t1.SaveLM(fileLoc));

	}

}
