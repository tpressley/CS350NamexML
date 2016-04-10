package edu.odu.cs.cs350.namex;

//import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.odu.cs.cs350.namex.Librarian;

//import edu.odu.cs.cs350.Librarian;

public class TestLibrarian {

	// prints the weka classification of each token from an input string
	@Test
	public void testWeka() {
		// ********** Configurations **************

		String arffFilePath = "/data/arff/training_data_zeil.arff"; // actual
																	// training
																	// data
		String input = "Mr. Samuel L. Jackson, Jr.";
		boolean printEvaluationSummary = true;
		boolean showClassification = true;
		boolean showDistribution = false;

		// ********** End Configurations **********

		Librarian librarian = new Librarian();
		Trainer trainer = new Trainer();

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		arffFilePath = relativePath + "" + arffFilePath;

		ArrayList<Token> testTokens = trainer.tokenize(input);

		try {
			trainer.importARFF(arffFilePath);
			trainer.trainLM();

			if (printEvaluationSummary == true) {
				trainer.printEvaluationSummary();
			}

			for (Token t : testTokens) {
				t = librarian.classifyToken(t);

				if (t.getLexical() != "whiteSpace") {
					if (showClassification == true) {
						System.out.println("lexeme:           " + t.getLexeme());
						System.out.println("classification:   " + trainer.classify(t.toString()));

						if (showDistribution == false) {
							System.out.println();
						}
					}
					if (showDistribution == true) {
						double[] distribution = trainer.getDistribution(t.toString());
						System.out.println("beginning:        " + Math.round(distribution[0] * 100.00) + "%");
						System.out.println("continuing:       " + Math.round(distribution[1] * 100.00) + "%");
						System.out.println("other:            " + Math.round(distribution[2] * 100.00) + "%\n");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	// User Story #861 - Gerard Silverio
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

	// User Story #850
	// Status: Complete
	// As a Librarian, I want the PNE to use a learning machine to
	// classify tokens within the input.
	@Test
	public void testClassify() {
		Librarian librarian = new Librarian();
		Trainer trainer = new Trainer();

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
			trainer.importARFF(arffFilePath);
			trainer.trainLM();
			trainer.printEvaluationSummary();

			for (Token t : testTokens) {
				t = librarian.classifyToken(t);

				if (t.getLexical() != "whiteSpace") {
					if (t.getLexical() == "capitalized") {
						// Since all of the capitalized values in the test input
						// are parts of names, they should all be classified as
						// either 'beginning' or 'continuing'
						assertTrue("beginning" == trainer.classify(t.toString())
								|| "continuing" == trainer.classify(t.toString()));

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testClassifyTokens() {
		Librarian l = new Librarian();
		Trainer trainer = new Trainer();

		// System.out.println(l.isCommonLastName("Smith"));

		String input = "<NER>Hello <PER>John Smith</PER> this is <PER>Mr. Samuel L. Jackson, III</PER></NER>";

		ArrayList<TextBlock> textBlocks = l.separateNER(input);

		for (TextBlock textBlock : textBlocks) {
			System.out.println(textBlock.getTextBlock());

			ArrayList<Token> tokens = trainer.tokenize(textBlock.getTextBlock());

			/*
			 * for (Token token : tokens) { token = l.classifyToken(token);
			 * System.out.println(token.toStringQuotes()); }
			 */

			HashSet<Token> classifiedTokens = l.classifyTokens(tokens);

			for (Token token : classifiedTokens) {
				System.out.println(token.toStringQuotes());
			}
		}

	}

	// PNE packaged for deployment in fat jar (A)
	@Test
	public void testPackage() {
		// is it possible to even create a JUnit test to see if the PNE was
		// packaged in a fat jar?
	}

	// User Story #852 As a Librarian, I want names of places to be identified
	// correctly
	/*
	 * @Test public void testNameOfPlace() { // "George Washington Bridge";
	 * ArrayList<String> place1 = new ArrayList<String>(); String plc1 =
	 * "George Washington Bridge"; place1.add("George");
	 * place1.add("Washington"); place1.add("Bridge");
	 * 
	 * // "Washington State"; ArrayList<String> place2 = new
	 * ArrayList<String>(); String plc2 = "Washington State";
	 * place2.add("Washington"); place2.add("State");
	 * 
	 * // "James Madison University"; ArrayList<String> place3 = new
	 * ArrayList<String>(); String plc3 = "James Madison University";
	 * place3.add("James"); place3.add("Madison"); place3.add("University");
	 * 
	 * // "" empty string ArrayList<String> place4 = new ArrayList<String>();
	 * String plc4 = ""; place4.add("");
	 * 
	 * // "George Washington"; ArrayList<String> name1 = new
	 * ArrayList<String>(); String n1 = "George Washington";
	 * name1.add("George"); name1.add("Washington");
	 * 
	 * // "James Madison"; ArrayList<String> name2 = new ArrayList<String>();
	 * String n2 = "James Madison"; name2.add("James"); name2.add("Madison");
	 * 
	 * Librarian lib; try { lib = new Librarian(); assertEquals(
	 * "George Washington Bridge/Plc", (lib.nameOfPlace(plc1)).toString() );
	 * assertEquals(plc1, lib.nameOfPlace(plc1)); assertEquals(
	 * "Washington State/Plc", lib.nameOfPlace(plc2) ); assertEquals(
	 * "James Madison University/Plc", lib.nameOfPlace(plc3) ); assertEquals("",
	 * lib.nameOfPlace(plc4));
	 * 
	 * assertEquals(name1, lib.nameOfPlace(n1)); assertEquals(name2,
	 * lib.nameOfPlace(n2)); } catch (FileNotFoundException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */

	// User Story #843 As a Librarian/application developer, I want text
	// identified as names marked with <PER> tags
	/*
	 * @Test
	 * 
	 * public void testMarkPERtag() {
	 * 
	 * Librarian lib; try { lib = new Librarian();
	 * 
	 * String text1 = "Hello, my name is John Doe."; String text2 =
	 * "This paper was written by Pythagoras."; String text3 =
	 * "Queen Elizabeth II authored this book."; String text4 =
	 * "written by Martin Luther King Jr.";
	 * 
	 * assertEquals("<PER>John Doe</PER>", lib.markPERtag(text1));
	 * assertEquals("<PER>Pythagoras</PER>", lib.markPERtag(text2));
	 * assertEquals("<PER>Queen Elizabeth II</PER>", lib.markPERtag(text3));
	 * assertEquals("<PER>Martin Luther King Jr.</PER>", lib.markPERtag(text4));
	 * 
	 * String text5 = "The sky is blue today."; // no personal name here
	 * assertEquals(text5, lib.markPERtag(text5));
	 * 
	 * String text6 = ""; // empty string assertEquals("",
	 * lib.markPERtag(text6)); } catch (FileNotFoundException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */

	@Test
	/*
	 * // Caroline public void testMarkNERtag() {
	 * 
	 * Librarian lib; try {
	 * 
	 * lib = new Librarian();
	 * 
	 * String text1 = "Hello, my name is John Doe."; String text2 =
	 * "This paper was written by Pythagoras."; // String text3 =
	 * "Queen Elizabeth II authored this book."; // String text4 =
	 * "written by Martin Luther King Jr."; ArrayList<String> t1 = new
	 * ArrayList<String>(); // t1 = lib.markNERtag(text1); ArrayList<String> t2
	 * = new ArrayList<String>(); // t2 = lib.markNERtag(text2);
	 * 
	 * assertEquals("<NER>", t1.get(0)); System.out.println("t1.get(0) = " +
	 * t1.get(0)); assertEquals("</NER>", t1.get(t1.size() - 1));
	 * System.out.println("t1.get(end) = " + t1.get(t1.size() - 1));
	 * assertEquals("Hello", t1.get(1));
	 * 
	 * for (int i = 0; i < t1.size(); i++) { System.out.println("t1.get(" + i +
	 * ") = " + t1.get(i)); } System.out.println(t1.toString());
	 * 
	 * for (int i = 0; i < t2.size(); i++) { System.out.println("t2.get(" + i +
	 * ") = " + t2.get(i)); }
	 * 
	 * // assertEquals("<NER>" , t2.get(0)); // assertEquals("</NER>" ,
	 * t2.get(t1.size()-1));
	 * 
	 * // assertEquals("<NER>This paper was written by Pythagoras.</NER>", //
	 * (lib.markNERtag(text2)).toString() ); // assertEquals(
	 * "<NER>Queen Elizabeth II authored this book.</NER>", //
	 * (lib.markNERtag(text3)).toString()); // assertEquals(
	 * "<NER>written by Martin Luther King Jr.</NER>", //
	 * (lib.markNERtag(text4)).toString());
	 * 
	 * // String text5 = "The sky is blue today."; // String text5marked =
	 * "<NER>The sky is blue today.</NER>"; // assertEquals(text5marked,
	 * (lib.markNERtag(text5)).toString());
	 * 
	 * // String text6 = "<NER>The sky is blue today.</NER>"; //
	 * assertEquals(text6, (lib.markNERtag("The sky is blue //
	 * today.")).toString()); } catch (FileNotFoundException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */

	public void testMarkClassificationTag() {

		Librarian lib = new Librarian();

		String text1 = "This small paper was written by Pythagoras.";

		assertEquals("This <ADJ>small</ADJ> paper was <VERB>written</VERB> by <NOUN>Pythagoras</NOUN>.",
				lib.markClassificationTag(text1));
	}

}