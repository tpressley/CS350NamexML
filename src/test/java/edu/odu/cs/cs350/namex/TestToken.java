package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestToken {

	// toString
	@Test
	public void testToString() {
		Token token = new Token("Hello");
		String output = "Hello,capitalized,other,0,0,0,0,0,0,0,0,0,0,0,0,other";

		token.setLexical("capitalized");
		token.setPartOfSpeech("other");
		token.setName("other");

		assertEquals(output, token.toString());
	}

	// Name getter and setter methods
	@Test
	public void testName() {
		Token token = new Token();

		token.setName("beginning");
		assertEquals("beginning", token.getName());
	}

	// KillWord getter and setter methods
	@Test
	public void testKillWord() {
		Token token = new Token();

		token.setKillWord(1);
		;
		assertEquals(1, token.isKillWord());
	}

	// Suffix getter and setter methods
	@Test
	public void testSuffix() {
		Token token = new Token();

		token.setSuffix(1);
		;
		assertEquals(1, token.isSuffix());
	}

	// Prefix getter and setter methods
	@Test
	public void testPrefix() {
		Token token = new Token();

		token.setPrefix(1);
		;
		assertEquals(1, token.isPrefix());
	}

	// Honorific getter and setter methods
	@Test
	public void testHonorific() {
		Token token = new Token();

		token.setHonorific(1);
		;
		assertEquals(1, token.isHonorific());
	}

	// CommonLast getter and setter methods
	@Test
	public void testCommonLast() {
		Token token = new Token();

		token.setCommonLast(1);
		;
		assertEquals(1, token.isCommonLast());
	}

	// CommonFirst getter and setter methods
	@Test
	public void testCommonFirst() {
		Token token = new Token();

		token.setCommonFirst(1);
		;
		assertEquals(1, token.isCommonFirst());
	}

	// DTICLast getter and setter methods
	@Test
	public void testDTICLast() {
		Token token = new Token();

		token.setDTICLast(1);
		;
		assertEquals(1, token.isDTICLast());
	}

	// DTICFirst getter and setter methods
	@Test
	public void testDTICFirst() {
		Token token = new Token();

		token.setDTICFirst(1);
		;
		assertEquals(1, token.isDTICFirst());
	}

	// Place getter and setter methods
	@Test
	public void testPlace() {
		Token token = new Token();

		token.setPlace(1);
		;
		assertEquals(1, token.isPlace());
	}

	// CountryTerritory getter and setter methods
	@Test
	public void testCountryTerritory() {
		Token token = new Token();

		token.setCountryTerritory(1);
		;
		assertEquals(1, token.isCountryTerritory());
	}

	// cityState getter and setter methods
	@Test
	public void testCityState() {
		Token token = new Token();

		token.setCityState(1);
		;
		assertEquals(1, token.isCityState());
	}

	// dictionaryWord getter and setter methods
	@Test
	public void testDictionaryWord() {
		Token token = new Token();

		token.setDictionaryWord(1);
		;
		assertEquals(1, token.isDictionaryWord());
	}

	// partOfSpeech getter and setter methods
	@Test
	public void testPartOfSpeech() {
		Token token = new Token();

		token.setPartOfSpeech("other");
		assertEquals("other", token.getPartOfSpeech());
	}

	// lexical getter and setter methods
	@Test
	public void testLexical() {
		Token token = new Token();

		token.setLexical("other");
		assertEquals("other", token.getLexical());
	}

	// Constructor test
	@Test
	public void testToken() {
		Token token = new Token("Hello");

		assertEquals("Hello", token.getLexeme());
	}

	// lexeme getter and setter methods
	@Test
	public void testLexeme() {
		Token token = new Token();

		token.setLexeme("Hello");
		assertEquals("Hello", token.getLexeme());
	}

}