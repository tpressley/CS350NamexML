package edu.odu.cs.cs350.namex;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.odu.cs.cs350.namex.Trainer;

import java.util.*;

public class TestTrainer {
	
	//member variables
	String mArffPath = "/src/main/data/trainingMaterial002.arff";
	String mTrainPathMarked = "/src/main/data/trainingMaterial002.txt";
	
	String mTrainPathUnmarked = "/src/main/data/trainingMaterial002Unmarked.txt"; //make this file by unmarking <PER>s in train...002.txt
	String mArffPathShingle = "/src/main/data/shingling_training_k3_2.arff";
	

	// ********** USER STORIES UNDER DEVELOPMENT **********

	// User Story #1095
	// Status - Implementation
	// As a Trainer, I want Shingling applied either to lists of
	// tokens or to lists of feature sets.
	@Test
	public void testShingle() {

		// ********** Configurations **********

		String ARFFFilePath = mArffPath;
		String shingleARFFFilePath = mArffPathShingle;
		String input = "<NER>The George Washington University is where I will be attending in the fall.</NER>";
		int k = 3; // the value of k needs to match the same value k as the
					// generated .arff file

		// ********** End Configurations **********

		long startTime = System.currentTimeMillis(); // for elapsed time
														// computation

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		ARFFFilePath = relativePath + "" + ARFFFilePath;
		shingleARFFFilePath = relativePath + "" + shingleARFFFilePath;

		Librarian librarian = new Librarian();
		Trainer trainer = new Trainer();

		// Trainer for Token Classification
		LearningMachine learningMachine = new LearningMachine();

		// Trainer for Shingle Classification
		LearningMachine shingleLearningMachine = new LearningMachine(k);

		try {
			learningMachine.importARFF(ARFFFilePath);
			learningMachine.train();
			// trainer.printEvaluationSummary();
			// trainer.printARFF();

			shingleLearningMachine.importARFF(shingleARFFFilePath);
			shingleLearningMachine.train();
			shingleLearningMachine.printEvaluationSummary();
			// System.out.println("# of Attributes: " +
			// shingleTrainer.getNumberOfAttributes());

			ArrayList<TextBlock> tbs = Librarian.separateNER(input);
			for (TextBlock tb : tbs) {
				ArrayList<Token> tks = trainer.tokenize(tb.getTextBlock());
				ArrayList<Token> classifiedTks = new ArrayList<Token>();

				for (Token t : tks) {
					t = librarian.getFeatures(t);

					if (!t.getLexical().equals("whiteSpace")) {
						t.setName(learningMachine.classify(t.getARFF()));
						classifiedTks.add(t);
						// System.out.print(t.getLexeme() + " ");
					}
				}

				ArrayList<Shingle> shingles = trainer.getShingles(classifiedTks, "");

				for (Shingle s : shingles) {
					s.setContainsName(shingleLearningMachine.classifyShingle(s.getArffData()));
					s.setDistribution(shingleLearningMachine.getShingleDistribution(s.getArffData()));
					s.printShingle();
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// print elapsed time
		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime) / 1000;
		System.out.println("Elapsed Time: " + elapsedTime + " seconds.");
	}

	// User Story #851
	// Status - Development
	// As a Trainer, I want the program to properly prepare data
	// to train the learning machine.
	@Test
	public void testPrepareShingleData() {
		// ********** Configurations **************

		String inputFilePath = mTrainPathUnmarked;
		String outputFilePath = mArffPathShingle;
		String arffFilePath = mArffPath;

		int k = 3;

		// ********** End Configurations **********

		long startTime = System.currentTimeMillis();

		Trainer trainer = new Trainer();

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		inputFilePath = relativePath + "" + inputFilePath;
		outputFilePath = relativePath + "" + outputFilePath;
		arffFilePath = relativePath + "" + arffFilePath;

		trainer.prepareShinglingData(arffFilePath, k, inputFilePath, outputFilePath);

		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime) / 1000;

		System.out.println("Elapsed Time: " + elapsedTime + " seconds.");
	}

	// User Story #858, #857
	// Status - Development
	// The total token count for any token should be equal
	// to the number of times that token appears in the input
	@Test
	public void testGetTokenCount() {
		Trainer t1 = new Trainer();
		ArrayList<Token> tokenizedText = t1.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>");

		int TCount = 0;
		TCount = t1.getTokenCount(2, tokenizedText);

		assertTrue(TCount == t1.getTokenCount(2, tokenizedText));
		assertFalse(t1.getTokenCount(2, tokenizedText) < 1); // tokencount
																// cannot be
																// less than 1
																// if present
	}

	// ********** COMPLETED USER STORIES **********

	// User Story #851
	// Status - Completed
	// As a Trainer, I want the program to properly prepare data
	// to train the learning machine.
	@Test
	public void testPrepareData() throws Exception {
		// ********** Configurations **************

		String inFpath = mTrainPathMarked;
		String outFpath = mArffPath;
		//where is this this arff file supposed to be stored according to grading rubric??

		// ********** End Configurations **********

		Path currRelPath = Paths.get(""); // current relative path
		String relPath = currRelPath.toAbsolutePath().toString();
		inFpath = relPath + "" + inFpath;
		outFpath = relPath + "" + outFpath;

		Trainer t1 = new Trainer();

		System.out.println("*******************************");
		System.out.println(" Generating ARFF Training Data");
		System.out.println("*******************************\n");

		System.out.println(" Input FilePath: " + inFpath);
		System.out.println("Output FilePath: " + outFpath);

		// check if the output .arff file exists
		File file = new File(outFpath);

		System.out.println(file.exists());
		
		//fail this test if filepath does not exist
		if(!file.exists())
		{
			fail("outFpath does not exist");
		}
		
		assertTrue(file.exists());
		assertTrue(t1.prepareData(inFpath, outFpath, true));
	}

	// User Story #853
	// Status: Completed
	// As Trainer, I want to use existing data to train the learning machine
	@Test
	public void testTrainLM() {
		String arffPath = mArffPath; 

		Path currRelPath = Paths.get("");
		String relPath = currRelPath.toAbsolutePath().toString();
		arffPath = relPath + "" + arffPath;

		Trainer t1 = new Trainer(5);
		int numInst = t1.getLM().getTrainingInstances().numInstances();

		try {
			t1.trainLM(arffPath);
			assertEquals(numInst, t1.getLM().getTrainingInstances().numInstances());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * User story #860 Tests to see if the function properly tokenizes the
	 * output by comparing to the output when using StringTokenizer User Story
	 * #847 As a Trainer I want the PNE to convert tokens into a set of symbols
	 * and identifiers
	 */
	// User Story #860
	// Status - Completed
	// Text blocks divided into tokens, with punctuation separate from
	// alphabetics (T)
	@Test
	public void testTokenize() {
		Trainer trainer = new Trainer();

		String input = "Hello World! This is John Smith.";

		ArrayList<Token> tokens = trainer.tokenize(input);

		assertEquals("Hello", tokens.get(0).getLexeme());
		assertEquals("World", tokens.get(2).getLexeme());
		assertEquals("!", tokens.get(3).getLexeme());
		assertEquals("Smith", tokens.get(11).getLexeme());
		assertEquals(".", tokens.get(12).getLexeme());
	}

	// User Story #849 - Completed
	// Save trained learning machine in a file. (T)
	// User Story #848 - Completed
	// Load trained machine into the extractor (L,T)
	@Test
	public void testSaveLoadLM() throws Exception {
		// ********** Configurations **************

		String learningMachineFileName = "lm_1";

		String arffFilePath = mArffPath;

		// ********** End Configurations **********

		Trainer trainer = new Trainer();

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		String filePath = relativePath + "/learning_machines/" + learningMachineFileName;
		arffFilePath = relativePath + "" + arffFilePath;

		LearningMachine LM1 = new LearningMachine();
		LM1.importARFF(arffFilePath);
		LM1.train();
		LM1.printEvaluationSummary();
		trainer.setLM(LM1);
		// trainer.saveLearningMachine(filePath);

		LearningMachine LM2 = Trainer.loadLM(filePath);
		LM2.train();
		// LM2.printEvaluationSummary();

		assertEquals(LM1.getSerialVersionUID(), LM2.getSerialVersionUID());
	}

	// ********** MISC TEST CASES **********

	// Tests the constructors
	@Test
	public void testTrainer() {
		Trainer t1 = new Trainer();
		assertTrue(t1.getLM() != null);

		// a learning machine with a k value of 3 should have 106 attributes
		Trainer t2 = new Trainer(3);
		assertTrue(t2.getLM().getNumberOfAttributes() == 106);
	}

}