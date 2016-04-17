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
	
	
	
	@Test
	/**
	 * test for public int getPosition()
	 */
	public void testGetPosition(){
		
		Token tok = new Token("testLex" , 0);
		Token tok2 = new Token("testLex2" , 1);
		Token tok3 = new Token("testLex3" , 4);
		
		assertEquals(0 , tok.getPosition());
		assertEquals(1 , tok2.getPosition());
		assertEquals(4 , tok3.getPosition());
	}
	
	
	@Test
	/**
	 * test for public void setPosition(int position)
	 */
	public void testSetPosition(){
		Token tok = new Token();
		Token tok2 = new Token("test");
		Token tok3 = new Token("test2" , 0);
		Token tok4 = new Token("test3" , 2);
		
		assertEquals(0, tok.getPosition());
		assertEquals(0, tok2.getPosition());
		assertEquals(0, tok3.getPosition());
		assertEquals(2, tok4.getPosition());
		
		tok3.setPosition(3);
		assertEquals(3 , tok3.getPosition());
		
		tok4.setPosition(0);
		assertEquals(0 , tok4.getPosition());
	}
	
	
	@Test
	/**
	 * test for public String getLexeme()
	 */
	public void testGetLexeme(){
		Token tok = new Token();
		Token tok2 = new Token("test");
		
		assertEquals("" , tok.getLexeme());
		assertEquals("test" , tok2.getLexeme());
	}
	
	
	@Test
	/**
	 * test for public void setLexeme(String lexeme)
	 */
	public void testSetLexeme(){
		Token tok = new Token();
		Token tok2 = new Token("test");
		
		tok.setLexeme("new");
		assertEquals("new" , tok.getLexeme());
		
		tok2.setLexeme("next");
		assertEquals("next" , tok2.getLexeme());
		
		tok2.setLexeme("");
		assertEquals("" , tok2.getLexeme());
	}
	
	
	@Test
	/**
	 * test for public String getLexical() 
	 */
	public void testGetLexical(){
		
		Token tok = new Token();
		Token tok2 = new Token("hello");
		Token tok3 = new Token("John");
		Token tok4 = new Token("!");
		Token tok5 = new Token("P");
		
		assertEquals("" , tok.getLexical());
		
		tok2.setLexical("other");
		assertEquals("other" , tok2.getLexical());
		
		tok3.setLexical("capitalized");
		assertEquals("capitalized" , tok3.getLexical());
		
		tok4.setLexical("punct");
		assertEquals("punct" , tok4.getLexical());
		
		tok5.setLexical("CapLetter");
		assertEquals("CapLetter" , tok5.getLexical());
	}

	
	
	@Test
	/**
	 * test for public void setLexical(String lexical)
	 */
	public void testSetLexical(){
		
		/*
		 * Lexical features	
				-   number			
				-   punctuation mark; "punct"			
				-   single capitalized letter; "CapLetter"				
				-   capitalized word (of more than one letter); "capitalized"						
				-   ALL-CAPS word (of more than one letter)						
				-   Line feed / new line						
				-   Null/nothing
		 */
		Token tok = new Token("no"); //other
		Token tok2 = new Token("H"); //CapLetter
		Token tok3 = new Token(","); //punct
		Token tok4 = new Token("School"); //capitalized

		tok.setLexical("other");
		assertEquals("other" , tok.getLexical());

		tok2.setLexical("CapLetter");
		assertEquals("CapLetter" , tok2.getLexical());
		
		tok3.setLexical("punct");
		assertEquals("punct" , tok3.getLexical());
		
		tok4.setLexical("capitalized");
		assertEquals("capitalized" , tok4.getLexical());
	}
	
	
	@Test
	/**
	 * test for public String getPartOfSpeech()
	 */
	public void testGetPartOfSpeech(){
		/*
		 * Parts of Speech

				 - Articles (“a”, “an”, “the”)			
				 - Conjunction, specifically “and”				
				 - Period					
				 - Comma				
				 - Hyphen
				 - other
		 */
		
		Token t1 = new Token("an");
		Token t2 = new Token(".");
		Token t3 = new Token("and");
		Token t4 = new Token("bathroom");
		Token t5 = new Token("H");
		Token t6 = new Token("Sally");
		Token t7 = new Token();
		Token t8 = new Token(",");
		Token t9 = new Token("-");
		
		t1.setLexical("other");
		t1.setPartOfSpeech("article");
		assertEquals(0, ("article").compareToIgnoreCase(t1.getPartOfSpeech()));
		
		t2.setLexical("punct");
		t2.setPartOfSpeech("period");
		assertEquals(0, ("period").compareToIgnoreCase(t2.getPartOfSpeech()));
		
		t3.setLexical("other");
		t3.setPartOfSpeech("conjunction");
		assertEquals(0, ("conjunction").compareToIgnoreCase(t3.getPartOfSpeech()));
		
		t4.setLexical("other");
		t4.setPartOfSpeech("other");
		assertEquals(0, ("other").compareToIgnoreCase(t4.getPartOfSpeech()));
		
		t5.setLexical("CapLetter");
		t5.setPartOfSpeech("other");
		assertEquals(0, ("other").compareToIgnoreCase(t5.getPartOfSpeech()));
		
		t6.setLexical("capitalized");
		t6.setPartOfSpeech("other");
		assertEquals(0, ("other").compareToIgnoreCase(t6.getPartOfSpeech()));
		
		t7.setLexical("null");
		t7.setPartOfSpeech("other");
		assertEquals(0, ("other").compareToIgnoreCase(t7.getPartOfSpeech()));
		
		t8.setLexical("punct");
		t8.setPartOfSpeech("comma");
		assertEquals(0, ("comma").compareToIgnoreCase(t8.getPartOfSpeech()));
		
		t9.setLexical("punct");
		t9.setPartOfSpeech("hyphen");
		assertEquals(0, ("hyphen").compareToIgnoreCase(t9.getPartOfSpeech()));	
	}
	
	@Test
	/**
	 * test for public void setPartOfSpeech(String partOfSpeech)
	 */
	public void testSetPartOfSpeech(){
		/*
		 * Parts of Speech

				 - Articles (“a”, “an”, “the”)			
				 - Conjunction, specifically “and”				
				 - Period					
				 - Comma				
				 - Hyphen
				 - other
		 */
		
		Token t1 = new Token("an");
		Token t2 = new Token(".");
		Token t3 = new Token("and");
		Token t4 = new Token("bathroom");
		Token t5 = new Token("H");
		Token t6 = new Token("Sally");
		Token t7 = new Token();
		Token t8 = new Token(",");
		Token t9 = new Token("-");
		
		//t1.setLexical("other");
		t1.setPartOfSpeech("article");
		assertEquals("article", t1.getPartOfSpeech());
		
		//t2.setLexical("punct");
		t2.setPartOfSpeech("period");
		assertEquals("period", t2.getPartOfSpeech());
		
		//t3.setLexical("other");
		t3.setPartOfSpeech("conjunction");
		assertEquals("conjunction" , t3.getPartOfSpeech());
		
		//t4.setLexical("other");
		t4.setPartOfSpeech("other");
		assertEquals("other" , t4.getPartOfSpeech());
		
		//t5.setLexical("CapLetter");
		t5.setPartOfSpeech("other");
		assertEquals("other" , t5.getPartOfSpeech());
		
		//t6.setLexical("capitalized");
		t6.setPartOfSpeech("other");
		assertEquals("other" , t6.getPartOfSpeech());
		
		//t7.setLexical("null");
		t7.setPartOfSpeech("other");
		assertEquals("other" , t7.getPartOfSpeech());
		
		//t8.setLexical("punct");
		t8.setPartOfSpeech("comma");
		assertEquals("comma" , t8.getPartOfSpeech());
		
		//t9.setLexical("punct");
		t9.setPartOfSpeech("hyphen");
		assertEquals("hyphen" , t9.getPartOfSpeech());	
	}
	
	
	@Test
	/**
	 * test for public int isDictionaryWord()
	 */
	public void testIsDictionaryWord(){
		Token t1 = new Token("desk");
		Token t2 = new Token();
		Token t3 = new Token("");
		Token t4 = new Token("strawberry");
		Token t5 = new Token("ODU");
		
		t1.setDictionaryWord(1);
		t2.setDictionaryWord(0);
		t3.setDictionaryWord(0);
		t4.setDictionaryWord(1);
		t5.setDictionaryWord(0);
		assertEquals(1 , t1.isDictionaryWord());
		assertEquals(0 , t2.isDictionaryWord());
		assertEquals(0 , t3.isDictionaryWord());
		assertEquals(1 , t4.isDictionaryWord());
		assertEquals(0 , t5.isDictionaryWord());
	}
	
	
	@Test
	/**
	 * test for public void setDictionaryWord(int dictionaryWord) 
	 */
	public void testSetDictionaryWord(){
		Token t1 = new Token("desk");
		Token t2 = new Token();
		Token t3 = new Token("");
		Token t4 = new Token("strawberry");
		Token t5 = new Token("ODU");
		
		t1.setDictionaryWord(1);
		t2.setDictionaryWord(0);
		t3.setDictionaryWord(0);
		t4.setDictionaryWord(1);
		t5.setDictionaryWord(0);
		
		assertEquals(1 , t1.isDictionaryWord());
		assertEquals(0 , t2.isDictionaryWord());
		assertEquals(0 , t3.isDictionaryWord());
		assertEquals(1 , t4.isDictionaryWord());
		assertEquals(0 , t5.isDictionaryWord());
		
	}
	
	
	@Test
	/**
	 * test for public int isCityState()
	 */
	public void testIsCityState(){
		Token vermont = new Token("Vermont"); //state
		Token norfolk = new Token("Norfolk"); //city
		Token vabeach = new Token("Virginia Beach"); //city
		Token va = new Token("Virginia"); //state
		Token sc = new Token("South Carolina"); //state
		Token milford = new Token("Milford"); //city
		Token ca = new Token("California"); //state
		Token boston = new Token("Boston"); //city
		Token shanghai = new Token("Shanghai"); //city
		Token london = new Token("London"); //city
		Token cairo = new Token("Cairo"); //city
		
		Token t1 = new Token("John");
		Token t2 = new Token("Ashley");
		Token t3 = new Token("desk");
		Token t4 = new Token("lamp");
		Token t5 = new Token("ocean");
		
		vermont.setCityState(1);
		norfolk.setCityState(1);
		vabeach.setCityState(1);
		va.setCityState(1);
		sc.setCityState(1);
		milford.setCityState(1);
		ca.setCityState(1);
		boston.setCityState(1);
		shanghai.setCityState(1);
		london.setCityState(1);
		cairo.setCityState(1);
		t1.setCityState(0);
		t2.setCityState(0);
		t3.setCityState(0);
		t4.setCityState(0);
		t5.setCityState(0);
		
		assertEquals(1 , vermont.isCityState());
		assertEquals(1 , norfolk.isCityState());
		assertEquals(1 , vabeach.isCityState());
		assertEquals(1 , va.isCityState());
		assertEquals(1 , sc.isCityState());
		assertEquals(1 , milford.isCityState());
		assertEquals(1 , ca.isCityState());
		assertEquals(1 , boston.isCityState());
		assertEquals(1 , shanghai.isCityState());
		assertEquals(1 , london.isCityState());
		assertEquals(1 , cairo.isCityState());
		assertEquals(0, t1.isCityState());
		assertEquals(0, t2.isCityState());
		assertEquals(0, t3.isCityState());
		assertEquals(0, t4.isCityState());
		assertEquals(0, t5.isCityState());
	}
	
	
	@Test
	/**
	 * test for public void setCityState(int cityState)
	 */
	public void testSetCityState(){
		
		Token vermont = new Token("Vermont"); //state
		Token norfolk = new Token("Norfolk"); //city
		Token vabeach = new Token("Virginia Beach"); //city
		Token va = new Token("Virginia"); //state
		Token sc = new Token("South Carolina"); //state
		Token milford = new Token("Milford"); //city
		Token ca = new Token("California"); //state
		Token boston = new Token("Boston"); //city
		
		Token t1 = new Token("John");
		Token t2 = new Token("Ashley");
		Token t3 = new Token("desk");
		Token t4 = new Token("lamp");
		Token t5 = new Token("ocean");
		
		vermont.setCityState(1);
		norfolk.setCityState(1);
		vabeach.setCityState(1);
		va.setCityState(1);
		sc.setCityState(1);
		milford.setCityState(1);
		ca.setCityState(1);
		boston.setCityState(1);
		t1.setCityState(0);
		t2.setCityState(0);
		t3.setCityState(0);
		t4.setCityState(0);
		t5.setCityState(0);
		
		assertEquals(1 , vermont.isCityState());
		assertEquals(1 , norfolk.isCityState());
		assertEquals(1 , vabeach.isCityState());
		assertEquals(1 , va.isCityState());
		assertEquals(1 , sc.isCityState());
		assertEquals(1 , milford.isCityState());
		assertEquals(1 , ca.isCityState());
		assertEquals(1 , boston.isCityState());
		assertEquals(0, t1.isCityState());
		assertEquals(0, t2.isCityState());
		assertEquals(0, t3.isCityState());
		assertEquals(0, t4.isCityState());
		assertEquals(0, t5.isCityState());
	
	}
	
	@Test
	/**
	 * test for public int isCountryTerritory()
	 */
	public void testIsCountryTerritory(){
		Token egypt = new Token("Egypt");
		Token canada = new Token("Canada");
		Token southafrica = new Token("South Africa");
		Token china = new Token("China");
		Token france = new Token("France");
		
		Token t1 = new Token("John");
		Token t2 = new Token("Ashley");
		Token t3 = new Token("desk");
		Token t4 = new Token("lamp");
		Token t5 = new Token("ocean");
		
		egypt.setCountryTerritory(1);
		canada.setCountryTerritory(1);
		southafrica.setCountryTerritory(1);
		china.setCountryTerritory(1);
		france.setCountryTerritory(1);
		t1.setCountryTerritory(0);
		t2.setCountryTerritory(0);
		t3.setCountryTerritory(0);
		t4.setCountryTerritory(0);
		t5.setCountryTerritory(0);
		
		assertEquals(1 , egypt.isCountryTerritory());
		assertEquals(1 , canada.isCountryTerritory());
		assertEquals(1 , china.isCountryTerritory());
		assertEquals(1 , southafrica.isCountryTerritory());
		assertEquals(1 , france.isCountryTerritory());
		assertFalse(t1.isCountryTerritory() == 1);
		assertFalse(t2.isCountryTerritory() == 1);
		assertFalse(t3.isCountryTerritory() == 1);
		assertFalse(t4.isCountryTerritory() == 1);
		assertFalse(t5.isCountryTerritory() == 1);
	}
	
	
	@Test
	/**
	 * test for public void setCountryTerritory(int countryTerritory)
	 */
	public void testSetCountryTerritory(){
		
		Token egypt = new Token("Egypt");
		Token canada = new Token("Canada");
		Token southafrica = new Token("South Africa");
		Token china = new Token("China");
		Token france = new Token("France");
		
		Token t1 = new Token("John");
		Token t2 = new Token("Ashley");
		Token t3 = new Token("desk");
		Token t4 = new Token("lamp");
		Token t5 = new Token("ocean");
		
		egypt.setCountryTerritory(1);
		canada.setCountryTerritory(1);
		southafrica.setCountryTerritory(1);
		china.setCountryTerritory(1);
		france.setCountryTerritory(1);
		t1.setCountryTerritory(0);
		t2.setCountryTerritory(0);
		t3.setCountryTerritory(0);
		t4.setCountryTerritory(0);
		t5.setCountryTerritory(0);
		
		assertEquals(1 , egypt.isCountryTerritory());
		assertEquals(1 , canada.isCountryTerritory());
		assertEquals(1 , china.isCountryTerritory());
		assertEquals(1 , southafrica.isCountryTerritory());
		assertEquals(1 , france.isCountryTerritory());
		assertEquals(0 , t1.isCountryTerritory());
		assertEquals(0 , t2.isCountryTerritory());
		assertEquals(0 , t3.isCountryTerritory());
		assertEquals(0 , t4.isCountryTerritory());
		assertEquals(0 , t5.isCountryTerritory());
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