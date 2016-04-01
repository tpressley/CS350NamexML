package edu.odu.cs.cs350.namex;

import java.io.BufferedReader;
import java.io.FileReader;

import weka.core.Instances;
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
			currentWord.setText(tokenizer.nextToken().toString());
			if (currentWord.getText().trim().length() > 0)
				trainingSentence.add(currentWord);
		}
		// @return split training sentence.
		return trainingSentence;

	};

	/**
	 * Uses the tokenized text to convert the text into a form using identifiers
	 * which can be used to train the learning machine
	 * 
	 * @param tokenizedText
	 */
	public void parse(ArrayList<Token> tokenizedText) {
		String[] articles = { "a", "an", "the" };
		String[] conjunctions = { "and", "but" };
		String[] punctuation = { ".", ",", "\"", "\'", ";", ":", "<", ">", "?", "\\", "/", "!", "@", "#", "$", "%", "^",
				"&", "*", "(", ")", "-", "=", "_", "+", "`", "~" };

		//Loop through the entire tokenizedText arraylist
		for(int i = 0; i < tokenizedText.size(); i++)
		{
			//Loop through the entire articles array
			for(int j = 0; j < articles.length; j++)
			{
				//If a token's text is identified as an article, mark it as an article
				if(tokenizedText.get(i).getText() == articles[j])
				{
					tokenizedText.get(i).setArticle(true);
				}
			}
		}
	};

	/**
	 * Returns the the token count for a specific token
	 * @param token
	 */
	public int getTokenCount(Token token, ArrayList<Token> tokenizedText) {
		//todo make this run in O(LogN) or O(1) time keeping a running list of tokens and their counts while actually tokenizing the input
		int tokenCount = 0;
		
		for(int i = 0; i < tokenizedText.size(); i++)
		{
			if(token == tokenizedText.get(i))
			{
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
	 */
	public boolean LoadLM(String fileLoc) {
		// returns true if successful
		return false;
	}

	/**
	 * 
	 * @param fileLoc
	 *            Saves a trained learning machine to a file
	 */
	public boolean SaveLM(String fileLoc) {
		// returns true if successful
		return false;

	}

}
