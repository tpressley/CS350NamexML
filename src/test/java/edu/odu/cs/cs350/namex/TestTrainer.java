package edu.odu.cs.cs350.namex;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.odu.cs.cs350.namex.Trainer;

import java.util.*;

public class TestTrainer
{
	
	// ********** USER STORIES UNDER DEVELOPMENT **********
	
	// User Story #1095
	// Status - Implementation
	// As a Trainer, I want Shingling applied either to lists of 
	// tokens or to lists of feature sets.
	@Test
	public void testShingle() {
		
		// ********** Configurations **********

		String ARFFFilePath = "/data/arff/trainingData.arff";
		String shingleARFFFilePath = "/data/arff/shingling_training_k3_2.arff";
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

				ArrayList<Shingle> shingles = trainer.getShingles(k, classifiedTks, "");

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

		String inputFilePath = "/data/training/trainingDataUnmarked.txt";
		String outputFilePath = "/data/arff/shingling_training_k3_2.arff";
		String arffFilePath = "/data/arff/trainingData.arff";

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
		Trainer trainer = new Trainer();
		ArrayList<Token> tokenizedText = trainer.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>");

		assertEquals(3, trainer.getTokenCount(tokenizedText.get(2), tokenizedText));
		assertFalse(trainer.getTokenCount(tokenizedText.get(2), tokenizedText) < 1);
	}
	
	// ********** COMPLETED USER STORIES **********
	
	// User Story #851 
	// Status - Completed
	// As a Trainer, I want the program to properly prepare data
	// to train the learning machine.
	@Test
	public void testPrepareData() 
	{
		// ********** Configurations **************

		String inputFilePath = "/data/training/trainingData.txt";
		String outputFilePath = "/data/arff/trainingData.arff";

		// ********** End Configurations **********

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		inputFilePath = relativePath + "" + inputFilePath;
		outputFilePath = relativePath + "" + outputFilePath;

		Trainer trainer = new Trainer();

		System.out.println("*******************************");
		System.out.println(" Generating ARFF Training Data");
		System.out.println("*******************************\n");

		System.out.println(" Input FilePath: " + inputFilePath);
		System.out.println("Output FilePath: " + outputFilePath);

		trainer.prepareData(inputFilePath, outputFilePath, true);
		
		// check if the output .arff file exists
		File file = new File(outputFilePath);
		System.out.println(file.exists());
		assertTrue(file.exists());
	}

	// User Story #853
	// Status: Completed
	// As Trainer, I want to use existing data to train the learning machine
	@Test
	public void testTrainLM() {
		String arffFilePath = "/data/arff/test_training_data.arff";

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		arffFilePath = relativePath + "" + arffFilePath;
		
		Trainer trainer = new Trainer();

		try 
		{
			trainer.trainLM(arffFilePath);
			assertEquals(24, trainer.getLearningMachine().getTrainingInstances().numInstances());

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
	// Text blocks divided into tokens, with punctuation separate from alphabetics (T)
	@Test
	public void testTokenize() 
	{
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
	public void testSaveLoadLM() throws Exception 
	{
		// ********** Configurations **************

		String learningMachineFileName = "lm_1";
		
		String arffFilePath = "/data/arff/trainingData.arff";

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
		trainer.setLearningMachine(LM1);
		//trainer.saveLearningMachine(filePath);
		
		LearningMachine LM2 = Trainer.loadLearningMachine(filePath);
		LM2.train();
		//LM2.printEvaluationSummary();
		
		assertEquals(LM1.getSerialVersionUID(), LM2.getSerialVersionUID());
	}

	// ********** MISC TEST CASES **********
	
	// Tests the constructors
	@Test
	public void testTrainer() 
	{
		Trainer t1 = new Trainer();
		assertTrue(t1.getLearningMachine() != null);
		
		// a learning machine with a k value of 3 should have 106 attributes
		Trainer t2 = new Trainer(3);
		assertTrue(t2.getLearningMachine().getNumberOfAttributes() == 106);
	}
	
}