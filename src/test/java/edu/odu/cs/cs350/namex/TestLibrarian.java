package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashSet;

import org.junit.Test;

import edu.odu.cs.cs350.namex.Librarian;

//import edu.odu.cs.cs350.Librarian;

/**
 * Test class for Librarian.java
 * 
 * @author Caroline Chey
 *
 */
public class TestLibrarian {

	// User Story #844 - Gerard Silverio
	// As a Librarian/application developer, I want a program that will
	// accept standard input from command line interface
	// User Story #845
	// As a Librarian/application developer, I want a program that will
	// send output to standard command line interface
	@Test
	public void testMain() throws FileNotFoundException {
		// CLI String input
		// Librarian.main(new String[] {"<NER>Hello, <PER>John Smith</PER> There
		// are snakes on this plane! <PER>Mr. Samuel L Jackson, III</PER> I
		// don't know what to do!</NER><NER>Hello World line 2</NER><NER>Goodbye
		// world!</NER>"});
		
		// call to main with one textblock
		Librarian.main(new String[] { "The authors of this textbook are John Smith, Amy R. Johnson and Mr. Samuel Jackson." });
		
		

		// CLI .txt file input

		// CLI generate ARFF training data
		/*
		String inputFilePath = "/data/training/trainingData.txt";
		String outputFilePath = "/data/arff/trainingData.arff";

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		inputFilePath = relativePath + "" + inputFilePath;
		outputFilePath = relativePath + "" + outputFilePath;
		
		Librarian.main(new String[] { "train", inputFilePath, outputFilePath });
		*/		
	}
	
	
	@Test
	/**
	 * test for Librarian constructor
	 */
	public void testLibrarian() {

		Librarian cathy = new Librarian();
		Librarian tom = new Librarian();
		
		/*
		// cathy is not null
		assertTrue(cathy != null);
		assertTrue(tom != null);

		// cathy has working lm and trainer
		assertFalse(cathy.lm == null);
		assertTrue(cathy.trainer != null);
		assertFalse(tom.lm == null);
		assertTrue(tom.trainer != null);
		
		assertFalse(cathy.lm.equals(tom.lm));
		*/
	}
	
	
	/**
	 * test for getFeatures method
	 */
	@Test
	public void testGetFeaturesToken() {

		Token tks1 = new Token("Hello");
		Token tks2 = new Token("hello");
		Token tks3 = new Token("John");
		Token tks4 = new Token("Alaska");
		Token tks5 = new Token("Alaska");

		Librarian librarian = new Librarian();
		tks1 = librarian.getFeatures(tks1);
		tks2 = librarian.getFeatures(tks2);
		tks3 = librarian.getFeatures(tks3);
		tks4 = librarian.getFeatures(tks4);
		tks5 = librarian.getFeatures(tks5);

		// Test strings for common token variations to ensure the features are
		// setting correctly

		assertEquals(tks1.toString(), "capitalized,other,1,0,0,0,0,0,0,0,0,0,0,0");
		assertEquals(tks2.toString(), "other,other,1,0,0,0,0,0,0,0,0,0,0,0");
		assertEquals(tks3.toString(), "capitalized,other,1,0,0,1,1,1,1,1,0,0,0,0");
		assertEquals(tks4.toString(), "capitalized,other,1,0,0,1,0,0,0,0,0,0,0,0");
		assertEquals(tks5.toString(), "capitalized,other,1,0,0,1,0,0,0,0,0,0,0,0");

	}

	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Trainer#getPartOfSpeech(java.lang.String)}.
	 */
	@Test
	public void testGetPartOfSpeech() {
		
		Librarian librarian = new Librarian();
		Token token1 = new Token("and");
		Token token2 = new Token("an");
		Token token3 = new Token("the");
		Token token4 = new Token(".");
		Token token5 = new Token(",");
		Token token6 = new Token("-");
		Token token7 = new Token("and");
		Token token8 = new Token("test");
		
		token1 = librarian.getFeatures(token1);
		token2 = librarian.getFeatures(token2);
		token3 = librarian.getFeatures(token3);
		token4 = librarian.getFeatures(token4);
		token5 = librarian.getFeatures(token5);
		token6 = librarian.getFeatures(token6);
		token7 = librarian.getFeatures(token7);
		token8 = librarian.getFeatures(token8);
		
		System.out.println(token1.getPartOfSpeech());
		System.out.println(token2.getPartOfSpeech());
		System.out.println(token3.getPartOfSpeech());
		System.out.println(token4.getPartOfSpeech());
		System.out.println(token5.getPartOfSpeech());
		System.out.println(token6.getPartOfSpeech());
		System.out.println(token7.getPartOfSpeech());
		System.out.println(token8.getPartOfSpeech());

		assertEquals(token1.getPartOfSpeech(), "conjunction");
		assertEquals(token2.getPartOfSpeech(), "article");
		assertEquals(token3.getPartOfSpeech(), "article");
		assertEquals(token4.getPartOfSpeech(), "period");
		assertEquals(token5.getPartOfSpeech(), "comma");
		assertEquals(token6.getPartOfSpeech(), "hyphen");
		assertEquals(token7.getPartOfSpeech(), "conjunction");
		assertEquals(token8.getPartOfSpeech(), "other");
		
		assertFalse(token2.getPartOfSpeech() != "article");
		assertTrue(token1.getPartOfSpeech() == token7.getPartOfSpeech());
	}
	
	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Libarian#isCityState(java.lang.String)}.
	 */
	@Test
	public void testIsCityState() 
	{
		Librarian librarian = new Librarian();
		String string0 = "Norfolk, Virginia";
		String string1 = "Washington, DC";
		String string2 = "television";
		
		assertEquals(1, librarian.isCityState(string0.toLowerCase()));
		assertEquals(1, librarian.isCityState(string1.toLowerCase()));
		assertEquals(0, librarian.isCityState(string2.toLowerCase()));
	}
	
	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Libarian#isCommonFirstName(java.lang.String)}.
	 */
	@Test
	public void testIsCommonFirstName()
	{
		Librarian librarian = new Librarian();
		String string1 = "John";
		String string2 = "Mary";
		String string3 = "Joseph";
		String string4 = "lafsdjflasdf";
		
		assertEquals(1, librarian.isCommonFirstName(string1.toLowerCase()));
		assertEquals(1, librarian.isCommonFirstName(string2.toLowerCase()));
		assertEquals(1, librarian.isCommonFirstName(string3.toLowerCase()));
		assertEquals(0, librarian.isCommonFirstName(string4.toLowerCase()));
	}
	
	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Libarian#isCommonLastName(java.lang.String)}.
	 */
	@Test
	public void testIsCommonLastName()
	{
		Librarian librarian = new Librarian();
		String string1 = "Smith";
		String string2 = "Brown";
		String string3 = "Jackson";
		String string4 = "lafsdjflasdf";
		
		assertEquals(1, librarian.isCommonLastName(string1.toLowerCase()));
		assertEquals(1, librarian.isCommonLastName(string2.toLowerCase()));
		assertEquals(1, librarian.isCommonLastName(string3.toLowerCase()));
		assertEquals(0, librarian.isCommonLastName(string4.toLowerCase()));
	}
	
	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Libarian#isCountryTerritory(java.lang.String)}.
	 */
	@Test
	public void testIsCountryTerritory()
	{
		Librarian librarian = new Librarian();
		String string1 = "Egypt";
		String string2 = "United States";
		String string3 = "Mexico";
		String string4 = "lafsdjflasdf";
		
		assertEquals(1, librarian.isCountryTerritory(string1.toLowerCase()));
		assertEquals(1, librarian.isCountryTerritory(string2.toLowerCase()));
		assertEquals(1, librarian.isCountryTerritory(string3.toLowerCase()));
		assertEquals(0, librarian.isCountryTerritory(string4.toLowerCase()));
	}
	
	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Libarian#isDictionaryWord(java.lang.String)}.
	 */
	@Test
	public void testIsDictionaryWord()
	{
		Librarian librarian = new Librarian();
		String string1 = "stop";
		String string2 = "hello";
		String string3 = "welcome";
		String string4 = "lafsdjflasdf";
		
		assertEquals(1, librarian.isDictionaryWord(string1.toLowerCase()));
		assertEquals(1, librarian.isDictionaryWord(string2.toLowerCase()));
		assertEquals(1, librarian.isDictionaryWord(string3.toLowerCase()));
		assertEquals(0, librarian.isDictionaryWord(string4.toLowerCase()));
	}
	
	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Libarian#isDTICFirstName(java.lang.String)}.
	 */
	@Test
	public void testIsDTICFirstName()
	{
		Librarian librarian = new Librarian();
		String string1 = "John";
		String string2 = "Jacob";
		String string3 = "Mary";
		String string4 = "lafsdjflasdf";
		
		//librarian.getCityState();
		
		System.out.println(librarian.isDTICFirstName(string1.toLowerCase()));
		System.out.println(librarian.isDTICFirstName(string2.toLowerCase()));
		System.out.println(librarian.isDTICFirstName(string3.toLowerCase()));
		System.out.println(librarian.isDTICFirstName(string4.toLowerCase()));
		
		assertEquals(1, librarian.isDTICFirstName(string1.toLowerCase()));
		assertEquals(1, librarian.isDTICFirstName(string2.toLowerCase()));
		assertEquals(1, librarian.isDTICFirstName(string3.toLowerCase()));
		assertEquals(0, librarian.isDTICFirstName(string4.toLowerCase()));
	}
	
	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Libarian#isDTICLastName(java.lang.String)}.
	 */
	@Test
	public void testIsDTICLasttName()
	{
		Librarian librarian = new Librarian();
		String string1 = "Smith";
		String string2 = "Jackson";
		String string3 = "Johnson";
		String string4 = "lafsdjflasdf";
		
		assertEquals(1, librarian.isDTICLastName(string1.toLowerCase()));
		assertEquals(1, librarian.isDTICLastName(string2.toLowerCase()));
		assertEquals(1, librarian.isDTICLastName(string3.toLowerCase()));
		assertEquals(0, librarian.isDTICLastName(string4.toLowerCase()));
	}
	
	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Libarian#isHonorific(java.lang.String)}.
	 */
	@Test
	public void testIsHonorific()
	{
		Librarian librarian = new Librarian();
		String string1 = "Mr";
		String string2 = "Professor";
		String string3 = "Captain";
		String string4 = "lafsdjflasdf";
		
		assertEquals(1, librarian.isHonorific(string1.toLowerCase()));
		assertEquals(1, librarian.isHonorific(string2.toLowerCase()));
		assertEquals(1, librarian.isHonorific(string3.toLowerCase()));
		assertEquals(0, librarian.isHonorific(string4.toLowerCase()));
	}
	
	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Libarian#isKillWord(java.lang.String)}.
	 */
	@Test
	public void testIsKillWord()
	{
		Librarian librarian = new Librarian();
		String string1 = "University";
		String string2 = "Street";
		String string3 = "School";
		String string4 = "lafsdjflasdf";
		
		assertEquals(1, librarian.isKillWord(string1.toLowerCase()));
		assertEquals(1, librarian.isKillWord(string2.toLowerCase()));
		assertEquals(1, librarian.isKillWord(string3.toLowerCase()));
		assertEquals(0, librarian.isKillWord(string4.toLowerCase()));
	}
	
	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Libarian#isLastNamePrefix(java.lang.String)}.
	 */
	@Test
	public void testIsLastNamePrefix()
	{
		Librarian librarian = new Librarian();
		String string1 = "von";
		String string2 = "de";
		String string3 = "van";
		String string4 = "lafsdjflasdf";
		
		//librarian.getCityState();
		
		System.out.println(librarian.isLastNamePrefix(string1.toLowerCase()));
		System.out.println(librarian.isLastNamePrefix(string2.toLowerCase()));
		System.out.println(librarian.isLastNamePrefix(string3.toLowerCase()));
		System.out.println(librarian.isLastNamePrefix(string4.toLowerCase()));
		
		assertEquals(1, librarian.isLastNamePrefix(string1.toLowerCase()));
		assertEquals(1, librarian.isLastNamePrefix(string2.toLowerCase()));
		assertEquals(1, librarian.isLastNamePrefix(string3.toLowerCase()));
		assertEquals(0, librarian.isLastNamePrefix(string4.toLowerCase()));
	}
	
	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Libarian#isLastNameSuffix(java.lang.String)}.
	 */
	@Test
	public void testIsLastNameSuffix()
	{
		Librarian librarian = new Librarian();
		String string1 = "Jr";
		String string2 = "II";
		String string3 = "IV";
		String string4 = "lafsdjflasdf";
				
		assertEquals(1, librarian.isLastNameSuffix(string1.toLowerCase()));
		assertEquals(1, librarian.isLastNameSuffix(string2.toLowerCase()));
		assertEquals(1, librarian.isLastNameSuffix(string3.toLowerCase()));
		assertEquals(0, librarian.isLastNameSuffix(string4.toLowerCase()));
	}
	
	/**
	 * Test method for
	 * {@link edu.odu.cs.cs350.namex.Libarian#isPlace(java.lang.String)}.
	 */
	@Test
	public void testIsPlace()
	{
		Librarian librarian = new Librarian();
		String string1 = "Potomac River";
		String string2 = "Virginia Beach";
		String string3 = "Lake Ontario";
		String string4 = "lafsdjflasdf";
		
		assertEquals(1, librarian.isPlace(string1.toLowerCase()));
		assertEquals(1, librarian.isPlace(string2.toLowerCase()));
		assertEquals(1, librarian.isPlace(string3.toLowerCase()));
		assertEquals(0, librarian.isPlace(string4.toLowerCase()));
	}
	
	
	/**
	 * test for copy constructor; public Librarian(Librarian toCopy)
	 */
	/*
	@Test
	public void testLibrarianCopy(){
		Librarian cat = new Librarian();
		Librarian copycat = new Librarian(cat);
		
		assertTrue(cat != null);
		assertTrue(copycat != null);
		assertEquals(cat.getClass() , copycat.getClass());
		assertTrue(cat.equals(copycat));
		assertEquals(cat.lm , copycat.lm);
		assertEquals(cat.trainer, copycat.trainer);
		
	}
	*/

	/**
	 * test for public static void main(String[] args) throws
	 * FileNotFoundException
	 */
	/*
	@Test
	public void testMain() throws Exception 
	{
		Librarian lana = new Librarian();
		assertTrue(lana.trainer != null);
		assertTrue(lana.lm != null);
		assertFalse(lana.lm.getNumberOfAttributes() < 0);
		
		int lanalmsize = lana.lm.getSizeOfAttributes();
		//System.out.println("lana.lm.getSizeOfAttributes() = " + lanalmsize);
		assertEquals(71, lana.lm.getSizeOfAttributes());
		
		assertFalse(lana.lm.getEvalSummary().equals(""));
		assertTrue(lana.lm.getTrainingInstances() != null);

		String[] args = { "train", "src/main/data/trainingMaterial002.txt", "trainingDatao.txt" };

		try {
			lana.main(args);
		} catch (Exception e224) {
			e224.printStackTrace();
		}
	}
	*/

	/*
	 * 
	 * 
	 * // User Story #846 // Status - Development // PNE packaged for deployment
	 * in fat jar (A)
	 * 
	 * @Test public void testPackage() { // is it possible to even create a
	 * JUnit test to see if the PNE was // packaged in a fat jar? // // No, this
	 * should be tested by System Testing and not Unit Testing - // Tristan }
	 * 
	 * // User Story #852 As a Librarian, I want names of places to be
	 * identified // correctly
	 * 
	 * @Test public void identifyPlace() { // run shingles against the Places
	 * gazetteer list }
	 * 
	 * // COMPLETED USER STORIES
	 * 
	 * // User Story #844 - Gerard Silverio // As a Librarian/application
	 * developer, I want a program that will // accept standard input from
	 * command line interface // User Story #845 // As a Librarian/application
	 * developer, I want a program that will // send output to standard command
	 * line interface
	 * 
	 * @Test public void testMain() throws FileNotFoundException { // CLI String
	 * input // Librarian.main(new String[] {"<NER>Hello, <PER>John Smith</PER>
	 * There // are snakes on this plane! <PER>Mr. Samuel L Jackson, III</PER> I
	 * // don't know what to do!</NER><NER>Hello World line 2</NER><NER>Goodbye
	 * // world!</NER>"});
	 * 
	 * // CLI .txt file input
	 * 
	 * // CLI generate ARFF training data String inputFilePath =
	 * "/data/training/trainingData.txt"; String outputFilePath =
	 * "/data/arff/trainingData.arff";
	 * 
	 * Path currentRelativePath = Paths.get(""); String relativePath =
	 * currentRelativePath.toAbsolutePath().toString(); inputFilePath =
	 * relativePath + "" + inputFilePath; outputFilePath = relativePath + "" +
	 * outputFilePath;
	 * 
	 * Librarian.main(new String[] { "train", inputFilePath, outputFilePath });
	 * }
	 * 
	 * // User Story #861 // Status: Complete // As a Librarian/application
	 * developer I want to use Command line to // process each block of text
	 * separately via the personal name extractor
	 * 
	 * @Test public void testSeparateNER() throws FileNotFoundException { String
	 * input =
	 * "<NER>Hello, There are snakes on this plane! I don't know what to do!</NER><NER>This should be another text block!</NER>"
	 * ;
	 * 
	 * ArrayList<TextBlock> textBlocks = Librarian.detectNERTag(input);
	 * 
	 * // separateNER should have separated the input into two lines
	 * assertEquals(2, textBlocks.size()); }
	 * 
	 * // User Story #850 // Status: Complete // As a Librarian, I want the PNE
	 * to use a learning machine to // classify tokens within the input.
	 * 
	 * @Test public void testGetFeatures() { Librarian librarian = new
	 * Librarian(); Trainer trainer = new Trainer(); LearningMachine
	 * learningMachine = new LearningMachine();
	 * 
	 * // Change to test_training_data to make building the project faster
	 * String arffFilePath = "/data/arff/test_training_data.arff"; // String
	 * arffFilePath = "/data/arff/training_data_zeil.arff"; // actual //
	 * training data
	 * 
	 * String input = "Mr. Samuel L. Jackson, Jr.";
	 * 
	 * Path currentRelativePath = Paths.get(""); String relativePath =
	 * currentRelativePath.toAbsolutePath().toString(); arffFilePath =
	 * relativePath + "" + arffFilePath;
	 * 
	 * ArrayList<Token> testTokens = trainer.tokenize(input);
	 * 
	 * try { learningMachine.importARFF(arffFilePath); learningMachine.train();
	 * learningMachine.printEvaluationSummary();
	 * 
	 * for (Token t : testTokens) { t = librarian.getFeatures(t);
	 * 
	 * if (t.getLexical() != "whiteSpace") { if (t.getLexical() ==
	 * "capitalized") { // Since all of the capitalized values in the test input
	 * // are parts of names, they should all be classified as // either
	 * 'beginning' or 'continuing' assertTrue("beginning" ==
	 * learningMachine.classify(t.toString()) || "continuing" ==
	 * learningMachine.classify(t.toString()));
	 * 
	 * }
	 * 
	 * 
	 * // Uncomment to view Token classification on the console
	 * System.out.println(t.getLexeme() + ": " +
	 * trainer.classify(t.toString()));
	 * 
	 * // Uncomment to view Token distribution on console double[] distribution
	 * = trainer.getDistribution(t.toString()); System.out.println("beginning: "
	 * + distribution[0]); System.out.println("continuing: " + distribution[1]);
	 * System.out.println("other: " + distribution[2] + "\n");
	 * 
	 * } } } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * 
	 * // User Story #856 // Status - Completed // Dictionary features
	 * identified correctly (L) // User Story #854 // Status - Completed // Misc
	 * features (honorifics, kill words, etc) identified correctly. (L)
	 * 
	 * @Test public void testClassifyToken() { Librarian librarian = new
	 * Librarian();
	 * 
	 * Token token = new Token("Samuel"); token = librarian.getFeatures(token);
	 * 
	 * assertEquals("capitalized,other,1,0,0,1,1,1,1,1,0,0,0,0",
	 * token.toString()); }
	 * 
	 * // User Story #1094 // Status - Completed // As a librarian, I want Token
	 * Lexical features to be identified correctly.
	 * 
	 * @Test public void testGetLexicalFeature() { Librarian librarian = new
	 * Librarian();
	 * 
	 * Token t1 = new Token("Samuel"); Token t2 = new Token("SAMUEL"); Token t3
	 * = new Token("."); Token t4 = new Token("S");
	 * 
	 * assertEquals("capitalized", librarian.getLexicalFeature(t1.getLexeme()));
	 * assertEquals("allCaps", librarian.getLexicalFeature(t2.getLexeme()));
	 * assertEquals("punct", librarian.getLexicalFeature(t3.getLexeme()));
	 * assertEquals("capLetter", librarian.getLexicalFeature(t4.getLexeme())); }
	 */

}