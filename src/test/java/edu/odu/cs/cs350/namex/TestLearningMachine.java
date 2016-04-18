package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.nio.file.*;
import java.util.HashSet;


import org.junit.Before;
import org.junit.Test;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TestLearningMachine {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test 
	/**
	 * test for constructor; public LearningMachine()
	 */
	public void testLearningMachine() throws Exception{
		/*
		 * private Classifier classifier; 
		 * private FastVector attributes; 
		 * private int numAttr; 
		 * private Instances trainingInstances; 
		 * private String evalSummary;
		 */
		
		LearningMachine lm1 = new LearningMachine();
		weka.classifiers.bayes.NaiveBayes nb1 = new weka.classifiers.bayes.NaiveBayes();
		assertTrue(lm1.getClassifier().getClass().equals(nb1.getClass()));
		assertTrue(lm1.getNumberOfAttributes() > 0);
		assertEquals(71 , lm1.getSizeOfAttributes() );
		assertFalse(lm1.getEvalSummary().equals(""));
		assertTrue(lm1.getTrainingInstances() != null);
	}
	
	@Test
	/**
	 * test for public boolean train() throws Exception
	 */
	public void TestTrain() throws Exception{
		LearningMachine lm1 = new LearningMachine();
		assertTrue(lm1.train());
	}
	
	
	
	
	@Test
	/**
	 * test for constructor; public LearningMachine(String machineModel)
	 */
	public void testLearningMachineString() throws Exception{
		
		LearningMachine lm1 = new LearningMachine("testing");
		weka.classifiers.bayes.NaiveBayes nb1 = new weka.classifiers.bayes.NaiveBayes();
		assertTrue(lm1.getClassifier().getClass().equals(nb1.getClass()));
		assertTrue(lm1.getNumberOfAttributes() > 0);
		assertEquals(71 , lm1.getSizeOfAttributes() );
		assertFalse(lm1.getEvalSummary().equals(""));
		assertTrue(lm1.getTrainingInstances() != null);
	}
	
	
	@Test
	/**
	 * test for public boolean train(Instances trainingData) throws Exception
	 */
	public void TestTrainInstances() throws Exception{
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetDistribution() throws Exception{
		
		LearningMachine lm1 = new LearningMachine();
		
		assertTrue(lm1.getDistribution("This is a test input.") != null);
		assertTrue(lm1.getDistribution("This is not a test.") != null);
		assertTrue(lm1.getDistribution("") != null);
	}
	
	
	@Test
	/**
	 * test for public String classify(String input) throws Exception 
	 */
	public void testClassify() throws Exception{
		String in1 = "This";
		String in2 = "";
		String in3 = null;
		String in4 = "is";
		String in5 = "Caroline";
		String in6 = "John";
		
		LearningMachine lm1 = new LearningMachine();
		assertEquals("other", lm1.classify(in1));
		assertEquals("other", lm1.classify(in2));
		assertEquals("other", lm1.classify(in3));
		assertEquals("other", lm1.classify(in4));
		assertEquals("beginning", lm1.classify(in5));
		assertEquals("beginning", lm1.classify(in6));
		
	}
	
	



	@Test
	public void testSaveLoadLM() {

		String learningMachineFileName = "trainedmachine.model";

		String arffFilePath = "/data/arff/trainingData.arff";

		// ********** End Configurations **********

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		String filePath = relativePath + "/learning_machines/" + learningMachineFileName;
		arffFilePath = relativePath + "" + arffFilePath;

		LearningMachine LM1 = new LearningMachine();
		LearningMachine LM2 = new LearningMachine();
		LM2.loadLM(filePath);

		try {
			LM1.importARFF(arffFilePath);
			LM1.train();
			LM1.printEvaluationSummary();
			LM1.saveLM();
			LM2.train();
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(LM1.hashCode(), LM2.hashCode());
		assertEquals(LM1,LM2);
	}




	
	

}

