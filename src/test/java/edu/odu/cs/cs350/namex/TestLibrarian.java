package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.Test;

import edu.odu.cs.cs350.namex.Librarian;

//import edu.odu.cs.cs350.Librarian;

public class TestLibrarian {

	// ********** TO BE DELETED (USED FOR TESTING PURPOSES) **********

	// prints the weka classification of each token from an input string
	@Test
	public void testWeka() {
		// ********** Configurations **************

		String arffFilePath = "/data/arff/trainingData.arff"; // actual
																// training
																// data
		String input = "Good afternoon, this is John Smith. I will be attending George Washington University next year. Say hello to Mr. Samuel L. Jackson, III";
		boolean printEvaluationSummary = true;
		boolean showClassification = true;

		// ********** End Configurations **********

		Librarian librarian = new Librarian();
		Trainer trainer = new Trainer();
		LearningMachine learningMachine = new LearningMachine();

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		arffFilePath = relativePath + "" + arffFilePath;

		ArrayList<Token> testTokens = trainer.tokenize(input);

		try {
			learningMachine.importARFF(arffFilePath);
			learningMachine.train();

			if (printEvaluationSummary == true) {
				learningMachine.printEvaluationSummary();
			}

			for (Token t : testTokens) {
				t = librarian.getFeatures(t);

				if (t.getLexical() != "whiteSpace") {
					t.setName(learningMachine.classify(t.toString()));
					t.setDistribution(learningMachine.getDistribution(t.toString()));

					if (showClassification == true) {
						t.printTokenData();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ********** USER STORIES UNDER DEVELOPMENT **********

	// User Story #846
	// Status - Development
	// PNE packaged for deployment in fat jar (A)
	@Test
	public void testPackage() {
		// is it possible to even create a JUnit test to see if the PNE was
		// packaged in a fat jar?
		//
		// No, this should be tested by System Testing and not Unit Testing -
		// Tristan
	}

	// User Story #852 As a Librarian, I want names of places to be identified
	// correctly
	@Test
	public void identifyPlace() {
		// run shingles against the Places gazetteer list
	}

	// ********** COMPLETED USER STORIES **********

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

		// CLI .txt file input

		// CLI generate ARFF training data
		String inputFilePath = "/data/training/trainingData.txt";
		String outputFilePath = "/data/arff/trainingData.arff";

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		inputFilePath = relativePath + "" + inputFilePath;
		outputFilePath = relativePath + "" + outputFilePath;

		Librarian.main(new String[] { "train", inputFilePath, outputFilePath });
	}

	// User Story #861
	// Status: Complete
	// As a Librarian/application developer I want to use Command line to
	// process each block of text separately via the personal name extractor
	@Test
	public void testSeparateNER() throws FileNotFoundException {
		String input = "<NER>Hello, There are snakes on this plane! I don't know what to do!</NER><NER>This should be another text block!</NER>";

		ArrayList<TextBlock> textBlocks = Librarian.separateNER(input);

		// separateNER should have separated the input into two lines
		assertEquals(2, textBlocks.size());
	}

	
	/**
	 *  User Story #850
	 * Status: Complete
	 * As a Librarian, I want the PNE to use a learning machine to
	 * classify tokens within the input.
	 */
	@Test
	public void testGetFeatures() {
		Librarian librarian = new Librarian();
		Trainer trainer = new Trainer();
		LearningMachine learningMachine = new LearningMachine();

		// Change to test_training_data to make building the project faster
		String arffFilePath = "/data/arff/test_training_data.arff";
		// String arffFilePath = "/data/arff/training_data_zeil.arff"; // actual
		// training data

		String input = "Mr. Samuel L. Jackson, Jr.";

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		arffFilePath = relativePath + "" + arffFilePath;

		ArrayList<Token> testTokens = trainer.tokenize(input);

		try {
			learningMachine.importARFF(arffFilePath);
			learningMachine.train();
			learningMachine.printEvaluationSummary();

			for (Token t : testTokens) {
				t = librarian.getFeatures(t);

				if (t.getLexical() != "whiteSpace") {
					if (t.getLexical() == "capitalized") {
						// Since all of the capitalized values in the test input
						// are parts of names, they should all be classified as
						// either 'beginning' or 'continuing'
						assertTrue("beginning" == learningMachine.classify(t.toString())
								|| "continuing" == learningMachine.classify(t.toString()));

					}

					/*
					 * // Uncomment to view Token classification on the console
					 * System.out.println(t.getLexeme() + ": " +
					 * trainer.classify(t.toString()));
					 * 
					 * // Uncomment to view Token distribution on console
					 * double[] distribution =
					 * trainer.getDistribution(t.toString());
					 * System.out.println("beginning: " + distribution[0]);
					 * System.out.println("continuing: " + distribution[1]);
					 * System.out.println("other: " + distribution[2] + "\n");
					 */
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// User Story #856
	// Status - Completed
	// Dictionary features identified correctly (L)
	// User Story #854
	// Status - Completed
	// Misc features (honorifics, kill words, etc) identified correctly. (L)
	@Test
	public void testClassifyToken() {
		Librarian librarian = new Librarian();

		Token token = new Token("Samuel");
		token = librarian.getFeatures(token);

		assertEquals("capitalized,other,1,0,0,1,1,1,1,1,0,0,0,0", token.toString());
	}

	// User Story #1094
	// Status - Completed
	// As a librarian, I want Token Lexical features to be identified correctly.
	@Test
	public void testGetLexicalFeature() {
		Librarian librarian = new Librarian();

		Token t1 = new Token("Samuel");
		Token t2 = new Token("SAMUEL");
		Token t3 = new Token(".");
		Token t4 = new Token("S");

		assertEquals("capitalized", librarian.getLexicalFeature(t1.getLexeme()));
		assertEquals("allCaps", librarian.getLexicalFeature(t2.getLexeme()));
		assertEquals("punct", librarian.getLexicalFeature(t3.getLexeme()));
		assertEquals("capLetter", librarian.getLexicalFeature(t4.getLexeme()));
	}

}