package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

//import java.nio.file.Path;
//import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class TestLearningMachine {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	/**
	 * test for Librarian constructor
	 */
	public void testLibrarian(){
		
		Librarian cathy = new Librarian();
		//cathy is not null
		assertTrue(cathy != null);
		
		//cathy has working lm and trainer
		assertFalse(cathy.lm == null);
		assertTrue(cathy.trainer != null);
		
	}
	
}

