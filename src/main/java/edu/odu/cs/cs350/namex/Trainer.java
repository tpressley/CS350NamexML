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
import weka.core.Debug;

import java.util.ArrayList;
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

	public Classifier getClassifier() {
		return classifier;
	}
	
	public ArrayList<Token> tokenize(String textBlock) {
		
		// split the string
		String[] tks = textBlock.split("(?=[\" ,.!?\n()-:;@#$%^&*{}<>])|(?<=[\" ,.!?\n()-:;@#$%^&*{}<>])");

		ArrayList<Token> tokens = new ArrayList<Token>();
		
		Librarian librarian;
		
		try
		{
			librarian = new Librarian();
			
			for (int i = 0; i < tks.length; i++)
			{
				tokens.add(librarian.classifyToken(new Token(tks[i])));
			}
		}
		catch(Exception e)
		{
			System.err.println("Exception" + e.toString() + " " + e.getMessage());
		}

		return tokens;
	}

	public ArrayList<Token> tokenize(String trainingMaterial, boolean verbose) {
		// split the string
		String[] tks = trainingMaterial.split("(?=[\" ,.!?\n()-:;@#$%^&*{}<>])|(?<=[\" ,.!?\n()-:;@#$%^&*{}<>])");

		ArrayList<Token> tokens = new ArrayList<Token>();
		Librarian librarian = null;
		try {
			librarian = new Librarian();
		} catch (Exception e) {
			System.err.println("Exception" + e.toString() + " " + e.getMessage());
		}
		for (int i = 0; i < tks.length; i++) {
			// store the lexeme in a new Token object
			Token token = new Token(tks[i]);
			// set the lexicalFeature for the Token
			token.setLexical(librarian.getLexicalFeature(token.getLexeme()));
			if (!token.getLexical().equals("whiteSpace")) {
				// set partOfSpeech
				token.setPartOfSpeech(librarian.getPartOfSpeech(tks[i]));
				if (token.getLexical().equals("other")) {
					// set dictionaryWord
					token.setDictionaryWord(librarian.isDictionaryWord(token.getLexeme()));
				}
				// only classify remaining Gazetteer if the current Token is a
				// capitalized word
				if (token.getLexical().equals("capitalized")) {
					// set cityState
					token.setCityState(librarian.isCityState(token.getLexeme()));
					// set countryTerritory
					token.setCountryTerritory(librarian.isCountryTerritory(token.getLexeme()));
					// set place
					token.setPlace(librarian.isPlace(token.getLexeme()));
					// set DTICFirst
					token.setDTICFirst(librarian.isDTICFirstName(token.getLexeme()));
					// set DTICLast
					token.setDTICLast(librarian.isDTICLastName(token.getLexeme()));
					// set commonFirst
					token.setCommonFirst(librarian.isCommonFirstName(token.getLexeme()));
					// set commonLast
					token.setCommonLast(librarian.isCommonLastName(token.getLexeme()));
					// set honorific
					token.setHonorific(librarian.isHonorific(token.getLexeme()));
					// set prefix
					token.setPrefix(librarian.isLastNamePrefix(token.getLexeme()));
					// set suffix
					token.setSuffix(librarian.isLastNameSuffix(token.getLexeme()));
					// set killWord
					token.setKillWord(librarian.isKillWord(token.getLexeme()));
				}
			}
			// add the Token to the ArrayList of Tokens
			tokens.add(token);
			System.out.println("Adding Token" + token.toString());
		}

		return tokens;
	}

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

}