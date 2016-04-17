package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestToken {
	
	@Test
	/**
	 * test for default constructor; public Token()
	 */
	public void testToken() {
		Token tok = new Token();

		assertTrue(tok != null);
		assertEquals("", tok.getLexeme());
		assertEquals("", tok.getLexical());
		assertEquals("", tok.getPartOfSpeech());
		assertEquals(0, tok.isDictionaryWord());
		assertEquals(0, tok.isCityState());
		assertEquals(0, tok.isCountryTerritory());
		assertEquals(0, tok.isPlace());
		assertEquals(0, tok.isDTICFirst());
		assertEquals(0, tok.isDTICLast());
		assertEquals(0, tok.isCommonFirst());
		assertEquals(0, tok.isCommonLast());
		assertEquals(0, tok.isHonorific());
		assertEquals(0, tok.isPrefix());
		assertEquals(0, tok.isSuffix());
		assertEquals(0, tok.isKillWord());
		assertEquals("", tok.getName());
		assertEquals(0, tok.getPosition());
		assertTrue(tok.getDistribution() != null && tok.getDistribution().length == 1);
	}

	
	@Test
	/**
	 * test for constructor; public Token(String lexeme) 
	 */
	public void testTokenString(){
		
		Token tok = new Token("testLex");
		Token tok2 = new Token("");
		Token tok3 = new Token();
		Token tok4 = new Token("null");

		assertTrue(tok != null);
		assertEquals("testLex", tok.getLexeme());
		assertEquals("", tok.getLexical());
		assertEquals("", tok.getPartOfSpeech());
		assertEquals(0, tok.isDictionaryWord());
		assertEquals(0, tok.isCityState());
		assertEquals(0, tok.isCountryTerritory());
		assertEquals(0, tok.isPlace());
		assertEquals(0, tok.isDTICFirst());
		assertEquals(0, tok.isDTICLast());
		assertEquals(0, tok.isCommonFirst());
		assertEquals(0, tok.isCommonLast());
		assertEquals(0, tok.isHonorific());
		assertEquals(0, tok.isPrefix());
		assertEquals(0, tok.isSuffix());
		assertEquals(0, tok.isKillWord());
		assertEquals("", tok.getName());
		assertEquals(0, tok.getPosition());
		assertTrue(tok.getDistribution() != null && tok.getDistribution().length == 1);
		
		assertTrue(tok2 != null);
		assertEquals("", tok2.getLexeme());
		assertEquals("", tok2.getLexical());
		assertEquals("", tok2.getPartOfSpeech());
		assertEquals(0, tok2.isDictionaryWord());
		assertEquals(0, tok2.isCityState());
		assertEquals(0, tok2.isCountryTerritory());
		assertEquals(0, tok2.isPlace());
		assertEquals(0, tok2.isDTICFirst());
		assertEquals(0, tok2.isDTICLast());
		assertEquals(0, tok2.isCommonFirst());
		assertEquals(0, tok2.isCommonLast());
		assertEquals(0, tok2.isHonorific());
		assertEquals(0, tok2.isPrefix());
		assertEquals(0, tok2.isSuffix());
		assertEquals(0, tok2.isKillWord());
		assertEquals("", tok2.getName());
		assertEquals(0, tok2.getPosition());
		assertTrue(tok2.getDistribution() != null && tok2.getDistribution().length == 1);
		
		assertTrue(tok3 != null);
		assertEquals("", tok3.getLexeme());
		assertEquals("", tok3.getLexical());
		assertEquals("", tok3.getPartOfSpeech());
		assertEquals(0, tok3.isDictionaryWord());
		assertEquals(0, tok3.isCityState());
		assertEquals(0, tok3.isCountryTerritory());
		assertEquals(0, tok3.isPlace());
		assertEquals(0, tok3.isDTICFirst());
		assertEquals(0, tok3.isDTICLast());
		assertEquals(0, tok3.isCommonFirst());
		assertEquals(0, tok3.isCommonLast());
		assertEquals(0, tok3.isHonorific());
		assertEquals(0, tok3.isPrefix());
		assertEquals(0, tok3.isSuffix());
		assertEquals(0, tok3.isKillWord());
		assertEquals("", tok3.getName());
		assertEquals(0, tok3.getPosition());
		assertTrue(tok3.getDistribution() != null && tok3.getDistribution().length == 1);
		
		assertTrue(tok4 != null);
		assertTrue(tok4.getLexeme() == null);
		assertTrue(tok4.getLexical() == null);
		assertTrue(tok4.getPartOfSpeech() == null);
		assertEquals(0, tok4.isDictionaryWord());
		assertEquals(0, tok4.isCityState());
		assertEquals(0, tok4.isCountryTerritory());
		assertEquals(0, tok4.isPlace());
		assertEquals(0, tok4.isDTICFirst());
		assertEquals(0, tok4.isDTICLast());
		assertEquals(0, tok4.isCommonFirst());
		assertEquals(0, tok4.isCommonLast());
		assertEquals(0, tok4.isHonorific());
		assertEquals(0, tok4.isPrefix());
		assertEquals(0, tok4.isSuffix());
		assertEquals(0, tok4.isKillWord());
		assertEquals("", tok4.getName());
		assertEquals(0, tok4.getPosition());
		assertTrue(tok4.getDistribution() != null && tok4.getDistribution().length == 1);
	}
	
	@Test
	/**
	 * test for constructor; public Token(String lexeme, int position)
	 */
	public void testTokenStringInt(){
		
		Token tok = new Token("testLex" , 0);
		Token tok2 = new Token("testLex2" , 1);
		Token tok3 = new Token("testLex3" , 4);
		
		assertTrue(tok != null);
		assertEquals("testLex", tok.getLexeme());
		assertEquals("", tok.getLexical());
		assertEquals("", tok.getPartOfSpeech());
		assertEquals(0, tok.isDictionaryWord());
		assertEquals(0, tok.isCityState());
		assertEquals(0, tok.isCountryTerritory());
		assertEquals(0, tok.isPlace());
		assertEquals(0, tok.isDTICFirst());
		assertEquals(0, tok.isDTICLast());
		assertEquals(0, tok.isCommonFirst());
		assertEquals(0, tok.isCommonLast());
		assertEquals(0, tok.isHonorific());
		assertEquals(0, tok.isPrefix());
		assertEquals(0, tok.isSuffix());
		assertEquals(0, tok.isKillWord());
		assertEquals("", tok.getName());
		assertEquals(0, tok.getPosition());
		assertTrue(tok.getDistribution() != null && tok.getDistribution().length == 1);
		
		assertTrue(tok2 != null);
		assertEquals("testLex2", tok2.getLexeme());
		assertEquals("", tok2.getLexical());
		assertEquals("", tok2.getPartOfSpeech());
		assertEquals(0, tok2.isDictionaryWord());
		assertEquals(0, tok2.isCityState());
		assertEquals(0, tok2.isCountryTerritory());
		assertEquals(0, tok2.isPlace());
		assertEquals(0, tok2.isDTICFirst());
		assertEquals(0, tok2.isDTICLast());
		assertEquals(0, tok2.isCommonFirst());
		assertEquals(0, tok2.isCommonLast());
		assertEquals(0, tok2.isHonorific());
		assertEquals(0, tok2.isPrefix());
		assertEquals(0, tok2.isSuffix());
		assertEquals(0, tok2.isKillWord());
		assertEquals("", tok2.getName());
		assertEquals(1, tok2.getPosition());
		assertTrue(tok2.getDistribution() != null && tok2.getDistribution().length == 1);
		
		assertTrue(tok3 != null);
		assertEquals("testLex3", tok3.getLexeme());
		assertEquals("", tok3.getLexical());
		assertEquals("", tok3.getPartOfSpeech());
		assertEquals(0, tok3.isDictionaryWord());
		assertEquals(0, tok3.isCityState());
		assertEquals(0, tok3.isCountryTerritory());
		assertEquals(0, tok3.isPlace());
		assertEquals(0, tok3.isDTICFirst());
		assertEquals(0, tok3.isDTICLast());
		assertEquals(0, tok3.isCommonFirst());
		assertEquals(0, tok3.isCommonLast());
		assertEquals(0, tok3.isHonorific());
		assertEquals(0, tok3.isPrefix());
		assertEquals(0, tok3.isSuffix());
		assertEquals(0, tok3.isKillWord());
		assertEquals("", tok3.getName());
		assertEquals(4, tok3.getPosition());
		assertTrue(tok3.getDistribution() != null && tok3.getDistribution().length == 1);
	}
	
	
	@Test
	/**
	 * test for public double[] getDistribution()
	 */
	public void testGetDistribution(){
		Token tok = new Token();
		assertTrue(tok.getDistribution() != null);
		assertEquals(1 , tok.getDistribution().length);
		
		Token tok2 = new Token("test");
		assertTrue(tok2.getDistribution() != null);
		assertEquals(1, tok2.getDistribution().length);
	}
	
	@Test
	/**
	 * test for public void setDistribution(double[] distribution) 
	 */
	public void testSetDistribution(){
		double[] test1 = new double[1];
		Token tok = new Token();
		tok.setDistribution(test1);
		
		assertTrue(tok.getDistribution()!=null);
		assertEquals(1, tok.getDistribution().length);
	}
	
	
	// User Story #850
	// Status - Completed
	// Tokens converted into a set of features (T)
	@Test
	public void testToString() {
		Trainer trainer = new Trainer();

		Token token = new Token("Hello");
		token = trainer.getFeatures(token);

		assertEquals("capitalized,other,1,0,0,0,0,0,0,0,0,0,0,0", token.toString());
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


	// lexeme getter and setter methods
	@Test
	public void testLexeme() {
		Token token = new Token();

		token.setLexeme("Hello");
		assertEquals("Hello", token.getLexeme());
	}

}