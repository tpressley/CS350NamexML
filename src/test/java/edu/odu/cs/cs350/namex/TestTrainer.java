package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.odu.cs.cs350.namex.Trainer;

import java.util.*;

/**
 * Test class for Trainer.java
 * 
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
		tokens = Trainer.tokenize(string);

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
		
		Librarian librarian = new Librarian();
		tokens = librarian.getAllFeatures(tokens);

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


	// Tests the constructors
	@Test
	public void testTrainer() {

		// ensure the default constructor creates a trainer object properly.
		Trainer t1 = new Trainer();
		assertNotNull(t1);

		// assertTrue(t2.getLM().getNumberOfAttributes() == 106);
	}

}