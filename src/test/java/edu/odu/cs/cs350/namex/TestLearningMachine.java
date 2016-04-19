package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for LearningMachine.java
 * 
 *
 */
public class TestLearningMachine {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * test for loadLM() in LearningMachine.java
	 */
	@Test
	public void testLoadLM()
	{
		String LMFilePath = "/data/lm/learningMachine";   // without .ser extension
		
		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		LMFilePath = relativePath + "/src/main" + LMFilePath;
		
		LearningMachine lm = Trainer.loadLM(LMFilePath);
		
		try {
			lm.printEvaluationSummary();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    // test output
	}
	
	/**
	 * test for saveLM() in LearningMachine.java
	 */
	@Test
	public void testSaveLM()
	{
		int k = 5;
		
		String arffFilePath = "/data/arff/trainingDataAll.arff";
		String outputFilePath = "/data/lm/learningMachine";
		
		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		arffFilePath = relativePath + "/src/main" + arffFilePath;
		outputFilePath = relativePath + "/src/main" + outputFilePath;
		
		//LearningMachine lm = new LearningMachine();
		Trainer trainer = new Trainer(arffFilePath);
		trainer.saveLM(outputFilePath);	
	}

	/**
	 * test  for classifyShingles() in LearningMachine.java
	 */
	@Test
	public void testClassifyShingles()
	{
		int k = 5;
		String arffFilePath = "/data/arff/trainingData";
		String outputFilePath = "/data/arff/trainingDataAll.arff";
		
		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		arffFilePath = relativePath + "/src/main" + arffFilePath;
		outputFilePath = relativePath + "/src/main" + outputFilePath;
		
		ArrayList<String> shingles = new ArrayList<String>();
		
		LearningMachine lm = new LearningMachine();
		try 
		{
			for (int i = 1; i <= 51; i++)
			{
				lm.importARFF(arffFilePath + "" + i + ".arff");
				System.out.println("Imported: " + arffFilePath + "" + i + ".arff");
			}
			
			//lm.importARFF(arffFilePath);
			lm.train();
			lm.printEvaluationSummary();
			//lm.exportARFF(outputFilePath);		
			//lm.saveLM();
			
			Trainer trainer = new Trainer();
			Librarian librarian = new Librarian();
			
			ArrayList<TextBlock> textBlocks = new ArrayList<TextBlock>();

			textBlocks.add(new TextBlock("This textbook was written by John Smith, Sam Jones and Walter H. Brown from George Washington University."));
			textBlocks.add(new TextBlock("I don't know if Professor Steven Smith wants this to be used for George Mason University."));
			
			for (TextBlock tb : textBlocks)
			{
				System.out.println(tb.getTextBlock());
				
				ArrayList<Token> tokens = Trainer.tokenize(tb.getTextBlock());
				
				for (Token token : tokens)
				{
					if (token.getLexical().equals("whiteSpace"))
					{
						tokens.remove(token);
					}
					else
					{
						token = librarian.getFeatures(token);
						
					}
					//System.out.println(token.getLexeme());
					//System.out.println(token.getPosition() + ": " + token.toString());
				}
				
				shingles.add(lm.getShingle(tokens, k));
				System.out.println();
			}
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * test for train() in LearningMAchine.java
	 */
	@Test
	public void testTrainLM() {
		int k = 5;

		long startTime = System.currentTimeMillis();

		String inputFilePath = "/data/testTrainingData.txt";
		String outputFilePath = "/data/arff/testTrainingData";

		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		inputFilePath = relativePath + "/src/main" + inputFilePath;
		outputFilePath = relativePath + "/src/main" + outputFilePath;

		LearningMachine lm = new LearningMachine();

		HashSet<TextBlock> textBlocks = Librarian.importFileHash(inputFilePath);
		HashSet<String> trainingShingles = new HashSet<String>();

		Trainer trainer = new Trainer();
		Librarian librarian = new Librarian();

		// String input = "Hello my name is <PER>John Smith</PER> this is
		// <PER>Mr. Samuel L. Jackson, Jr.</PER> who will be attending George
		// Washington University next summer with <PER>Mrs. Jane M. Doe,
		// Jr.</PER>.";

		ArrayList<Token> featuredTokens = new ArrayList<Token>();

		/*
		 * int count = 1; int totalCount = 0; int fileCount = 1;
		 */

		for (TextBlock tb : textBlocks) {
			/*
			 * if (count == 10000) { totalCount += count;
			 * System.out.println(totalCount + "/" + textBlocks.size());
			 * System.out.println("Elapsed Time: " +
			 * ((System.currentTimeMillis() - startTime) / 1000) + " seconds.");
			 * count = 0;
			 * 
			 * lm.importARFF(trainingShingles); lm.exportARFF(outputFilePath +
			 * "" + fileCount + ".arff"); trainingShingles = null;
			 * trainingShingles = new HashSet<String>(); lm = null; lm = new
			 * LearningMachine(); fileCount++; } count++;
			 */

			ArrayList<Token> tokens = Trainer.tokenize(tb.getTextBlock());

			for (Token token : tokens) {
				token = librarian.getFeatures(token);

				if (!token.getLexical().equals("whiteSpace") || !token.getLexeme().equals(" ")) {
					featuredTokens.add(token);
				}
			}

			trainingShingles.addAll(lm.getTrainingShingles(featuredTokens, 5, false));
		}

		// import the shingle data and train the LM
		lm.importARFF(trainingShingles);
		try {
			lm.train();
			lm.printEvaluationSummary();
			lm.exportARFF(outputFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime) / 1000;

		System.out.println("Elapsed Time: " + elapsedTime + " seconds.");
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
			//assertEquals(71, lm1.getSizeOfAttributes());
			//assertFalse(lm1.getEvalSummary().equals(""));
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
			//assertEquals(71, lm1.getSizeOfAttributes());
			//assertFalse(lm1.getEvalSummary().equals(""));
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
			//assertTrue(lm1.printEvaluationSummary());
			lm2.train();
			//assertTrue(lm2.printEvaluationSummary());

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
			/*
			assertTrue(lm1.getEvalSummary() != null);
			assertFalse(lm1.getEvalSummary().equals(""));

			lm2.train();
			assertTrue(lm2.getEvalSummary() != null);
			assertFalse(lm2.getEvalSummary().equals(""));
			*/
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
			//assertTrue(lm.printARFF());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * test for getShingle() in LearningMachine.java
	 */
	@Test
	public void testGetShingle()
	{
		LearningMachine lm = new LearningMachine();
		String trainingMaterials = "<NER>! -- r John road + $2 N- 1)</NER>\n<NER>\"*</NER>\n<NER>\"China's Pursuit for World Power Status : Is the Transformation of the</NER>";
		Trainer trainer = new Trainer();
		ArrayList<Token> tokens = new ArrayList<Token>();
		tokens = Trainer.tokenize(trainingMaterials);
		String shingle = lm.getShingle(tokens, 11);
		assertEquals(shingle.length(),tokens.size()+22);
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

		/*
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
		 */
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
