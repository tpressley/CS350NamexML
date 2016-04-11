package edu.odu.cs.cs350.namex;

//import weka.core.Instances;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.odu.cs.cs350.namex.Trainer;

import java.util.*;

public class TestTrainer {

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

		// Trainer for Token Classification
		Trainer trainer = new Trainer();

		// Trainer for Shingle Classification
		Trainer shingleTrainer = new Trainer(k);

		try {
			trainer.importARFF(ARFFFilePath);
			trainer.trainLM();
			// trainer.printEvaluationSummary();
			// trainer.printARFF();

			shingleTrainer.importARFF(shingleARFFFilePath);
			shingleTrainer.trainLM();
			shingleTrainer.printEvaluationSummary();
			// System.out.println("# of Attributes: " +
			// shingleTrainer.getNumberOfAttributes());

			ArrayList<TextBlock> tbs = Librarian.separateNER(input);
			for (TextBlock tb : tbs) {
				ArrayList<Token> tks = trainer.tokenize(tb.getTextBlock());
				ArrayList<Token> classifiedTks = new ArrayList<Token>();

				for (Token t : tks) {
					t = librarian.classifyToken(t);

					if (!t.getLexical().equals("whiteSpace")) {
						t.setName(trainer.classify(t.getARFF()));
						classifiedTks.add(t);
						// System.out.print(t.getLexeme() + " ");
					}
				}

				ArrayList<Shingle> shingles = shingleTrainer.getShingles(k, classifiedTks, "");

				for (Shingle s : shingles) {
					s.setContainsName(shingleTrainer.classifyShingle(s.getArffData()));
					s.setDistribution(shingleTrainer.getShingleDistribution(s.getArffData()));
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

	@Test
	public void testGenerateShingleARFF() {
		// ********** Configurations **************

		String inputFilePath = "/data/training/trainingDataUnmarked.txt";
		String outputFilePath = "/data/arff/shingling_training_k3_2.arff";
		String arffFilePath = "/data/arff/trainingData.arff";

		int k = 3;

		// ********** End Configurations **********

		long startTime = System.currentTimeMillis();

		Trainer trainer = new Trainer(k);

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		inputFilePath = relativePath + "" + inputFilePath;
		outputFilePath = relativePath + "" + outputFilePath;
		arffFilePath = relativePath + "" + arffFilePath;

		trainer.generateShingleARFF(arffFilePath, k, inputFilePath, outputFilePath);

		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime) / 1000;

		System.out.println("Elapsed Time: " + elapsedTime + " seconds.");
	}

	// Generates an .arff file from a .txt file containing names tagged between
	// <PER> tags
	@Test
	public void testGenerateARFF() {
		// ********** Configurations **************

		String trainingDataFilePath = "/data/training/trainingData.txt";
		String arffFilePath = "/data/arff/trainingData.arff";

		// ********** End Configurations **********

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		trainingDataFilePath = relativePath + "" + trainingDataFilePath;
		arffFilePath = relativePath + "" + arffFilePath;

		Trainer trainer = new Trainer();

		System.out.println("*******************************");
		System.out.println(" Generating ARFF Training Data");
		System.out.println("*******************************\n");

		System.out.println(" Input FilePath: " + trainingDataFilePath);
		System.out.println("Output FilePath: " + arffFilePath);

		trainer.generateARFF(trainingDataFilePath, arffFilePath);
	}

	// User Story #853
	// Status: Complete
	// As Trainer, I want to use existing data to train the learning machine
	@Test
	public void testTrainLM() {
		String arffFilePath = "/data/arff/test_training_data.arff";

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		arffFilePath = relativePath + "" + arffFilePath;

		Trainer trainer = new Trainer();

		try {
			trainer.importARFF(arffFilePath);
			trainer.trainLM();

			// trainer.printEvaluationSummary();
			// System.out.println(trainer.getTrainingInstances().numInstances());
			assertEquals(24, trainer.getTrainingInstances().numInstances());

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
	@Test
	public void testTokenize() {
		Trainer trainer = new Trainer();
		Token token = new Token();

		token.setLexeme("Oh");
		token.setPartOfSpeech("other");
		token.setLexical("capitalized");
		token.setKillWord(0);
		ArrayList<Token> tokenList = trainer.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>");
		assertEquals("capitalized", tokenList.get(1).getLexical());
		assertFalse("verb" == tokenList.get(1).getPartOfSpeech());
		assertTrue(trainer.getTokenCount(tokenList.get(0), tokenList) < 1);
	}

	/**
	 * User story #858, #857 The total token count for any token should be equal
	 * to the number of times that token appears in the input
	 */

	@Test
	public void testGetTokenCount() {
		Trainer trainer = new Trainer();
		ArrayList<Token> tokenizedText = trainer.tokenize(
				"<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>");

		assertEquals(3, trainer.getTokenCount(tokenizedText.get(2), tokenizedText));
		assertFalse(trainer.getTokenCount(tokenizedText.get(2), tokenizedText) < 1);
	}

	/**
	 * The trainer should probably collect training materials upon creation
	 */
	@Test
	public void testTrainer() {
		Trainer trainer = new Trainer();
		assertTrue(trainer.getTrainingData() != null);
	}

	// User Story #851 As a Trainer, I want the program to properly prepare data
	// to train the learning machine.
	@Test
	public void testGetTrainingData() {

		Trainer t1 = new Trainer();
		assertTrue(t1.getTrainingData() != null);

		Trainer t2 = new Trainer();
		assertTrue(t2.getTrainingData() != null);

		//String in = "this is a test.";

		// assertEquals(t1.prepareData(in), t2.prepareData(in));
	}

	public void testSaveLoadLM() throws Exception {
		Trainer t1 = new Trainer();
		Trainer t2 = new Trainer();
		// t2.prepareData("<NER>\"Oh, no,\" she\'s saying, \"a $400 blender
		// can\'t handle something this hard!\"</NER>");
		t2.SaveClassifier();
		t1.LoadClassifier();
		assertEquals(t1, t2);

	}

}