package edu.odu.cs.cs350.namex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Debug;
import weka.core.FastVector;
import weka.core.Instance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Trainer {

	private Classifier classifier;
	private FastVector attributes;
	private int numberOfAttributes;
	private Instances trainingInstances;
	private String evaluationSummary;

	public Instances getTrainingInstances() {
		return trainingInstances;
	}

	/**
	 * Takes the initial trainingMaterial and converts it into a tokenized form
	 * and outputs the tokenized text split by space and punctuation E.G. Input
	 * "This function's input is split into tokens. It is split by punctuation, it is also split by spaces."
	 * Output: " This function 's input is split into tokens. It is split by
	 * punctuation, it is also split by spaces." I/O should be identical to the
	 * Standford English Tokenizer
	 *
	 * @param inputText
	 */

	public void generateARFF(String inputFileName, String outputFileName) {
		Librarian librarian = new Librarian();

		ArrayList<TextBlock> textBlocks = Librarian.importFile(inputFileName);
		ArrayList<String> arffData = new ArrayList<String>();

		System.out.println("\nClassifying Tokens from " + textBlocks.size() + " TextBlocks...");

		for (TextBlock textBlock : textBlocks) {
			ArrayList<Token> tks = tokenize(textBlock.getTextBlock());

			HashSet<Token> classifiedTokens = librarian.classifyTokens(tks);

			for (Token t : tks) {
				if (t.getLexical() != "whiteSpace") {
					arffData.add(t.getARFF());
				}
			}
		}

		String[] ARFFArray = new String[arffData.size()];
		ARFFArray = arffData.toArray(ARFFArray);

		importARFF(ARFFArray);

		try {
			trainLM();
			// printARFF();
			printEvaluationSummary();
			exportARFF(outputFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Trainer() {
		// Initialize the Classifier as a Naive Bayes Classifier
		classifier = (Classifier) new NaiveBayes();

		// Initialize Attributes
		// Declare Lexical Attribute with its values
		FastVector NominalValLexical = new FastVector(8);
		NominalValLexical.addElement("punct");
		NominalValLexical.addElement("capLetter");
		NominalValLexical.addElement("capitalized");
		NominalValLexical.addElement("allCaps");
		NominalValLexical.addElement("lineFeed");
		NominalValLexical.addElement("whiteSpace");
		NominalValLexical.addElement("number");
		NominalValLexical.addElement("other");
		Attribute Lexical = new Attribute("Lexical", NominalValLexical);

		// Declare PartOfSpeech Attribute with its values
		FastVector NominalValPoS = new FastVector(6);
		NominalValPoS.addElement("article");
		NominalValPoS.addElement("conjunction");
		NominalValPoS.addElement("period");
		NominalValPoS.addElement("comma");
		NominalValPoS.addElement("hyphen");
		NominalValPoS.addElement("other");
		Attribute PartOfSpeech = new Attribute("PartOfSpeech", NominalValPoS);

		// Declare Gazetteer Attributes with its values
		FastVector NominalValGazetteer = new FastVector(2);
		NominalValGazetteer.addElement("0");
		NominalValGazetteer.addElement("1");
		Attribute DictionaryWord = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City = new Attribute("City", NominalValGazetteer);
		Attribute Country = new Attribute("Country", NominalValGazetteer);
		Attribute Places = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill = new Attribute("Kill", NominalValGazetteer);

		// Declare Name Attribute
		FastVector NominalValName = new FastVector(3);
		NominalValName.addElement("beginning");
		NominalValName.addElement("continuing");
		NominalValName.addElement("other");
		Attribute Name = new Attribute("Name", NominalValName);

		// Declare the Feature vector
		attributes = new FastVector(15);
		attributes.addElement(Lexical);
		attributes.addElement(PartOfSpeech);
		attributes.addElement(DictionaryWord);
		attributes.addElement(City);
		attributes.addElement(Country);
		attributes.addElement(Places);
		attributes.addElement(DTICFirst);
		attributes.addElement(DTICLast);
		attributes.addElement(CommonFirst);
		attributes.addElement(CommonLast);
		attributes.addElement(Honorific);
		attributes.addElement(Prefix);
		attributes.addElement(Suffix);
		attributes.addElement(Kill);
		attributes.addElement(Name);

		numberOfAttributes = attributes.size();
	}

	public void trainLM(Instances trainingData) throws Exception {
		classifier.buildClassifier(trainingData);

		// Test the Model
		Evaluation evaluation = new Evaluation(trainingData);
		evaluation.evaluateModel(classifier, trainingData);

		// Print the Evaluation Summary:
		String summary = evaluation.toSummaryString();
		System.out.println(summary);

		// Get the confusion matrix
		double[][] cmMatrix = evaluation.confusionMatrix();
	}

	// builds the classifier based on the ARFF data imported into
	// trainingInstances
	public void trainLM() throws Exception {
		// Build the Classifier
		classifier.buildClassifier(this.trainingInstances);

		// Test the Model
		Evaluation evaluation = new Evaluation(this.trainingInstances);
		evaluation.evaluateModel(classifier, this.trainingInstances);

		// Set the Evaluation Summary
		evaluationSummary = evaluation.toSummaryString();
	}

	// Get the likelihood of each classes
	// distribution[0] is the probability of the Token beginning a name
	// distribution[1] is the probability of the Token continuing a name
	// distribution[2] is the probability of the Token being other
	public double[] getDistribution(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attributes, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numberOfAttributes - 1);
		toClassify.setDataset(classificationInstances);

		toClassify.setValue((Attribute) attributes.elementAt(0), dataToClassify[0]);
		toClassify.setValue((Attribute) attributes.elementAt(1), dataToClassify[1]);
		toClassify.setValue((Attribute) attributes.elementAt(2), dataToClassify[2]);
		toClassify.setValue((Attribute) attributes.elementAt(3), dataToClassify[3]);
		toClassify.setValue((Attribute) attributes.elementAt(4), dataToClassify[4]);
		toClassify.setValue((Attribute) attributes.elementAt(5), dataToClassify[5]);
		toClassify.setValue((Attribute) attributes.elementAt(6), dataToClassify[6]);
		toClassify.setValue((Attribute) attributes.elementAt(7), dataToClassify[7]);
		toClassify.setValue((Attribute) attributes.elementAt(8), dataToClassify[8]);
		toClassify.setValue((Attribute) attributes.elementAt(9), dataToClassify[9]);
		toClassify.setValue((Attribute) attributes.elementAt(10), dataToClassify[10]);
		toClassify.setValue((Attribute) attributes.elementAt(11), dataToClassify[11]);
		toClassify.setValue((Attribute) attributes.elementAt(12), dataToClassify[12]);
		toClassify.setValue((Attribute) attributes.elementAt(13), dataToClassify[13]);

		// Specify that the instance belong to the training set
		// in order to inherit from the set description
		toClassify.setDataset(this.trainingInstances);

		// Get the likelihood of each classes
		double[] distribution = this.classifier.distributionForInstance(toClassify);

		return distribution;
	}

	public String classify(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attributes, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numberOfAttributes - 1);
		toClassify.setDataset(classificationInstances);

		toClassify.setValue((Attribute) attributes.elementAt(0), dataToClassify[0]);
		toClassify.setValue((Attribute) attributes.elementAt(1), dataToClassify[1]);
		toClassify.setValue((Attribute) attributes.elementAt(2), dataToClassify[2]);
		toClassify.setValue((Attribute) attributes.elementAt(3), dataToClassify[3]);
		toClassify.setValue((Attribute) attributes.elementAt(4), dataToClassify[4]);
		toClassify.setValue((Attribute) attributes.elementAt(5), dataToClassify[5]);
		toClassify.setValue((Attribute) attributes.elementAt(6), dataToClassify[6]);
		toClassify.setValue((Attribute) attributes.elementAt(7), dataToClassify[7]);
		toClassify.setValue((Attribute) attributes.elementAt(8), dataToClassify[8]);
		toClassify.setValue((Attribute) attributes.elementAt(9), dataToClassify[9]);
		toClassify.setValue((Attribute) attributes.elementAt(10), dataToClassify[10]);
		toClassify.setValue((Attribute) attributes.elementAt(11), dataToClassify[11]);
		toClassify.setValue((Attribute) attributes.elementAt(12), dataToClassify[12]);
		toClassify.setValue((Attribute) attributes.elementAt(13), dataToClassify[13]);

		// Specify that the instance belong to the training set
		// in order to inherit from the set description
		toClassify.setDataset(this.trainingInstances);

		// Get the likelihood of each classes
		double[] distribution = this.classifier.distributionForInstance(toClassify);

		if (distribution[0] >= distribution[1] && distribution[0] >= distribution[2]) {
			return "beginning";
		} else if (distribution[1] >= distribution[0] && distribution[1] >= distribution[2]) {
			return "continuing";
		} else {
			return "other";
		}
	}

	// print the Evaluation Summary of the classifier
	public void printEvaluationSummary() {
		System.out.println("\n*******************************");
		System.out.println("      Evaluation Summary");
		System.out.println("*******************************");
		System.out.println(this.evaluationSummary);
	}

	// print the values of ARFF data from trainingInstances
	public void printARFF() {
		System.out.println(this.trainingInstances);
	}

	// export the ARFF data from trainingInstances to /data/arff as a .arff file
	public void exportARFF(String outputFilePath) {
		// System.out.println("Exporting ARFF...");

		PrintWriter writer;
		try {
			writer = new PrintWriter(outputFilePath, "UTF-8");
			writer.println(trainingInstances);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Test Output
		System.out.println("Created: " + outputFilePath);
	}

	// import a .arff file from /data folder
	public void importARFF(String filePath) throws Exception {
		/*
		 * Path currentRelativePath = Paths.get(""); String relativePath =
		 * currentRelativePath.toAbsolutePath().toString() + "/data"; String
		 * filePath = relativePath + "/" + fileName;
		 */

		DataSource source = new DataSource(filePath);
		trainingInstances = source.getDataSet();

		// setting class attribute if the data format does not provide this
		// information
		// For example, the XRFF format saves the class attribute information as
		// well
		if (trainingInstances.classIndex() == -1) {
			trainingInstances.setClassIndex(trainingInstances.numAttributes() - 1);
		}
	}

	// import ARFF data from a String[]
	public void importARFF(String[] trainingData) {
		this.trainingInstances = new Instances("Classification", attributes, trainingData.length);

		// Make the last attribute be the class
		this.trainingInstances.setClassIndex(numberOfAttributes - 1);

		for (String sdata : trainingData) {
			// System.out.println(trainingData);

			String[] values = sdata.split(",");

			Instance instance = new Instance(numberOfAttributes);

			instance.setValue((Attribute) attributes.elementAt(0), values[0]);
			instance.setValue((Attribute) attributes.elementAt(1), values[1]);
			instance.setValue((Attribute) attributes.elementAt(2), values[2]);
			instance.setValue((Attribute) attributes.elementAt(3), values[3]);
			instance.setValue((Attribute) attributes.elementAt(4), values[4]);
			instance.setValue((Attribute) attributes.elementAt(5), values[5]);
			instance.setValue((Attribute) attributes.elementAt(6), values[6]);
			instance.setValue((Attribute) attributes.elementAt(7), values[7]);
			instance.setValue((Attribute) attributes.elementAt(8), values[8]);
			instance.setValue((Attribute) attributes.elementAt(9), values[9]);
			instance.setValue((Attribute) attributes.elementAt(10), values[10]);
			instance.setValue((Attribute) attributes.elementAt(11), values[11]);
			instance.setValue((Attribute) attributes.elementAt(12), values[12]);
			instance.setValue((Attribute) attributes.elementAt(13), values[13]);
			instance.setValue((Attribute) attributes.elementAt(14), values[14]);

			this.trainingInstances.add(instance); // Add new instance to
													// training data
		}
	}

	// return ARFF data from trainingInstances as a String
	public String getTrainingData() {
		return trainingInstances.toString();
	}

	public Classifier getClassifier() {
		return classifier;
	}

	public ArrayList<Token> tokenize(String textBlock) {

		// split the string
		String[] tks = textBlock.split("(?=[\" ,.!?\n()-:;@#$%^&*{}<>])|(?<=[\" ,.!?\n()-:;@#$%^&*{}<>])");

		ArrayList<Token> tokens = new ArrayList<Token>();

		for (int i = 0; i < tks.length; i++) {
			tokens.add(new Token(tks[i], i));
		}

		return tokens;
	}

	public ArrayList<Token> tokenize(String textBlock, boolean verbose) {
		// split the string
		String[] tks = textBlock.split("(?=[\" ,.!?\n()-:;@#$%^&*{}<>])|(?<=[\" ,.!?\n()-:;@#$%^&*{}<>])");

		ArrayList<Token> tokens = new ArrayList<Token>();

		for (int i = 0; i < tks.length; i++) {
			tokens.add(new Token(tks[i], i));
			System.out.println(tokens.get(i).toString());
		}

		return tokens;
	}

	/**
	 * Returns the the token count for a specific token
	 * 
	 * @param token
	 */
	public int getTokenCount(Token token, ArrayList<Token> tokenizedText) {
		// todo make this run in O(LogN) or O(1) time keeping a running list of
		// tokens and their counts while actually tokenizing the input
		int tokenCount = 0;

		for (int i = 0; i < tokenizedText.size(); i++) {
			if (token == tokenizedText.get(i)) {
				tokenCount++;
			}
		}
		return tokenCount;
	};

	/**
	 * *
	 * 
	 * @param fileLoc
	 *            Loads a trained learning machine from file
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	public void LoadClassifier() throws FileNotFoundException, IOException, ClassNotFoundException {

		// deserialize model
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("/saved/learningMachine/LearningMachine.model"));
		classifier = (Classifier) ois.readObject();
		ois.close();

	}

	/**
	 * 
	 * @param fileLoc
	 *            Saves a trained learning machine to a file
	 * @throws Exception
	 */
	public void SaveClassifier() throws Exception {

		// serialize model
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("/saved/learningMachine/LearningMachine.model"));
		weka.core.SerializationHelper.write("/saved/learningMachine/LearningMachine.model", classifier);

	}

	/*
	 * For some small integer k, imagine a window consisting of k words in front
	 * of the one we want to classify, the word we want to classify, and then
	 * the k words after the one we want to classify.
	 * 
	 * slide that window from the start of the input to the end, collecting all
	 * the (2k + 1) length sequences that we can obtain.
	 * 
	 * For example, given k = 3 and the input
	 * 
	 * by John Doe, \n Lawrence Livermore Laboratory
	 * 
	 * would yield the sequences
	 * 
	 * null null null by John Doe , null null by John Doe , \n null by John Doe
	 * , \n Lawrence by John Doe , \n Lawrence Livermore John Doe , \n Lawrence
	 * Livermore Laboratory Doe , \n Lawrence Livermore Laboratory null , \n
	 * Lawrence Livermore Laboratory null null \n Lawrence Livermore Laboratory
	 * null null
	 * 
	 * So each input will consist of (2k + 1) words, each word described by m
	 * features for a total of (2km + m) features.
	 * 
	 */

	public ArrayList<ArrayList<Token>> shingle(ArrayList<Token> tokens, int kay) {
		int k = kay;
		int size = tokens.size(); // size of input ArrayList<Token>

		ArrayList<ArrayList<Token>> ret = new ArrayList<ArrayList<Token>>(size); // return
																					// value

		for (int i = 0; i < size; ++i) {
			ArrayList<Token> temp = new ArrayList<Token>(2 * k + 1);

			{
				for (int j = 0; j < k; ++j) // first element will contain k
											// number of 'null's
				{
					temp.add(j, null);
					i++;
				}
				temp.add(i, tokens.get(i - k));
			}

			ret.add(i, temp);
			temp.clear();
		}

		return ret;

	}

}