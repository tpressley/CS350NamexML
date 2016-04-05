package edu.odu.cs.cs350.namex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import weka.core.Instances;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Attribute;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Trainer {

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

	public ArrayList<Token> tokenize(String trainingMaterial) {
		ArrayList<Token> trainingSentence = new ArrayList<Token>();

		StringTokenizer tokenizer = new StringTokenizer(trainingMaterial, " \t\n\r\f,.:;?![]'", true);
		Token currentWord = new Token();
		// run through sentence, if sentence is not whitespace, add to arraylist
		while (tokenizer.hasMoreTokens()) {
			currentWord.setLexeme(tokenizer.nextToken().toString());
			if (currentWord.getLexeme().trim().length() > 0)
				currentWord.setLexeme(tokenizer.nextToken().toString());
			if (currentWord.toString().trim().length() > 0)
				trainingSentence.add(currentWord);
		}
		// @return split training sentence.
		return trainingSentence;

	};

	public ArrayList<Token> tokenize(String trainingMaterial, boolean verbose) {
		ArrayList<Token> trainingSentence = new ArrayList<Token>();

		StringTokenizer tokenizer = new StringTokenizer(trainingMaterial, " \t\n\r\f,.:;?![]'", true);
		Token currentWord = new Token();
		// run through sentence, if sentence is not whitespace, add to arraylist
		while (tokenizer.hasMoreTokens()) {
			currentWord.setLexeme(tokenizer.nextToken().toString());
			if (currentWord.toString().trim().length() > 0) {
				trainingSentence.add(currentWord);
				if (verbose) {
					System.out.println("Adding token:" + currentWord.toString());
				}
			}

		}
		// @return split training sentence.
		return trainingSentence;

	};

	/**
	 * Uses the tokenized text and identifies parts of speech and other lexical
	 * features which can be used to train the learning machine
	 * 
	 * @param tokenizedText
	 */
	public void parse(ArrayList<Token> tokenizedText) {
		String[] articles = { "a", "an", "the", "A", "An", "The" };
		String[] conjunctions = { "and", "but", "And", "But" };
		String[] punctuation = { ".", ",", "\"", "\'", ";", ":", "<", ">", "?", "\\", "/", "!", "@", "#", "$", "%", "^",
				"&", "*", "(", ")", "-", "=", "_", "+", "`", "~" };

		// Loop through the entire tokenizedText arraylist
		for (int i = 0; i < tokenizedText.size(); i++) {
			// Loop through the entire articles array
			for (int j = 0; j < articles.length; j++) {
				// If a token's text is identified as an article, mark it as an
				// article

				if (tokenizedText.get(i).getLexeme() == articles[j]) {
					tokenizedText.get(i).setPartOfSpeech("article");
				}
				if (tokenizedText.get(i).toString() == articles[j]) {
					tokenizedText.get(i).setLexical("punct");

				}
			}
		}
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
	 * Initializes the trainer and gets the training data from std input
	 */
	public Trainer() {

	};

	public String getTrainingData() {

		return null;
	};

	/**
	 * prepare training data for Learning Machine based on input parameter?
	 */
	public String prepareData(String in) {

		return null;

	}

	/**
	 * use existing data to train Learning Machine
	 */
	public boolean trainLM(String data) {
		// returns true if successful
		return false;

	}

	/**
	 * *
	 * 
	 * @param fileLoc
	 *            Loads a trained learning machine from file
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	public boolean LoadLM(String fileLoc) throws FileNotFoundException, IOException, ClassNotFoundException {
		// deserialize model
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("/saved/learningMachine/LearningMachine.model"));
		Classifier cls = (Classifier) ois.readObject();
		ois.close();
		return false;
	}

	/**
	 * 
	 * @param fileLoc
	 *            Saves a trained learning machine to a file
	 * @throws Exception
	 */
	public boolean SaveLM(String fileLoc) throws Exception {
		Classifier cls = new J48();

		// train
		Instances inst = new Instances(new BufferedReader(new FileReader("/saved/arff/trainingMaterials.arff")));
		inst.setClassIndex(inst.numAttributes() - 1);
		cls.buildClassifier(inst);

		// serialize model
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("/saved/learningMachine/LearningMachine.model"));
		weka.core.SerializationHelper.write("/saved/learningMachine/LearningMachine.model", cls);

		oos.close();
		return false;

	}

}