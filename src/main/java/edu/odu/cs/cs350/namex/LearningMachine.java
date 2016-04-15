package edu.odu.cs.cs350.namex;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class LearningMachine implements Serializable {
	private static final long serialVersionUID = 1L;
	private Classifier classifier;
	private FastVector attributes;
	private int numAttr;
	private Instances trainingInstances;
	private String evalSummary;

	public LearningMachine() {
		// Initialize the Classifier as a Naive Bayes Classifier
		classifier = (Classifier) new NaiveBayes();
		// Initialize Attributes
		// Declare Lexical Attribute with its values

		FastVector NominalValLexical = new FastVector(9);

		NominalValLexical.addElement("punct");
		NominalValLexical.addElement("capLetter");
		NominalValLexical.addElement("capitalized");
		NominalValLexical.addElement("allCaps");
		NominalValLexical.addElement("lineFeed");
		NominalValLexical.addElement("whiteSpace");
		NominalValLexical.addElement("number");
		NominalValLexical.addElement("other");
		NominalValLexical.addElement("null");
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

		numAttr = attributes.size();
	}

	public LearningMachine(String machineModel) {
		if (machineModel.equals("new"))
			classifier = (Classifier) new NaiveBayes();
		else
			loadLM(machineModel);

		// Initialize Attributes
		// Declare Lexical Attribute with its values
		FastVector NominalValLexical = new FastVector(9);
		NominalValLexical.addElement("punct");
		NominalValLexical.addElement("capLetter");
		NominalValLexical.addElement("capitalized");
		NominalValLexical.addElement("allCaps");
		NominalValLexical.addElement("lineFeed");
		NominalValLexical.addElement("whiteSpace");
		NominalValLexical.addElement("number");
		NominalValLexical.addElement("other");
		NominalValLexical.addElement("null");
		Attribute Lexical1 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical2 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical3 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical4 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical5 = new Attribute("Lexical", NominalValLexical);

		// Declare PartOfSpeech Attribute with its values
		FastVector NominalValPoS = new FastVector(6);
		NominalValPoS.addElement("article");
		NominalValPoS.addElement("conjunction");
		NominalValPoS.addElement("period");
		NominalValPoS.addElement("comma");
		NominalValPoS.addElement("hyphen");
		NominalValPoS.addElement("other");
		Attribute PartOfSpeech1 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech2 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech3 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech4 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech5 = new Attribute("PartOfSpeech", NominalValPoS);

		// Declare Gazetteer Attributes with its values
		FastVector NominalValGazetteer = new FastVector(2);
		NominalValGazetteer.addElement("0");
		NominalValGazetteer.addElement("1");
		Attribute DictionaryWord1 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City1 = new Attribute("City", NominalValGazetteer);
		Attribute Country1 = new Attribute("Country", NominalValGazetteer);
		Attribute Places1 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst1 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast1 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst1 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast1 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific1 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix1 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix1 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill1 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord2 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City2 = new Attribute("City", NominalValGazetteer);
		Attribute Country2 = new Attribute("Country", NominalValGazetteer);
		Attribute Places2 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst2 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast2 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst2 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast2 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific2 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix2 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix2 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill2 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord3 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City3 = new Attribute("City", NominalValGazetteer);
		Attribute Country3 = new Attribute("Country", NominalValGazetteer);
		Attribute Places3 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst3 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast3 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst3 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast3 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific3 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix3 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix3 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill3 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord4 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City4 = new Attribute("City", NominalValGazetteer);
		Attribute Country4 = new Attribute("Country", NominalValGazetteer);
		Attribute Places4 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst4 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast4 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst4 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast4 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific4 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix4 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix4 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill4 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord5 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City5 = new Attribute("City", NominalValGazetteer);
		Attribute Country5 = new Attribute("Country", NominalValGazetteer);
		Attribute Places5 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst5 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast5 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst5 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast5 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific5 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix5 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix5 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill5 = new Attribute("Kill", NominalValGazetteer);

		// Declare Name Attribute
		FastVector NominalValName = new FastVector(3);
		NominalValName.addElement("beginning");
		NominalValName.addElement("continuing");
		NominalValName.addElement("other");
		Attribute Name = new Attribute("Name", NominalValName);

		// Declare the Feature vector
		attributes = new FastVector(71);
		// word one
		attributes.addElement(Lexical1);
		attributes.addElement(PartOfSpeech1);
		attributes.addElement(DictionaryWord1);
		attributes.addElement(City1);
		attributes.addElement(Country1);
		attributes.addElement(Places1);
		attributes.addElement(DTICFirst1);
		attributes.addElement(DTICLast1);
		attributes.addElement(CommonFirst1);
		attributes.addElement(CommonLast1);
		attributes.addElement(Honorific1);
		attributes.addElement(Prefix1);
		attributes.addElement(Suffix1);
		attributes.addElement(Kill1);
		// word two
		attributes.addElement(Lexical2);
		attributes.addElement(PartOfSpeech2);
		attributes.addElement(DictionaryWord2);
		attributes.addElement(City2);
		attributes.addElement(Country2);
		attributes.addElement(Places2);
		attributes.addElement(DTICFirst2);
		attributes.addElement(DTICLast2);
		attributes.addElement(CommonFirst2);
		attributes.addElement(CommonLast2);
		attributes.addElement(Honorific2);
		attributes.addElement(Prefix2);
		attributes.addElement(Suffix2);
		attributes.addElement(Kill2);

		// word three
		attributes.addElement(Lexical3);
		attributes.addElement(PartOfSpeech3);
		attributes.addElement(DictionaryWord3);
		attributes.addElement(City3);
		attributes.addElement(Country3);
		attributes.addElement(Places3);
		attributes.addElement(DTICFirst3);
		attributes.addElement(DTICLast3);
		attributes.addElement(CommonFirst3);
		attributes.addElement(CommonLast3);
		attributes.addElement(Honorific3);
		attributes.addElement(Prefix3);
		attributes.addElement(Suffix3);
		attributes.addElement(Kill3);
		// word four
		attributes.addElement(Lexical4);
		attributes.addElement(PartOfSpeech4);
		attributes.addElement(DictionaryWord4);
		attributes.addElement(City4);
		attributes.addElement(Country4);
		attributes.addElement(Places4);
		attributes.addElement(DTICFirst4);
		attributes.addElement(DTICLast4);
		attributes.addElement(CommonFirst4);
		attributes.addElement(CommonLast4);
		attributes.addElement(Honorific4);
		attributes.addElement(Prefix4);
		attributes.addElement(Suffix4);
		attributes.addElement(Kill4);

		// word five
		attributes.addElement(Lexical5);
		attributes.addElement(PartOfSpeech5);
		attributes.addElement(DictionaryWord5);
		attributes.addElement(City5);
		attributes.addElement(Country5);
		attributes.addElement(Places5);
		attributes.addElement(DTICFirst5);
		attributes.addElement(DTICLast5);
		attributes.addElement(CommonFirst5);
		attributes.addElement(CommonLast5);
		attributes.addElement(Honorific5);
		attributes.addElement(Prefix5);
		attributes.addElement(Suffix5);
		attributes.addElement(Kill5);

		attributes.addElement(Name);
	}

	public LearningMachine(int k) {
		int dimension = (((2 * k) + 1) * 14) + k + 1;

		// Initialize the Classifier as a Naive Bayes Classifier
		classifier = (Classifier) new NaiveBayes();

		// Initialize Attributes
		// Declare Lexical Attribute with its values
		FastVector NominalValLexical = new FastVector(9);
		NominalValLexical.addElement("punct");
		NominalValLexical.addElement("capLetter");
		NominalValLexical.addElement("capitalized");
		NominalValLexical.addElement("allCaps");
		NominalValLexical.addElement("lineFeed");
		NominalValLexical.addElement("whiteSpace");
		NominalValLexical.addElement("number");
		NominalValLexical.addElement("other");
		NominalValLexical.addElement("null");
		// Attribute Lexical = new Attribute("Lexical", NominalValLexical);

		// Declare PartOfSpeech Attribute with its values
		FastVector NominalValPoS = new FastVector(6);
		NominalValPoS.addElement("article");
		NominalValPoS.addElement("conjunction");
		NominalValPoS.addElement("period");
		NominalValPoS.addElement("comma");
		NominalValPoS.addElement("hyphen");
		NominalValPoS.addElement("other");
		// Attribute PartOfSpeech = new Attribute("PartOfSpeech",
		// NominalValPoS);

		// Declare Gazetteer Attributes with its values
		FastVector NominalValGazetteer = new FastVector(2);
		NominalValGazetteer.addElement("0");
		NominalValGazetteer.addElement("1");

		// Declare Name Attribute
		FastVector NominalValName = new FastVector(3);
		NominalValName.addElement("beginning");
		NominalValName.addElement("continuing");
		NominalValName.addElement("other");
		// Attribute Name = new Attribute("Name", NominalValName);

		// Declare ContainsName Attribute
		FastVector NominalValContainsName = new FastVector(3);
		NominalValContainsName.addElement("yes");
		NominalValContainsName.addElement("no");
		Attribute ContainsName = new Attribute("ContainsName", NominalValContainsName);

		// Declare the Feature vector
		attributes = new FastVector(dimension);

		for (int i = 0; i < ((2 * k) + 1); i++) {
			attributes.addElement(getAttribute("Lexical" + i, NominalValLexical));
			attributes.addElement(getAttribute("PartOfSpeech" + i, NominalValPoS));
			attributes.addElement(getAttribute("DictionaryWord" + i, NominalValGazetteer));
			attributes.addElement(getAttribute("City" + i, NominalValGazetteer));
			attributes.addElement(getAttribute("Country" + i, NominalValGazetteer));
			attributes.addElement(getAttribute("Places" + i, NominalValGazetteer));
			attributes.addElement(getAttribute("DTICFirst" + i, NominalValGazetteer));
			attributes.addElement(getAttribute("DTICLast" + i, NominalValGazetteer));
			attributes.addElement(getAttribute("CommonFirst" + i, NominalValGazetteer));
			attributes.addElement(getAttribute("CommonLast" + i, NominalValGazetteer));
			attributes.addElement(getAttribute("Honorific" + i, NominalValGazetteer));
			attributes.addElement(getAttribute("Prefix" + i, NominalValGazetteer));
			attributes.addElement(getAttribute("Suffix" + i, NominalValGazetteer));
			attributes.addElement(getAttribute("Kill" + i, NominalValGazetteer));
			attributes.addElement(getAttribute("Name" + i, NominalValName));
		}
		attributes.addElement(ContainsName);

		numAttr = attributes.size();
	}

	public long getSerialVersionUID() {

		return serialVersionUID;
	}

	/**
	 * Uses the training data to train the Learning Machine
	 * 
	 * @param trainingData
	 * @throws Exception
	 */
	public boolean train(Instances trainingData) throws Exception {

		try {
			classifier.buildClassifier(trainingData);

			// Test the Model
			Evaluation evaluation = new Evaluation(trainingData);
			evaluation.evaluateModel(classifier, trainingData);

			// Print the Evaluation Summary:
			String summary = evaluation.toSummaryString();
			System.out.println(summary);
			return true;

		} catch (Exception e2) {
			return false;
		}

		// Get the confusion matrix
		// double[][] cmMatrix = evaluation.confusionMatrix();
	}

	/**
	 * builds the classifier based on the ARFF data imported into
	 * trainingInstances
	 * 
	 * @throws Exception
	 */
	public boolean train() throws Exception {

		try {

			// Build the Classifier
			classifier.buildClassifier(this.trainingInstances);

			// Test the Model
			Evaluation eval = new Evaluation(this.trainingInstances);
			eval.evaluateModel(classifier, this.trainingInstances);

			// Set the Evaluation Summary
			evalSummary = eval.toSummaryString();

			return true;

		} catch (Exception e1) {

			e1.printStackTrace();

			return false;
		}
	}

	/**
	 * Get the likelihood of each classes distribution[0] is the probability of
	 * the Token beginning a name distribution[1] is the probability of the
	 * Token continuing a name distribution[2] is the probability of the Token
	 * being other
	 */
	public double[] getDistribution(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attributes, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numAttr - 1);
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

	/**
	 * Classifies tokens to create training data
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public String classify(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attributes, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numAttr - 1);
		toClassify.setDataset(classificationInstances);

		for (int i = 0; i < (numAttr - 1); i++) {
			toClassify.setValue((Attribute) attributes.elementAt(i), dataToClassify[i]);
		}

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

	/**
	 * Creates the singling data from the tokenized input
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public String classifyShingle(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attributes, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numAttr - 1);
		toClassify.setDataset(classificationInstances);

		for (int i = 0; i < (numAttr - 1); i++) {
			// System.out.println("Attribute " + i + " - " + dataToClassify[i]);
			toClassify.setValue((Attribute) attributes.elementAt(i), dataToClassify[i]);
		}

		toClassify.setDataset(this.trainingInstances);

		// Get the likelihood of each classes
		double[] distribution = this.classifier.distributionForInstance(toClassify);

		if (distribution[0] >= distribution[1]) {
			return "yes";
		} else {
			return "no";
		}
	}

	/**
	 * Get the likelihood of each classes distribution[0] is the probability of
	 * the Shingle containing a name distribution[1] is the probability of the
	 * Shingle not containing a name
	 */
	public double[] getShingleDistribution(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attributes, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numAttr - 1);
		toClassify.setDataset(classificationInstances);

		for (int i = 0; i < (numAttr - 1); i++) {
			// System.out.println("Attribute " + i + " - " + dataToClassify[i]);
			toClassify.setValue((Attribute) attributes.elementAt(i), dataToClassify[i]);
		}

		// Specify that the instance belong to the training set
		// in order to inherit from the set description
		toClassify.setDataset(this.trainingInstances);

		// Get the likelihood of each classes
		double[] distribution = this.classifier.distributionForInstance(toClassify);

		return distribution;
	}

	/**
	 * print the Evaluation Summary of the classifier
	 */
	public void printEvaluationSummary() {
		System.out.println("\n*******************************");
		System.out.println("      Evaluation Summary");
		System.out.println("*******************************");
		System.out.println(this.evalSummary);
	}

	/**
	 * print the values of ARFF data from trainingInstances
	 */
	public void printARFF() {
		System.out.println(this.trainingInstances);
	}

	/**
	 * Exports training data to an ARFF file
	 * 
	 * @param outputFilePath
	 */
	public boolean exportARFF(String outputFilePath) {
		// System.out.println("Exporting ARFF...");

		PrintWriter writer;
		try {
			writer = new PrintWriter(outputFilePath, "UTF-8");
			writer.println(trainingInstances);
			writer.close();

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}

		// Test Output
		System.out.println("Wrote to: " + outputFilePath);
		return true;
	}

	/**
	 * Imports an ARFF from a given filepath
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public boolean importARFF(String filePath) throws Exception {

		try {

			DataSource source = new DataSource(filePath);
			trainingInstances = source.getDataSet();

			// setting class attribute if the data format does not provide this
			// information
			// For example, the XRFF format saves the class attribute
			// information as
			// well
			if (trainingInstances.classIndex() == -1) {
				trainingInstances.setClassIndex(trainingInstances.numAttributes() - 1);
			}
			return true;
		} catch (Exception e3) {
			return false;
		}

	}

	/**
	 * Imports an arff file from the given trainingdata HashSet
	 * 
	 * @param HashSet<string>
	 *            trainingData
	 */
	public void importARFF(HashSet<String> trainingData) {

		this.trainingInstances = new Instances("Classification", attributes, trainingData.size());

		// Make the last attribute be the class
		this.trainingInstances.setClassIndex(numAttr - 1);

		for (String sdata : trainingData) {
			// System.out.println(trainingData);

			String[] values = sdata.split(",");

			Instance instance = new Instance(numAttr);

			for (int i = 0; i < numAttr; i++) {
				instance.setValue((Attribute) attributes.elementAt(i), values[i]);
			}

			this.trainingInstances.add(instance); // Add new instance to
													// training data
		}
	}

	/**
	 * Imports an arff file from the given training data string array
	 * 
	 * @param String[]
	 *            trainingData
	 */
	public boolean importARFF(String[] trainingData) throws Exception {

		try {
			this.trainingInstances = new Instances("Classification", attributes, trainingData.length);

			// Make the last attribute be the class
			this.trainingInstances.setClassIndex(numAttr - 1);

			for (String sdata : trainingData) {
				// System.out.println(trainingData);

				String[] values = sdata.split(",");

				Instance instance = new Instance(numAttr);

				for (int i = 0; i < numAttr; i++) {
					instance.setValue((Attribute) attributes.elementAt(i), values[i]);
				}

				this.trainingInstances.add(instance); // Add new instance to
														// training data
			}
			return true;
		} catch (Exception e10) {
			return false;
		}
	}

	/*
	 * public String getTrainingData() { return trainingInsts.toString(); }
	 */

	public Classifier getClassifier() {
		return classifier;
	}

	public Attribute getAttribute(String name, FastVector nominalVal) {
		return new Attribute(name, nominalVal);
	}

	// Returns the number of Attributes being used by the Classifier
	public int getNumberOfAttributes() {
		return numAttr;
	}

	public Instances getTrainingInstances() {
		return trainingInstances;
	}

	/*
	 * Commenting out the following code because it seems redundant and is not
	 * used anywhere see: Trainer.java: public static LearningMachine
	 * loadLearningMachine(String filePath) && Trainer.java: public void
	 * saveLearningMachine(String filePath) -Tristan
	 */
	/*
	 * public void saveLM() {
	 * 
	 * try { System.out.print("Saving Learning Machine to trainedmachine.model"
	 * ); weka.core.SerializationHelper.write("trainedmachine.model",
	 * classifier); } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * } // Shouldn't this function either return a value or load the //
	 * classifier into an object variable? - Tristan /** Are these save/load
	 * functions ever actually used anywhere?
	 *
	 * public void loadLM(String LMBrain) {
	 * 
	 * System.out.print("Loading Learning machine from file."); try { Classifier
	 * classifier = (Classifier) weka.core.SerializationHelper.read(LMBrain); }
	 * catch (Exception e) { e.printStackTrace(); } }
	 */

	public void saveLM() {

		try {
			System.out.print("Saving Learning Machine to trainedmachine.model");
			weka.core.SerializationHelper.write("trainedmachine.model", classifier);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadLM(String LMBrain) {

		System.out.print("Loading Learning machine from file.");
		try {
			Classifier classifier = (Classifier) weka.core.SerializationHelper.read(LMBrain);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
