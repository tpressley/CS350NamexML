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
	private Instances trainingInsts;
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
	 * @param trainingData
	 * @throws Exception
	 */
	public void train(Instances trainingData) throws Exception {
		classifier.buildClassifier(trainingData);

		// Test the Model
		Evaluation evaluation = new Evaluation(trainingData);
		evaluation.evaluateModel(classifier, trainingData);

		// Print the Evaluation Summary:
		String summary = evaluation.toSummaryString();
		System.out.println(summary);

		// Get the confusion matrix
		// double[][] cmMatrix = evaluation.confusionMatrix();
	}

	/**
	 *  builds the classifier based on the ARFF data imported into
	 *  trainingInstances
	 * @throws Exception
	 */
	public void train() throws Exception {
		// Build the Classifier
		classifier.buildClassifier(this.trainingInsts);

		// Test the Model
		Evaluation evaluation = new Evaluation(this.trainingInsts);
		evaluation.evaluateModel(classifier, this.trainingInsts);

		// Set the Evaluation Summary
		evalSummary = evaluation.toSummaryString();
	}

	/** Get the likelihood of each classes
	* distribution[0] is the probability of the Token beginning a name
	* distribution[1] is the probability of the Token continuing a name
	* distribution[2] is the probability of the Token being other
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
		toClassify.setDataset(this.trainingInsts);

		// Get the likelihood of each classes
		double[] distribution = this.classifier.distributionForInstance(toClassify);

		return distribution;
	}
	/**
	 * Classifies tokens to create training data
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
		toClassify.setDataset(this.trainingInsts);

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

		toClassify.setDataset(this.trainingInsts);

		// Get the likelihood of each classes
		double[] distribution = this.classifier.distributionForInstance(toClassify);

		if (distribution[0] >= distribution[1]) {
			return "yes";
		} else {
			return "no";
		}
	}

	/** Get the likelihood of each classes
	* distribution[0] is the probability of the Shingle containing a name
	* distribution[1] is the probability of the Shingle not containing a name
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
		toClassify.setDataset(this.trainingInsts);

		// Get the likelihood of each classes
		double[] distribution = this.classifier.distributionForInstance(toClassify);

		return distribution;
	}
	/**
	 * Depreciated form of the classify function
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public String classifyOld(String input) throws Exception {
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
		toClassify.setDataset(this.trainingInsts);

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
	 *  print the Evaluation Summary of the classifier
	 */
	public void printEvaluationSummary() {
		System.out.println("\n*******************************");
		System.out.println("      Evaluation Summary");
		System.out.println("*******************************");
		System.out.println(this.evalSummary);
	}

	/**
	 *  print the values of ARFF data from trainingInstances
	 */
	public void printARFF() {
		System.out.println(this.trainingInsts);
	}

	/**
	 * Exports training data to an ARFF file
	 * @param outputFilePath
	 */
	public void exportARFF(String outputFilePath) {
		// System.out.println("Exporting ARFF...");

		PrintWriter writer;
		try {
			writer = new PrintWriter(outputFilePath, "UTF-8");
			writer.println(trainingInsts);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// Test Output
		System.out.println("Wrote to: " + outputFilePath);
	}

	/**
	 * Imports an ARFF from a given filepath
	 * @param filePath
	 * @throws Exception
	 */
	public void importARFF(String filePath) throws Exception {

		DataSource source = new DataSource(filePath);
		trainingInsts = source.getDataSet();

		// setting class attribute if the data format does not provide this
		// information
		// For example, the XRFF format saves the class attribute information as
		// well
		if (trainingInsts.classIndex() == -1) {
			trainingInsts.setClassIndex(trainingInsts.numAttributes() - 1);
		}
	}

	/**
	 * Imports an arff file from the given trainingdata HashSet
	 * @param HashSet<string> trainingData
	 */
	public void importARFF(HashSet<String> trainingData) {

		this.trainingInsts = new Instances("Classification", attributes, trainingData.size());

		// Make the last attribute be the class
		this.trainingInsts.setClassIndex(numAttr - 1);

		for (String sdata : trainingData) {
			// System.out.println(trainingData);

			String[] values = sdata.split(",");

			Instance instance = new Instance(numAttr);

			for (int i = 0; i < numAttr; i++) {
				instance.setValue((Attribute) attributes.elementAt(i), values[i]);
			}

			this.trainingInsts.add(instance); // Add new instance to
													// training data
		}
	}

	/**
	 * Imports an arff file from the given training data string array
	 * @param String[] trainingData
	 */
	public void importARFF(String[] trainingData) {
		this.trainingInsts = new Instances("Classification", attributes, trainingData.length);

		// Make the last attribute be the class
		this.trainingInsts.setClassIndex(numAttr - 1);

		for (String sdata : trainingData) {
			// System.out.println(trainingData);

			String[] values = sdata.split(",");

			Instance instance = new Instance(numAttr);

			for (int i = 0; i < numAttr; i++) {
				instance.setValue((Attribute) attributes.elementAt(i), values[i]);
			}

			this.trainingInsts.add(instance); // Add new instance to
													// training data
		}
	} 

	/**
	 *  @return ARFF data from trainingInstances as a String
	 * 
	 */
	/*
	public String getTrainingData() {
		return trainingInsts.toString();
	}
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
		return trainingInsts;
	}

	/**
	 * Commenting out the following code because it seems reduntant and is not used anywhere
	 *  see: Trainer.java: public static LearningMachine loadLearningMachine(String filePath) &&
	 *  Trainer.java: public void saveLearningMachine(String filePath)
	 *  -Tristan
	 */
	/*
	public void saveLM() {

		try {
			System.out.print("Saving Learning Machine to trainedmachine.model");
			weka.core.SerializationHelper.write("trainedmachine.model", classifier);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// Shouldn't this function either return a value or load the 
	// classifier into an object variable? - Tristan
	/**
	 * Are these save/load functions ever actually used anywhere?
	 *
	public void loadLM(String LMBrain) {

		System.out.print("Loading Learning machine from file.");
		try {
			Classifier classifier = (Classifier) weka.core.SerializationHelper.read(LMBrain);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} */
}
