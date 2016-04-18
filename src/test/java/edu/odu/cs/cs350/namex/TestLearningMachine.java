package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.nio.file.*;
import java.util.HashSet;
import java.util.LinkedList;

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
	/**
	 * test for public boolean printEvaluationSummary() throws Exception
	 */
	public void testPrintEvaluationSummary() throws Exception{
		
		LearningMachine lm1 = new LearningMachine();
		LearningMachine lm2 = new LearningMachine("test2");
		try{
			
			lm1.train();
			assertTrue(lm1.printEvaluationSummary());
			lm2.train();
			assertTrue(lm2.printEvaluationSummary());
			
		}catch(Exception e375){
			
		}
	}
	
	
	@Test
	/**
	 * test for public String getEvalSummary() throws Exception
	 */
	public void testGetEvalSummary() throws Exception{
		
		LearningMachine lm1 = new LearningMachine();
		LearningMachine lm2 = new LearningMachine("two");
		try{
			lm1.train();
			assertTrue(lm1.getEvalSummary() != null);
			assertFalse(lm1.getEvalSummary().equals(""));
			
			lm2.train();
			assertTrue(lm2.getEvalSummary() != null);
			assertFalse(lm2.getEvalSummary().equals(""));
			
		}catch(Exception e462){
			
		}
	}
	
	@Test
	/**
	 * test for public boolean printARFF()
	 */
	public void testPrintARFF() throws Exception{
		LearningMachine lm = new LearningMachine();
		assertTrue(lm.train());
		assertTrue(lm.getTrainingInstances() != null);
		assertTrue(lm.printARFF());
	}


	@Test
	/**
	 * test for public boolean exportARFF(String outputFilePath)
	 */
	public void testExportARFF() throws Exception{
		LearningMachine lm = new LearningMachine();
		assertTrue(lm.train());
		assertTrue(lm.getTrainingInstances() != null);
		
		String outpath = "/src/main/data/testExportARFF.arff";
		assertTrue(lm.exportARFF(outpath));
	}
	
	
	@Test
	/**
	 * test for public boolean importARFF(String filePath) throws Exception
	 */
	public void testImportARFFString() throws Exception{
		
		LearningMachine lm = new LearningMachine();
		String inpath = "/src/main/data/testExportARFF.arff";
		assertTrue(lm.importARFF(inpath));
		
	}
	
	
	@Test
	/**
	 * test for public void importARFF(LinkedList<String> trainingData)
	 */
	public void testImportARFFLinkedList() throws Exception{
		
		fail("not yet implemented");
	}
	
	
	@Test
	/**
	 * test for public boolean importARFF(String[] trainingData) throws Exception
	 */
	public void testImportARFFStringArr() throws Exception{
		
		fail("not yet implemented");
	}
	
	
	@Test
	/**
	 * test for public Classifier getClassifier() 
	 */
	public void testGetClassifier(){
		LearningMachine lm999 = new LearningMachine();
		assertTrue(lm999.getClassifier() != null);
		
		LearningMachine lm472 = new LearningMachine();
		assertTrue(lm472.getClassifier() != null);
	}
	
/*
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
*/



	
	

}

