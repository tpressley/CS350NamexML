package personalNameExtractor;

//import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

public class TestTrainer {

	/**
	 * Tests to see if the function properly tokenizes the output by comparing
	 * to the output when using StringTokenizer
	 */
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

	/**
	 * The parsing should have the same output as the Stanford Natural Language
	 * parser
	 */
	@Test
	public void testParse() {
		Trainer trainer = new Trainer();
		assertEquals(
				"The/DT strongest/JJS rain/NN ever/RB recorded/VBN in/IN India/NNP shut/VBD down/RP the/DT financial/JJ hub/NN of/IN Mumbai/NNP ,/, snapped/VBD communication/NN lines/NNS ,/, closed/VBD airports/NNS and/CC forced/VBD thousands/NNS of/IN people/NNS to/TO sleep/VB in/IN their/PRP$ offices/NNS or/CC walk/VB home/NN during/IN the/DT night/NN ,/, officials/NNS said/VBD today/NN ./. ",
				trainer.parse(
						"The strongest rain ever recorded in India shut down the financial hub of Mumbai, snapped communication lines, closed airports and forced thousands of people to sleep in their offices or walk home during the night, officials said today."));
	}

	/**
	 * The total token count for any token should be equal to the number of
	 * times that token appears in the input
	 */
	@Test
	public void testGetTokenCount() {
		Trainer trainer = new Trainer();
		trainer.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>");
		assertEquals(3, trainer.getTokenCount(","));
	}

	/**
	 * The trainer should probably collect training materials upon creation
	 */
	@Test
	public void testTrainer() {
		Trainer trainer = new Trainer();
		assertTrue(trainer.getTrainingMaterials() != null);
	}

}
