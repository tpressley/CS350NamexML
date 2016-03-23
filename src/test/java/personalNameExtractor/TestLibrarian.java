package personalNameExtractor;

//import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import edu.odu.cs.cs350.Librarian;

public class TestLibrarian {

	// As a Librarian/application developer, I want a program that will accept
	// standard
	// input from command line interface
	@Test
	public void testStandardInput() {
		String inputString = "Mr. Gerard Silverio, Jr.";

		ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());
		System.setIn(input);

		Scanner scan = new Scanner(System.in);
		assertEquals("Mr. Gerard Silverio, Jr.", scan.nextLine());
	}

	// As a Librarian/application developer, I want a program that will send
	// output to
	// standard command line interface
	@Test
	public void testStandardOutput() {
		String outputString = "<PER>Mr. Gerard Silverio, Jr.</PER>";

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		System.out.print(outputString);
		assertEquals("<PER>Mr. Gerard Silverio, Jr.</PER>", outputStream.toString());
	}

	// As a Librarian/application developer I want to use Command line to
	// process each
	// block of text separately via the personal name extractor
	// CLI processes each <NER> block separately via Extractor interface. (A,
	// maybe L)
	@Test
	public void testProcessText() {
		String input1 = "<NER>Hello Gerard Silverio</NER><NER>Hello world! My name is Addy Alago</NER>";

		ByteArrayInputStream input = new ByteArrayInputStream(input1.getBytes());
		System.setIn(input);

		Scanner scan = new Scanner(System.in);

		Librarian librarian = new Librarian();

		ArrayList<String> names = librarian.extractPersonalNames(scan.nextLine());

		assertEquals("Gerard Silverio", names.get(1));
		assertEquals("Addy Alago", names.get(2));
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
		place1.add("George");
		place1.add("Washington");
		place1.add("Bridge");

		// "Washington State";
		ArrayList<String> place2 = new ArrayList<String>();
		place2.add("Washington");
		place2.add("State");

		// "James Madison University";
		ArrayList<String> place3 = new ArrayList<String>();
		place3.add("James");
		place3.add("Madison");
		place3.add("University");

		// "" empty string
		ArrayList<String> place4 = new ArrayList<String>();
		place4.add("");

		// "George Washington";
		ArrayList<String> name1 = new ArrayList<String>();
		name1.add("George");
		name1.add("Washington");

		// "James Madison";
		ArrayList<String> name2 = new ArrayList<String>();
		name2.add("James");
		name2.add("Madison");

		Librarian lib = new Librarian();

		assertEquals("George Washington Bridge/Plc", lib.nameOfPlace(place1.toString()));
		assertEquals("Washington State/Plc", lib.nameOfPlace(place2.toString()));
		assertEquals("James Madison University/Plc", lib.nameOfPlace(place3.toString()));
		assertEquals("", lib.nameOfPlace(place4.toString()));

		assertEquals(name1, lib.nameOfPlace(name1.toString()));
		assertEquals(name2, lib.nameOfPlace(name2.toString()));

	}

	// User Story #843 As a Librarian/application developer, I want text
	// identified as names marked with <PER> tags
	@Test
	public void testMarkPERtag() {

		Librarian lib = new Librarian();

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
	}

	
	@Test
	public void testMarkNERtag() {

		Librarian lib = new Librarian();

		String text1 = "Hello, my name is John Doe.";
		String text2 = "This paper was written by Pythagoras.";
		String text3 = "Queen Elizabeth II authored this book.";
		String text4 = "written by Martin Luther King Jr.";

		assertEquals("<NER>Hello, my name is John Doe.</NER>", lib.markNERtag(text1));
		assertEquals("<NER>This paper was written by Pythagoras.</NER>", lib.markNERtag(text2));
		assertEquals("<NER>Queen Elizabeth II authored this book.</NER>", lib.markNERtag(text3));
		assertEquals("<NER>written by Martin Luther King Jr.</NER>", lib.markNERtag(text4));

		String text5 = "The sky is blue today."; // no personal name here
		assertEquals(text5, lib.markNERtag(text5));

		String text6 = "<NER>The sky is blue today.</NER>"; // empty string
		assertEquals(text6, lib.markNERtag(text6));
	}

	public void testMarkClassificationtag() {

		Librarian lib = new Librarian();

		String text1 = "This small paper was written by Pythagoras.";

		assertEquals("This <ADJ>small</ADJ> paper was <VERB>written</VERB> by <NOUN>Pythagoras</NOUN>.",
				lib.markClassificationtag(text1));

	}

}
