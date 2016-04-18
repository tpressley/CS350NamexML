package edu.odu.cs.cs350.namex;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.odu.cs.cs350.namex.Trainer;
import edu.odu.cs.extract.wordlists.WordLists;

import java.util.*;

/**
 * Test class for Trainer.java
 * 
 * @author Addy Alago, Caroline Chey, Tristan Pressley
 *
 */
public class TestTrainer {

	// member variables
	String mArffPath = "/src/main/data/trainingMaterial002.arff";
	String mTrainPathMarked = "/src/main/data/trainingMaterial002.txt";

	String mTrainPathUnmarked = "/src/main/data/trainingMaterial002Unmarked.txt";
	String mArffPathShingle = "/src/main/data/shingling_training_k3_2.arff";

	// ********** USER STORIES UNDER DEVELOPMENT **********

	@Test
	public void testTokenizeArrayListOfString() {
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<Token> tokens = new ArrayList<Token>();
		Token token = new Token("<NER>");
		strings.add("<NER>");
		strings.add("Hello");
		strings.add(",");
		strings.add("I");
		strings.add("'");
		strings.add("m");
		strings.add("home");
		strings.add(".");
		strings.add("</NER>");

		Trainer trainer = new Trainer();
		tokens = trainer.tokenize(strings);
		assertEquals(tokens.get(0), token);
		assertTrue(tokens.size() == strings.size());
		assertFalse(tokens.get(0).equals(strings.get(0)));
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#tokenize(java.lang.String, boolean)}
	 * .
	 */
	@Test
	public void testTokenizeStringBoolean() {
		ArrayList<Token> tokens = new ArrayList<Token>();
		String string = "<NER> Hello, I'm home. </NER>";
		Token token = new Token("<NER>");

		Trainer trainer = new Trainer();
		tokens = trainer.tokenize(string);
		assertEquals(tokens.get(0), token);
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#getTokenCount(int, java.util.ArrayList)}
	 * .
	 */
	@Test
	public void testGetTokenCount() {
		ArrayList<Token> tokens = new ArrayList<Token>();
		String string = "<NER> Hello, I'm, home. </NER>";
		Token token = new Token("<NER>");
		Trainer trainer = new Trainer();
		tokens = trainer.tokenize(string);

		assertEquals(2, Trainer.getTokenCount(2, tokens));
		assertFalse(2 == Trainer.getTokenCount(2, tokens));
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#trainLM(java.lang.String)}.
	 */
	@Test
	public void testTrainLM() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#isCityState(java.lang.String)}.
	 */
	@Test
	public void testIsCityState() {
		Trainer trainer = new Trainer();
		String string0 = "Norfolk";
		String string1 = "Virginia";
		String string2 = "television";

		assertEquals(1, trainer.isCityState(string0));
		assertTrue(trainer.isCityState(string0) == trainer.isCityState(string1));
		assertFalse(1 == trainer.isCityState(string2));
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#isCommonFirstName(java.lang.String)}
	 * .
	 */
	@Test
	public void testIsCommonFirstName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#isCommonLastName(java.lang.String)}
	 * .
	 */
	@Test
	public void testIsCommonLastName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#isCountryTerritory(java.lang.String)}
	 * .
	 */
	@Test
	public void testIsCountryTerritory() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#isDictionaryWord(java.lang.String)}
	 * .
	 */
	@Test
	public void testIsDictionaryWord() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#isDTICFirstName(java.lang.String)}.
	 */
	@Test
	public void testIsDTICFirstName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#isDTICLastName(java.lang.String)}.
	 */
	@Test
	public void testIsDTICLastName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#isHonorific(java.lang.String)}.
	 */
	@Test
	public void testIsHonorific() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#isKillWord(java.lang.String)}.
	 */
	@Test
	public void testIsKillWord() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#isLastNamePrefix(java.lang.String)}
	 * .
	 */
	@Test
	public void testIsLastNamePrefix() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#isLastNameSuffix(java.lang.String)}
	 * .
	 */
	@Test
	public void testIsLastNameSuffix() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#isPlace(java.lang.String)}.
	 */
	@Test
	public void testIsPlace() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#getFeatures(edu.odu.cs.cs350.namex.Token)}
	 * .
	 */
	// @Test
	// public void testGetFeaturesToken()
	// {
	// fail("Not yet implemented");
	// }

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#getFeatures(java.util.ArrayList)}.
	 */
	@Test
	public void testGetFeaturesArrayListOfToken() {
		Token tks1 = new Token("Hello");
		Token tks2 = new Token("hello");
		Token tks3 = new Token("John");
		Token tks4 = new Token("Alaska");
		Token tks5 = new Token("Alaska");
		ArrayList<Token> tokens = new ArrayList<Token>();
		tokens.add(tks1);
		tokens.add(tks2);
		tokens.add(tks3);
		tokens.add(tks4);
		tokens.add(tks5);

		Trainer trainer = new Trainer();
		trainer.getFeatures(tokens);

		// Test strings for common token variations to ensure the features are
		// setting correctly

		assertEquals(tokens.get(1).toString(), "capitalized,other,1,0,0,0,0,0,0,0,0,0,0,0");
		assertEquals(tokens.get(2).toString(), "other,other,1,0,0,0,0,0,0,0,0,0,0,0");
		assertEquals(tokens.get(3).toString(), "capitalized,other,1,0,0,1,1,1,1,1,0,0,0,0");
		assertEquals(tokens.get(4).toString(), "capitalized,other,1,0,0,1,0,0,0,0,0,0,0,0");
		assertEquals(tokens.get(5).toString(), "capitalized,other,1,0,0,1,0,0,0,0,0,0,0,0");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#setFeatures(java.util.ArrayList)}.
	 */
	@Test
	public void testSetFeatures() {
		Token tks1 = new Token("Hello");
		Token tks2 = new Token("hello");
		Token tks3 = new Token("<PER>");
		Token tks4 = new Token("Mr");
		Token tks5 = new Token(".");
		Token tks6 = new Token("Jan");
		Token tk65 = new Token("van");
		Token tks7 = new Token("Brugen");
		Token tks8 = new Token("</PER>");
		Token tks9 = new Token("Alaska");
		Token tk10 = new Token("Alaska");
		HashSet<Token> tokenSet = new HashSet<Token>();
		ArrayList<Token> tokens = new ArrayList<Token>();
		tokens.add(tks1);
		tokens.add(tks2);
		tokens.add(tks3);
		tokens.add(tks4);
		tokens.add(tks5);
		tokens.add(tks6);
		tokens.add(tk65);
		tokens.add(tks7);
		tokens.add(tks8);
		tokens.add(tks9);
		tokens.add(tk10);

		Trainer trainer = new Trainer();
		tokenSet = trainer.setFeatures(tokens);

		// Test strings for common token variations to ensure the features are
		// setting correctly

		assertEquals(10, tokenSet.size());
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#createArff(java.util.LinkedList)}.
	 */
	@Test
	public void testCreateArff() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#getShingles(java.util.ArrayList)}.
	 */
	@Test
	public void testGetShingles() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#getLexicalFeature(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetLexicalFeature() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#getPartOfSpeech(java.lang.String)}.
	 */
	@Test
	public void testGetPartOfSpeech() {
		Trainer trainer = new Trainer();
		Token token1 = new Token("and");
		Token token2 = new Token("an");
		Token token3 = new Token("the");
		Token token4 = new Token(".");
		Token token5 = new Token(",");
		Token token6 = new Token("-");
		Token token7 = new Token("and");
		Token token8 = new Token("test");
		trainer.getFeatures(token1);
		trainer.getFeatures(token2);
		trainer.getFeatures(token3);
		trainer.getFeatures(token4);
		trainer.getFeatures(token5);
		trainer.getFeatures(token6);
		trainer.getFeatures(token7);
		trainer.getFeatures(token8);

		assertEquals(token1.getPartOfSpeech(), "conjunction");
		assertEquals(token2.getPartOfSpeech(), "article");
		assertEquals(token3.getPartOfSpeech(), "article");
		assertEquals(token4.getPartOfSpeech(), "period");
		assertEquals(token5.getPartOfSpeech(), "comma");
		assertEquals(token6.getPartOfSpeech(), "hyphen");
		assertEquals(token6.getPartOfSpeech(), "conjunction");
		assertEquals(token8.getPartOfSpeech(), "other");
		assertFalse(token2.getPartOfSpeech() != "article");
		assertTrue(token1.getPartOfSpeech() == token7.getPartOfSpeech());

	}

	@Test
	public void testGetFeaturesToken() {

		Token tks1 = new Token("Hello");
		Token tks2 = new Token("hello");
		Token tks3 = new Token("John");
		Token tks4 = new Token("Alaska");
		Token tks5 = new Token("Alaska");

		Trainer trainer = new Trainer();
		trainer.getFeatures(tks1);
		trainer.getFeatures(tks2);
		trainer.getFeatures(tks3);
		trainer.getFeatures(tks4);
		trainer.getFeatures(tks5);

		// Test strings for common token variations to ensure the features are
		// setting correctly

		assertEquals(tks1.toString(), "capitalized,other,1,0,0,0,0,0,0,0,0,0,0,0");
		assertEquals(tks2.toString(), "other,other,1,0,0,0,0,0,0,0,0,0,0,0");
		assertEquals(tks3.toString(), "capitalized,other,1,0,0,1,1,1,1,1,0,0,0,0");
		assertEquals(tks4.toString(), "capitalized,other,1,0,0,1,0,0,0,0,0,0,0,0");
		assertEquals(tks5.toString(), "capitalized,other,1,0,0,1,0,0,0,0,0,0,0,0");

	}

	// Tests the constructors
	@Test
	public void testTrainer() {

		// ensure the default constructor creates a trainer object properly.
		Trainer t1 = new Trainer();
		assertNotNull(t1);

		// assertTrue(t2.getLM().getNumberOfAttributes() == 106);
	}

}