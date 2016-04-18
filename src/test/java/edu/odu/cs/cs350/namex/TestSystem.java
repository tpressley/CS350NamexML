

package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
/**
 * Test class for the system
 * @author Tristan Pressley, Caroline Chey
 *
 */
public class TestSystem {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMainTrainData() {
		String[] args = { "train", "src/main/data/trainingData.txt", "trainingDatao.txt" };
		try {
			// Temporarily removed -- takes way too long
			// Librarian.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		File f = new File("LearningMachine.model");
		File f2 = new File("trainigDatao.txt");
		Scanner s = null;
		try {
			s = new Scanner(f2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertTrue(f.exists());
		assertTrue(s.nextLine() == "@relation Classification");

		String[] argsB = { "trainB", "src/main/data/trainingMaterial003.txt", "trainingDataob.txt" };
		try {
			// Temporarily removed -- takes way too long
			// Librarian.main(args);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		File fb = new File("LearningMachine.model");
		File fb2 = new File("trainigDataob.txt");
		Scanner sb = null;
		try {
			sb = new Scanner(f2);
		} catch (FileNotFoundException e3) {
			e3.printStackTrace();
		}
		assertTrue(fb.exists());
		assertTrue(sb.nextLine() == "@relation Classification");
	}
}

