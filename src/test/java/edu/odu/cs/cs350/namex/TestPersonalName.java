/**
 * 
 */
package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.odu.cs.cs350.namex.PersonalName;

/**
 * @author addy Alago User story 854: As a Librarian, I want miscellaneous words
 *         such as honorifics and kill words to be identified correctly.
 */
public class TestPersonalName { 

	/**
	 * Tests default contructor
	 */
	@Test
	public void testPersonalName() {
		
		PersonalName name = new PersonalName();

		assertTrue(name != null);
		assertEquals(name.getName(), "");
		
	}

	@Test
	/**
	 * test for public PersonalName(String givenNameInput, String surNameInput) 
	 */
	public void testPersonalNameFirstLast(){
		PersonalName n1 = new PersonalName("John" , "Doe");
		assertTrue(n1 != null);
		assertEquals("John" , n1.getGivenName());
		assertEquals("Doe" , n1.getSurName());
		//below should remain empty after using the two-string constructor
		assertEquals("" , n1.getHonorific());
		assertEquals("" , n1.getMiddleNames());
		assertEquals("" , n1.getSuffix());
	}
	
	
	/**
	 * test for public PersonalName(String name) e.g. "Cher"
	 */
	@Test
	public void testPersonalNameString() {
		PersonalName n1 = new PersonalName("Cher");
		assertTrue(n1 != null);
		assertEquals("Cher" , n1.getGivenName());
		assertEquals("" , n1.getSurName());
		assertEquals("" , n1.getHonorific());
		assertEquals("" , n1.getMiddleNames());
		assertEquals("" , n1.getSuffix());
		
		//a name constructed by an empty string
		PersonalName empty = new PersonalName("");
		assertTrue(empty != null);
		assertEquals("" , empty.getGivenName());
		assertEquals("" , empty.getSurName());
		assertEquals("" , empty.getHonorific());
		assertEquals("" , empty.getMiddleNames());
		assertEquals("" , empty.getSuffix());
		
	}
	
	@Test
	/**
	 * test for public PersonalName(String honor, String first, String middle, String last, String suff) 
	 */
	public void testPersonalNameFiveString(){
		PersonalName n1 = new PersonalName("Dr" , "Jane" , "X" , "Doe" , "III");
		assertTrue(n1 != null);
		assertEquals("Dr" , n1.getHonorific());
		assertEquals("Jane" , n1.getGivenName());
		assertEquals("X" , n1.getMiddleNames());
		assertEquals("Doe" , n1.getSurName());
		assertEquals("III" , n1.getSuffix());
		
		//a name with empty middlename
		PersonalName n2 = new PersonalName("Dr" , "Jane" , "" , "Doe" , "III");
		assertTrue(n2 != null);
		assertEquals("Dr" , n2.getHonorific());
		assertEquals("Jane" , n2.getGivenName());
		assertEquals("" , n2.getMiddleNames());
		assertEquals("Doe" , n2.getSurName());
		assertEquals("III" , n2.getSuffix());
		
		//a name with no prefix;honorific and no middle
		PersonalName n3 = new PersonalName("" , "Jane" , "" , "Doe" , "III");
		assertTrue(n3 != null);
		assertEquals("" , n3.getHonorific());
		assertEquals("Jane" , n3.getGivenName());
		assertEquals("" , n3.getMiddleNames());
		assertEquals("Doe" , n3.getSurName());
		assertEquals("III" , n3.getSuffix());
		
		//name with no suffix
		PersonalName n4 = new PersonalName("" , "Jane" , "Marie" , "Doe" , "");
		assertTrue(n4 != null);
		assertEquals("" , n4.getHonorific());
		assertEquals("Jane" , n4.getGivenName());
		assertEquals("Marie" , n4.getMiddleNames());
		assertEquals("Doe" , n4.getSurName());
		assertEquals("" , n4.getSuffix());
	}

	/**
	 * Test for public String getName() 
	 */
	@Test
	public void testGetName() {
		// default constructor
		PersonalName name = new PersonalName();
		
		//all five member variables filled
		PersonalName fullname = new PersonalName("Mr.", "Addy", "Alberto Alonzo", "Alago", "Jr.");
		
		//only given name 
		PersonalName firstname = new PersonalName("Cherry");
		
		//first and last name
		PersonalName firstlast = new PersonalName("Taylor","Swift");
		
		assertEquals("" , name.getName());
		assertEquals("Mr. Addy Alberto Alonzo Alago Jr." , fullname.getName());
		assertEquals("Cherry", firstname.getName());
		assertEquals("Taylor Swift", firstlast.getName());

	}

	/**
	 * Test for public String getHonorific()
	 */
	@Test
	public void testGetHonorific() {

		PersonalName def = new PersonalName();
		PersonalName n1 = new PersonalName("Mrs.","Jane","","Whiskers","");
		PersonalName n2 = new PersonalName(" ","Jane","","Whiskers","");
		
		assertEquals("" , def.getHonorific());
		assertEquals("Mrs.", n1.getHonorific());
		assertEquals(" ", n2.getHonorific());
	}

	/**
	 * Test for public String getGivenName()
	 */
	@Test
	public void testGetGivenName() {
		PersonalName name = new PersonalName();
		
		PersonalName name1 = new PersonalName("Mr.", "Addy", "Alberto Alonzo", "Alago", "Jr.");

		PersonalName name4 = new PersonalName("Addy", "Alago");

		assertEquals("" , name.getGivenName());
		assertEquals("Addy", name1.getGivenName());
		assertEquals("Addy" , name4.getGivenName());
	}

	/**
	 * Test for public String getMiddleNames()
	 */
	@Test
	public void testGetMiddleNames() {
		PersonalName n1 = new PersonalName();
		PersonalName n2 = new PersonalName("Melody");
		PersonalName n3 = new PersonalName("","Nicole","Brown","Simpson","");
		PersonalName n4 = new PersonalName("Dr.","Steven","","Zeil","");
		
		assertEquals("",n1.getMiddleNames());
		assertEquals("",n2.getMiddleNames());
		assertEquals("Brown",n3.getMiddleNames());
		assertEquals("",n4.getMiddleNames());
	}

	/**
	 * Test for public String getSurName()
	 */
	@Test
	public void testGetSurName() {
		PersonalName n1 = new PersonalName();
		PersonalName n2 = new PersonalName("Melody");
		PersonalName n3 = new PersonalName("","Nicole","Brown","Simpson","");
		PersonalName n4 = new PersonalName("Dr.","Steven","","Zeil","");
		PersonalName n5 = new PersonalName("Ms.","Elle","","","");
		
		assertEquals("" , n1.getSurName());
		assertEquals("" , n2.getSurName());
		assertEquals("Simpson", n3.getSurName());
		assertEquals("Zeil", n4.getSurName());
		assertEquals("", n5.getSurName());
	}

	/**
	 * Test for	public String getSuffix()
	 */
	@Test
	public void testGetSuffix() {
		PersonalName n1 = new PersonalName();
		PersonalName n2 = new PersonalName("Melody");
		PersonalName n3 = new PersonalName("","Nicole","Brown","Simpson","");
		PersonalName n4 = new PersonalName("Dr.","Steven","","Zeil","");
		PersonalName n5 = new PersonalName("Ms.","Elle","","","");
		PersonalName n6 = new PersonalName("Queen","Elizabeth","","","III");
		
		assertEquals("",n1.getSuffix());
		assertEquals("",n2.getSuffix());
		assertEquals("",n3.getSuffix());
		assertEquals("",n4.getSuffix());
		assertEquals("",n5.getSuffix());
		assertEquals("III",n6.getSuffix());
	}

}
