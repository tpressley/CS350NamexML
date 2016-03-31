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
  
  
	public ArrayList<String> tokenize(String trainingMaterial) {
	  ArrayList<String> trainingSentence = new ArrayList<String>();
	  
	  StringTokenizer tokenizer = new StringTokenizer(trainingMaterial, " \t\n\r\f,.:;?![]'",true);
	  
	  //run through sentence, if sentence is not whitespace, add to arraylist
	  while(tokenizer.hasMoreTokens())
	  {
	    String currentWord =tokenizer.nextToken().toString();
	      if (currentWord.trim().length() > 0) 
	        trainingSentence.add(currentWord);
    }
	  //@return split training sentence. 
		return trainingSentence;
		
	  };

	/**
	 * Uses the tokenized text to convert the text into a form using identifiers
	 * which can be used to train the learning machine
	 * 
	 * @param tokenizedText
	 */
	public String parse(String tokenizedText) {
		/*
		 * Still looking into formatting I/O, probably will work like the
		 * Stanford NL Parser
		 */
		return null;
	};

	/**
	 * Returns the the token count for a specific token
	 * 
	 * @param token
	 */
	public int getTokenCount(String token) {

		return 0;
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
