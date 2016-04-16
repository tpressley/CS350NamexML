package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

//import java.nio.file.Path;
//import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

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

}
