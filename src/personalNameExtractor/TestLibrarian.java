package personalNameExtractor;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
		String input1 = "<NER>Hello Gerard Silverio</NER>";

		ByteArrayInputStream input = new ByteArrayInputStream(input1.getBytes());
		System.setIn(input);

		Scanner scan = new Scanner(System.in);

		Librarian librarian = new Librarian();
		assertEquals("Gerard Silverio", librarian.extractPersonalNames(scan.nextLine()));
	}

	// PNE packaged for deployment in fat jar (A)
	@Test
	public void testPackage() {
		// is it possible to even create a JUnit test to see if the PNE was
		// packaged in a fat jar?
	}
	
	
	
	//User Story #852 As a Librarian, I want names of places to be identified correctly
	@Test
	public void testNamesOfPlaces() {
		
		arraylist<String> place1 = "George Washington Bridge";
		arraylist<String> place2 = "Washington State";
		arraylist<String> place3 = "James Madison University";
		arraylist<String> place4 = "";
		
		arraylist<String> name1 = "George Washington";
		arraylist<String> name2 = "James Madison";
		
		Librarian lib = new Librarian();
		
		assertEquals( "George Washington Bridge/Plc" , lib.nameOfPlace(place1) );
		assertEquals( "Washington State/Plc" , lib.nameOfPlace(place2) );
		assertEquals( "James Madison University/Plc", lib.nameOfPlace(place3) );
		assertEquals("", lib.nameOfPlace(place4));
		
		assertEquals(name1, lib.nameOfPlace(name1));
		assertEquals(name2, lib.nameOfPlace(name2));
		
	}


}
