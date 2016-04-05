package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class TestExtractor {

	// As a Librarian/application developer I want to use Command line to
	// process each
	// block of text separately via the personal name extractor
	// CLI processes each <NER> block separately via Extractor interface. (A,
	// maybe L)
	@Test
	public void testProcessText() {

		ArrayList<String> inputs = new ArrayList<String>();
		ArrayList<String> outputs = new ArrayList<String>();

		// add sample inputs
		inputs.add("<NER>Hello Gerard Silverio</NER><NER>Hello world! My name is Addy Alago</NER>"); // first
																										// last
		inputs.add("<NER>Name Extraction -- Requirements Definition\nSteven J Zeil\nJan 20, 2016</NER>"); // first
																											// middle
																											// initial
																											// last
		inputs.add("<NER>Name Extraction -- Requirements Definition\nSteven J. Zeil\nJan 20, 2016</NER>"); // first
																											// middle
																											// initial
																											// period
																											// last
		inputs.add("<NER>Why is this test so cool Jack James Jameson?</NER>"); // first
																				// middle
																				// last
		inputs.add("<NER>This is Dr. James F. Brown reporting for duty.</NER>"); // honorific
		inputs.add("<NER>I don't know why Senator John Smith is running for president.</NER>"); // honorific
		inputs.add("<NER>I went to George Washington University and studied Computer Science.</NER>"); // test
																										// kill
																										// word
																										// doesn't
																										// match
		inputs.add("<NER>You should go build a campfire Gary J. Jones, Jr.</NER>"); // last
																					// name
																					// suffix
		inputs.add("<NER>You should go build a campfire Gary J. Jones, III</NER>"); // last
																					// name
																					// suffix
		inputs.add("<NER>At school, James von Dutch was my Computer Science teacher.</NER>"); // last
																								// name
																								// prefix
		inputs.add("<NER>I have never read any books written by Mark Twain or Jane Austen.</NER>"); // DTIC
																									// authors

		// add pre-marked outputs to assert input text blocks
		outputs.add(
				"<NER>Hello <PER>Gerard Silverio</PER></NER><NER>Hello world! My name is <PER>Addy Alago</PER></NER>"); // first
																														// last
		outputs.add("<NER>Name Extraction -- Requirements Definition\n<PER>Steven J Zeil</PER>\nJan 20, 2016</NER>"); // first
																														// middle
																														// initial
																														// last
		outputs.add("<NER>Name Extraction -- Requirements Definition\nSteven J. Zeil\nJan 20, 2016</NER>"); // first
																											// middle
																											// initial
																											// period
																											// last
		outputs.add("<NER>Why is this test so cool <PER>Jack James Jameson</PER>?</NER>"); // first
																							// middle
																							// last
		outputs.add("<NER>This is <PER>Dr. James F. Brown<PER> reporting for duty.</NER>"); // honorific
		outputs.add("<NER>I don't know why <PER>Senator John Smith</PER> is running for president.</NER>"); // honorific
		outputs.add("<NER>I went to George Washington University and studied Computer Science.</NER>"); // test
																										// kill
																										// word
																										// doesn't
																										// match
		outputs.add("<NER>You should go build a campfire <PER>Gary J. Jones, Jr.</PER></NER>"); // last
																								// name
																								// suffix
		outputs.add("<NER>You should go build a campfire <PER>Gary J. Jones, III</PER></NER>"); // last
																								// name
																								// suffix
		outputs.add("<NER>At school, <PER>James von Dutch</PER> was my Computer Science teacher.</NER>"); // last
																											// name
																											// prefix
		inputs.add("<NER>I have never read any books written by <PER>Mark Twain</PER> or <PER>Jane Austen</PER.</NER>"); // DTIC
																															// authors

		Scanner scan = new Scanner(System.in);
		Extractor extractor = new Extractor();

		for (int i = 1; i <= inputs.size(); i++) {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(inputs.get(i).getBytes());
			System.setIn(inputStream);
			extractor.extractPersonalNames(scan.nextLine());

			assertEquals(outputs.get(i), extractor.extractPersonalNames(scan.nextLine()));
		}

		scan.close();

	}

	@Test
	public void testSeparateNER() {
		Extractor e = new Extractor();

		String textBlock = "<NER>Hello, There are snakes on this plan! I don't know what to do!</NER><NER>This should be another text block Samuel L. Jackson!</NER>";

		String[] textBlocks = e.separateNER(textBlock);

		// Test output of separateNER method
		for (int i = 0; i < textBlocks.length; i++) {
			// assertEquals(tokens[i], assumedTokens[i]);
			System.out.println(textBlocks[i]);
		}
	}

	@Test
	public void testTokenizer() {

		Extractor e = new Extractor();

		String textBlock = "Hello, I am doing homework.\n I don't know what to do!";

		String[] assumedTokens = { "Hello", ",", " ", "I", " ", "am", " ", "doing", " ", "homework", ".", "\n", " ",
				"I", " ", "don't", " ", "know", " ", "what", " ", "to", " ", "do", "!" };

		String[] tokens = e.tokenize(textBlock);

		// Display the lengths of each array
		System.out.println("tokens: " + tokens.length);
		System.out.println("assumedTokens: " + assumedTokens.length);

		// Display the tokens in the tokens array
		/*
		 * int count = 0; for (String token : tokens) { System.out.println(count
		 * + ": " + token); count++; }
		 */

		// Test output of tokenize method
		for (int i = 0; i < tokens.length; i++) {
			assertEquals(tokens[i], assumedTokens[i]);
			System.out.println(
					i + ": " + (tokens[i].equals(assumedTokens[i])) + " (" + tokens[i] + "-" + assumedTokens[i] + ")");
		}

	}

	@Test
	public void testIsLexicalFeature() {

		Extractor e = new Extractor();

		String textBlock = "!?.,:;()-\"";

		String[] tokens = e.tokenize(textBlock);
		String[] assumedTokens = { "", "!", "?", ".", ",", ":", ";", "(", ")", "-", "\"" };

		for (int i = 1; i < tokens.length; i++) {
			// Test Output
			if (e.isLexicalFeature(tokens[i])) {
				System.out.println(i + ": true - " + tokens[i]);
			} else {
				System.out.println(i + ": false - " + tokens[i]);
			}

			assertEquals(true, e.isLexicalFeature(tokens[i]));
		}

	}

}
