package personalNameExtractor;

//import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

public class TestTrainer {

	/**
	 * User story #860
	 * Tests to see if the function properly tokenizes the output by comparing
	 * to the output when using StringTokenizer
	 */
	@Test
	public void testTokenize() {
		ArrayList<String> tokenizedOutput = new ArrayList();
		Scanner scanner = new Scanner(new BufferedReader(new StringReader("<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>")));

		scanner.useDelimiter("\\s|(?=\\p{Punct})");
		while (scanner.hasNext()) {
				tokenizedOutput.add(scanner.next());
		}


		Trainer trainer = new Trainer();
		assertEquals(tokenizedOutput, trainer.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>"));
	}

	/**
	 * User story #859
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
	 * User story #858, #857
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
		assertTrue(trainer.getTrainingData() != null);
	}
	
	
	//User Story #851 As a Trainer, I want the program to properly prepare data to train the learning machine.
	@Test
	public void testGetTrainingData() {
		
		Trainer t1 = new Trainer();
		assertTrue(t1.getTrainingData() != null);
		
		Trainer t2 = new Trainer();
		assertTrue(t2.getTrainingData() != null);
		
		String in = "this is a test.";
		
		assertEquals(t1.prepareData(in) , t2.prepareData(in));
	}

	
	//User Story #853 As Trainer, I want to use existing data to train the learning machine
	@Test
	public void testTrainLM() {
		
		Trainer t1 = new Trainer();
		Trainer t2 = new Trainer();
		Librarian lib = new Librarian();
		
		String s = "We were crossing the George Washington Bridge.";
		String ss = "It was an honor to be accepted to George Mason University.";
		String sss = "Dr.Carson wrote this book.";
		
		assertTrue(t1.trainLM(s));
		assertTrue(t2.trainLM(s));
		assertTrue(t2.trainLM(ss));
		assertTrue(t1.trainLM(sss) && lib.markPERtag(sss).equals("<PER>Dr.Carson</PER>") );
		assert(t1.trainLM(ss) == t2.trainLM(ss));
		
	}
	
	
	
	


}
