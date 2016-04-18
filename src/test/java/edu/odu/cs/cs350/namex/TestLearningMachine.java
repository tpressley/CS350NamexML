package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
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
	public void testGetSerialVersionUID() {
		fail("Not yet implemented");
	}

	@Test
	public void testTrainInstances() {
		fail("Not yet implemented");
	}

	@Test
	public void testTrain() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDistribution() {
		fail("Not yet implemented");
	}

	@Test
	public void testClassify() {
		fail("Not yet implemented");
	}

	@Test
	public void testClassifyShingle() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetShingleDistribution() {
		fail("Not yet implemented");
	}

	@Test
	public void testClassifyOld() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintEvaluationSummary() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintARFF() {
		fail("Not yet implemented");
	}

	@Test
	public void testExportARFF() {
		fail("Not yet implemented");
	}

	@Test
	public void testImportARFFString() {
		fail("Not yet implemented");
	}

	@Test
	public void testImportARFFHashSetOfString() {
		fail("Not yet implemented");
	}

	@Test
	public void testImportARFFStringArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTrainingData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClassifier() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAttribute() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumberOfAttributes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTrainingInstances() {
		fail("Not yet implemented");
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

