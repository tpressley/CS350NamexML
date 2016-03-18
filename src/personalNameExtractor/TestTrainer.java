package personalNameExtractor;
//import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

public class TestTrainer {

	@Test
	public void testTokenize() {
		ArrayList<String> tokenizedOutput = new ArrayList();
		StringTokenizer st = new StringTokenizer(
				"\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"",
				",.:;\'\"/?[]{}\\|=-!@#$%^&*()_+ ");

		while (st.hasMoreElements()) {
			tokenizedOutput.add(st.nextToken());
		}

		Trainer trainer = new Trainer();
		assertEquals(tokenizedOutput, trainer.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>"));
	}

	@Test
	public void testParse() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTokenCount() {
		Trainer trainer = new Trainer();
		trainer.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>");
		assertEquals(3, trainer.getTokenCount(","));
	}

	@Test
	public void testTrainer() {
		fail("Not yet implemented");
	}
	
	
	// User Story #851 As an Trainer, I want the program to properly prepare
	// data to train the learning machine.
	@Test
	public void testPrepareData() {

	}

	
	
	

}
