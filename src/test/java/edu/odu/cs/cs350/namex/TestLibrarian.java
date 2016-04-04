package edu.odu.cs.cs350.namex;

//import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import edu.odu.cs.cs350.namex.Librarian;

//import edu.odu.cs.cs350.Librarian;

public class TestLibrarian {

	// User Story #844 - Gerard Silverio
	// As a Librarian/application developer, I want a program that will
	// accept standard input from command line interface
	// User Story #845
	// As a Librarian/application developer, I want a program that will
	// send output to standard command line interface
	@Test
	public void testMain() throws FileNotFoundException {
		Librarian.main(new String[] {
				"<NER>Hello, There are snakes on this plane! I don't know what to do!</NER><NER>Hello World line 2</NER><NER>Goodbye world!</NER>" });
		// Librarian.main(new String[] {"inputBlocks.txt",
		// "markedUpOutput.txt"});
	}

	// User Story #861 - Gerard Silverio
	// As a Librarian/application developer I want to use Command line to
	// process each block of text separately via the personal name extractor
	@Test
	public void testSeparateNER() throws FileNotFoundException {
		Librarian librarian = new Librarian();

		String input = "<NER>Hello, There are snakes on this plane! I don't know what to do!</NER><NER>This should be another text block!</NER>";
		String[] assumedTextBlocks = { "<NER>Hello, There are snakes on this plane! I don't know what to do!</NER>",
				"<NER>This should be another text block!</NER>" };

		ArrayList<TextBlock> textBlocks = librarian.separateNER(input);

		for (TextBlock textBlock : textBlocks) {
			textBlock.setTokens(librarian.tokenize(textBlock.getTextBlock()));
		}

		// Prints the size of the List of textBlocks
		// System.out.println(textBlocks.size());

		// Test output of separateNER method
		for (int i = 0; i < textBlocks.size(); i++) {
			assertEquals(textBlocks.get(i).toString(), assumedTextBlocks[i]);

			boolean matches = ((textBlocks.get(i).toString()).equals(assumedTextBlocks[i]));
			System.out.println(
					i + ": " + matches + " [" + textBlocks.get(i).toString() + " - " + assumedTextBlocks[i] + "]");
			// System.out.println(textBlocks.get(i));
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
	@Test
	public void testNameOfPlace() {
		// "George Washington Bridge";
		ArrayList<String> place1 = new ArrayList<String>();
		String plc1 = "George Washington Bridge";
		place1.add("George");
		place1.add("Washington");
		place1.add("Bridge");

		// "Washington State";
		ArrayList<String> place2 = new ArrayList<String>();
		String plc2 = "Washington State";
		place2.add("Washington");
		place2.add("State");

		// "James Madison University";
		ArrayList<String> place3 = new ArrayList<String>();
		String plc3 = "James Madison University";
		place3.add("James");
		place3.add("Madison");
		place3.add("University");

		// "" empty string
		ArrayList<String> place4 = new ArrayList<String>();
		String plc4 = "";
		place4.add("");

		// "George Washington";
		ArrayList<String> name1 = new ArrayList<String>();
		String n1 = "George Washington";
		name1.add("George");
		name1.add("Washington");

		// "James Madison";
		ArrayList<String> name2 = new ArrayList<String>();
		String n2 = "James Madison";
		name2.add("James");
		name2.add("Madison");

		Librarian lib;
		try {
			lib = new Librarian();
			assertEquals("George Washington Bridge/Plc", (lib.nameOfPlace(plc1)).toString() );
			assertEquals(plc1, lib.nameOfPlace(plc1));
			assertEquals("Washington State/Plc", lib.nameOfPlace(plc2) );
			assertEquals("James Madison University/Plc", lib.nameOfPlace(plc3) );
			assertEquals("", lib.nameOfPlace(plc4));

			assertEquals(name1, lib.nameOfPlace(n1));
			assertEquals(name2, lib.nameOfPlace(n2));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// User Story #843 As a Librarian/application developer, I want text
	// identified as names marked with <PER> tags
	@Test
	public void testMarkPERtag() {

		Librarian lib;
		try {
			lib = new Librarian();

			String text1 = "Hello, my name is John Doe.";
			String text2 = "This paper was written by Pythagoras.";
			String text3 = "Queen Elizabeth II authored this book.";
			String text4 = "written by Martin Luther King Jr.";

			assertEquals("<PER>John Doe</PER>", lib.markPERtag(text1));
			assertEquals("<PER>Pythagoras</PER>", lib.markPERtag(text2));
			assertEquals("<PER>Queen Elizabeth II</PER>", lib.markPERtag(text3));
			assertEquals("<PER>Martin Luther King Jr.</PER>", lib.markPERtag(text4));

			String text5 = "The sky is blue today."; // no personal name here
			assertEquals(text5, lib.markPERtag(text5));

			String text6 = ""; // empty string
			assertEquals("", lib.markPERtag(text6));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testMarkNERtag() {

		Librarian lib;
		try {
			lib = new Librarian();

			String text1 = "Hello, my name is John Doe.";
			String text2 = "This paper was written by Pythagoras.";
			String text3 = "Queen Elizabeth II authored this book.";
			String text4 = "written by Martin Luther King Jr.";

			assertEquals("<NER>Hello, my name is John Doe.</NER>", (lib.markNERtag(text1)).toString());
			assertEquals("<NER>This paper was written by Pythagoras.</NER>", (lib.markNERtag(text2)).toString());
			assertEquals("<NER>Queen Elizabeth II authored this book.</NER>", (lib.markNERtag(text3)).toString());
			assertEquals("<NER>written by Martin Luther King Jr.</NER>", (lib.markNERtag(text4)).toString());

			String text5 = "The sky is blue today."; 
			String text5marked = "<NER>The sky is blue today.</NER>";
			assertEquals(text5marked, (lib.markNERtag(text5)).toString());

			String text6 = "<NER>The sky is blue today.</NER>"; 
			assertEquals(text6, (lib.markNERtag("The sky is blue today.")).toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testMarkClassificationTag() {

		Librarian lib;
		try {
			lib = new Librarian();

			String text1 = "This small paper was written by Pythagoras.";

			assertEquals("This <ADJ>small</ADJ> paper was <VERB>written</VERB> by <NOUN>Pythagoras</NOUN>.",
					lib.markClassificationTag(text1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}