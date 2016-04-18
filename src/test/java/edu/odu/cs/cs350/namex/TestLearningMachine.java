package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.nio.file.*;
import java.util.ArrayList;
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

/**
 * Test class for LearningMachine.java
 * 
 * @author Caroline Chey
 *
 */
public class TestLearningMachine {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	/**
	 * test for constructor; public LearningMachine()
	 */
	public void testLearningMachine() throws Exception {
		/*
		 * private Classifier classifier; private FastVector attributes; private
		 * int numAttr; private Instances trainingInstances; private String
		 * evalSummary;
		 */

		try {
			LearningMachine lm1 = new LearningMachine();
			weka.classifiers.bayes.NaiveBayes nb1 = new weka.classifiers.bayes.NaiveBayes();
			assertTrue(lm1.getClassifier().getClass().equals(nb1.getClass()));
			assertTrue(lm1.getNumberOfAttributes() > 0);
			assertEquals(71, lm1.getSizeOfAttributes());
			assertFalse(lm1.getEvalSummary().equals(""));
			assertTrue(lm1.getTrainingInstances() != null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	/**
	 * test for public boolean train() throws Exception
	 */
	public void TestTrain() throws Exception {
		try {
			LearningMachine lm1 = new LearningMachine();
			assertTrue(lm1.train());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	/**
	 * test for constructor; public LearningMachine(String machineModel)
	 */
	public void testLearningMachineString() throws Exception {

		try {
			LearningMachine lm1 = new LearningMachine("testing");
			weka.classifiers.bayes.NaiveBayes nb1 = new weka.classifiers.bayes.NaiveBayes();
			assertTrue(lm1.getClassifier().getClass().equals(nb1.getClass()));
			assertTrue(lm1.getNumberOfAttributes() > 0);
			assertEquals(71, lm1.getSizeOfAttributes());
			assertFalse(lm1.getEvalSummary().equals(""));
			assertTrue(lm1.getTrainingInstances() != null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	/**
	 * test for public boolean train(Instances trainingData) throws Exception
	 */
	public void TestTrainInstances() throws Exception {
		fail("Not yet implemented");
	}

	/*@Test
	public void testGetDistribution() throws Exception {
		double[] distribution = {};
		ArrayList<Token> token = new ArrayList<Token>();
		token.add(new Token("James"));
		Trainer trainer = new Trainer();
		trainer.setFeatures(token);
		try {
			LearningMachine lm1 = new LearningMachine();
			distribution = lm1.getDistribution(token.get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(distribution[0] > 90);
		assertFalse(distribution[1] > 10);
		assertFalse(distribution[2] > 5);
	}*/

	@Test
	/**
	 * test for public String classify(String input) throws Exception
	 */
	public void testClassify() throws Exception {
		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	/**
	 * test for public boolean printEvaluationSummary() throws Exception
	 */
	public void testPrintEvaluationSummary() throws Exception {

		LearningMachine lm1 = new LearningMachine();
		LearningMachine lm2 = new LearningMachine("test2");
		try {

			lm1.train();
			assertTrue(lm1.printEvaluationSummary());
			lm2.train();
			assertTrue(lm2.printEvaluationSummary());

		} catch (Exception e375) {
			e375.printStackTrace();
		}
	}

	@Test
	/**
	 * test for public String getEvalSummary() throws Exception
	 */
	public void testGetEvalSummary() throws Exception {

		LearningMachine lm1 = new LearningMachine();
		LearningMachine lm2 = new LearningMachine("two");
		try {
			lm1.train();
			assertTrue(lm1.getEvalSummary() != null);
			assertFalse(lm1.getEvalSummary().equals(""));

			lm2.train();
			assertTrue(lm2.getEvalSummary() != null);
			assertFalse(lm2.getEvalSummary().equals(""));

		} catch (Exception e462) {
			e462.printStackTrace();
		}
	}

	@Test
	/**
	 * test for public boolean printARFF()
	 */
	public void testPrintARFF() throws Exception {
		try {
			LearningMachine lm = new LearningMachine();
			assertTrue(lm.train());
			assertTrue(lm.getTrainingInstances() != null);
			assertTrue(lm.printARFF());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	/**
	 * test for public boolean exportARFF(String outputFilePath)
	 */
	public void testExportARFF() throws Exception {
		try {
			LearningMachine lm = new LearningMachine();
			assertTrue(lm.train());
			assertTrue(lm.getTrainingInstances() != null);

			String outpath = "/src/main/data/testExportARFF.arff";
			assertTrue(lm.exportARFF(outpath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	/**
	 * test for public boolean importARFF(String filePath) throws Exception
	 */
	public void testImportARFFString() throws Exception {

		try {
			LearningMachine lm = new LearningMachine();
			String inpath = "/src/main/data/trainingData.arff";
			assertTrue(lm.importARFF(inpath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	/**
	 * test for public void importARFF(LinkedList<String> trainingData)
	 */
	public void testImportARFFLinkedList() throws Exception {

		fail("not yet implemented");
	}

	@Test
	/**
	 * test for public boolean importARFF(String[] trainingData) throws
	 * Exception
	 */
	public void testImportARFFStringArr() throws Exception {

		fail("not yet implemented");
	}

	@Test
	/**
	 * test for public Classifier getClassifier()
	 */
	public void testGetClassifier() {
		LearningMachine lm999 = new LearningMachine();
		assertTrue(lm999.getClassifier() != null);

		LearningMachine lm472 = new LearningMachine();
		assertTrue(lm472.getClassifier() != null);
	}

	@Test
	/**
	 * test for public Attribute getCopyAttribute(String name, FastVector
	 * nominalVal)
	 */
	public void testGetCopyAttribute() {
		LearningMachine lm57 = new LearningMachine();
		LearningMachine lm33 = new LearningMachine();

		String lm57name = "test_lm57";
		FastVector lm57nv = new FastVector(9);
		assertTrue(lm57.getCopyAttribute(lm57name, lm57nv) != null);
		FastVector lm57nv2 = new FastVector(0);
		assertTrue(lm57.getCopyAttribute(lm57name, lm57nv2) != null);
		FastVector lm57nv3 = new FastVector(1);
		assertTrue(lm57.getCopyAttribute(lm57name, lm57nv3) != null);

		String lm33name = "test_lm33";
		FastVector lm33nv = new FastVector(6);
		assertTrue(lm57.getCopyAttribute(lm33name, lm33nv) != null);
		FastVector lm33nv2 = new FastVector();
		assertTrue(lm57.getCopyAttribute(lm33name, lm33nv2) != null);

	}

	@Test
	/**
	 * test for public int getNumberOfAttributes()
	 */
	public void testGetNumberOfAttributes() {
		LearningMachine lm57 = new LearningMachine();
		LearningMachine lm33 = new LearningMachine("lm33");

		assertTrue(lm57.getNumberOfAttributes() != 0);
		assertTrue(lm57.getNumberOfAttributes() > 0);
		assertTrue(lm33.getNumberOfAttributes() != 0);
		assertTrue(lm33.getNumberOfAttributes() > 0);
	}

	@Test
	/**
	 * test for public int getSizeOfAttributes()
	 */
	public void testGetSizeOfAttributes() {
		LearningMachine lm57 = new LearningMachine();
		LearningMachine lm20 = new LearningMachine("lm20");

		assertTrue(lm57.getSizeOfAttributes() != 0);
		assertTrue(lm57.getSizeOfAttributes() > 0);
		assertTrue(lm20.getSizeOfAttributes() != 0);
		assertTrue(lm20.getSizeOfAttributes() > 0);
		assertEquals(71, lm57.getSizeOfAttributes());
		assertEquals(71, lm20.getSizeOfAttributes());
		assertEquals(lm57.getSizeOfAttributes(), lm20.getSizeOfAttributes());
	}

	@Test
	/**
	 * test for public Instances getTrainingInstances()
	 */
	public void testGetTrainingInstances() {
		LearningMachine lm49 = new LearningMachine();

		assertTrue(lm49.getTrainingInstances() != null);
		assertTrue(lm49.getTrainingInstances().lastInstance() != null);
		assertTrue(lm49.getTrainingInstances().firstInstance() != null);
		assertTrue(lm49.getTrainingInstances().numInstances() > 0);
		assertTrue(lm49.getTrainingInstances().numAttributes() > 0);
	}

	@Test
	/**
	 * test for public void saveLM() and public Classifier loadLM(String
	 * LMBrain)
	 */
	public void testSaveLMLoadLM() throws Exception {

		String lmFilename = "trainedLM.model";
		String arffPath = "/src/main/data/testSaveLMLoadLM.arff";

		Path currRelaPath = Paths.get("");
		String relaPath = currRelaPath.toAbsolutePath().toString();
		String filePath = relaPath + "/learning_machines/" + lmFilename;
		// arffPath = relaPath + "" + arffPath;

		LearningMachine LM1 = new LearningMachine();
		LearningMachine LM2 = new LearningMachine();
		LM2.loadLM(filePath);

		try {
			LM1.importARFF(arffPath);
			LM1.train();
			LM1.printEvaluationSummary();
			LM1.saveLM();
			LM2.train();

			assertEquals(LM1.hashCode(), LM2.hashCode());
			assertEquals(LM1, LM2);
		} catch (Exception e532) {
			e532.printStackTrace();
		}
	}

}
