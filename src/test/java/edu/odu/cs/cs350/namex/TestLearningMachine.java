package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.util.HashSet;

//import java.nio.file.Path;
//import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import weka.classifiers.Classifier;
import weka.core.Instances;

public class TestLearningMachine {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	/**
	 * test for constructor of LearningMachine.java
	 */
	public void TestLearningMachine(){
		
		fail("Not yet implemented");
		
	}
	
	@Test
	/**
	 * test for constructor(String ) of LearningMachine.java
	 */
	public void TestLearningMachineString(){
		fail("Not yet implemented");
	}
	
	
	@Test
	/**
	 * test for public boolean train(Instances trainingData) throws Exception
	 */
	public void TestTrainInstances() throws Exception{
		fail("Not yet implemented");
	}
	
	@Test
	/**
	 * test for public boolean train() throws Exception
	 */
	public void TestTrain() throws Exception{
		fail("Not yet implemented");
	
	}
	
	
	
	@Test
	/**
	 * test for public double[] getDistribution(String input) throws Exception
	 */
	public void TestGetDistribution() throws Exception {
		fail("Not yet implemented");
	}
	
	@Test
	/**
	 * test for public String classify(String input) throws Exception 
	 */
	public void TestClassify() throws Exception{
		fail("Not yet implemented");
	}
	
	
	@Test
	/**
	 * test for public String classifyShingle(String input) throws Exception
	 */
	public void TestClassifyShingle() throws Exception {
		fail("Not yet implemented");
	}
	
	
	@Test
	/**
	 * test for public double[] getShingleDistribution(String input) throws Exception
	 */
	public void TestGetShingleDistribution() throws Exception {
		fail("Not yet implemented");
	}
	
	@Test
	/**
	 * test for public void printEvaluationSummary() 
	 */
	public void TestPrintEvaluationSummary(){
		fail("Not yet implemented");
	}
	
	
	@Test
	/**
	 * test for public void printARFF()
	 */
	public void TestPrintARFF(){
		fail("Not yet implemented");
	}
	
	@Test
	/**
	 * test for public boolean exportARFF(String outputFilePath)
	 */
	public void TestExportARFF(){
		fail("Not yet implemented");
	}
	
	
	@Test
	/**
	 * test for public boolean importARFF(String filePath) throws Exception
	 */
	public void TestImportARFFString() throws Exception{
		fail("Not yet implemented");
	}
	
	
	@Test
	/**
	 * test for public void importARFF(HashSet<String> trainingData)
	 */
	public void TestImportARFFHashSet() {
		fail("Not yet implemented");
	}
	
	
	@Test
	/**
	 * test for public boolean importARFF(String[] trainingData) throws Exception
	 */
	public void TestImportARFFStringArr() throws Exception{
		fail("Not yet implemented");
	}
	
	@Test
	/**
	 * test for public Classifier getClassifier()
	 */
	public void TestGetClassifier(){
		fail("Not yet implemented");
	}
	
	
	@Test
	/**
	 * test for public int getNumberOfAttributes()
	 */
	public void TestGetNumberOfAttributes(){
		fail("Not yet implemented");
	}
	
	
	@Test
	/**
	 * test for public Instances getTrainingInstances() 
	 */
	public void TestGetTrainingInstances(){
		fail("Not yet implemented");
	}
	
	
	@Test
	/**
	 * test for public void saveLM()
	 */
	public void testSaveLM(){
		fail("Not yet implemented");
	}
	
	@Test
	/**
	 * test for public Classifier loadLM(String LMBrain) 
	 */
	public void testLoadLM(){
		fail("Not yet implemented");
	}
}

