package edu.odu.cs.cs350.namex;


import java.io.BufferedReader;
import java.io.FileReader;
import weka.core.Instances;
import weka.core.Attribute;
import java.util.ArrayList;







public class Trainer {
  ArrayList<Attribute> attributes = new ArrayList<Attribute>();
  //Strings for attributes of tokens
  String[] lexicalnames = {"capletter","other","capitalized","punct"};
  String[] partsofspeechnames = {"article", "conjunction", "period", "comma", "hyphen"};
  String[] dictnames = {"yes","no"};
  String[] citiesnames = {"yes","no"};
  String[] countriesnames = {"yes","no"};
  String[] placesnames = {"yes","no"};
  String[] dticfirstnames = {"yes","no"};
  String[] dticlastnames = {"yes","no"};
  String[] commonfirstnames = {"yes","no"};
  String[] commonlastnames = {"yes","no"};
  String[] honorificnames = {"yes","no"};
  String[] prefixnames = {"yes","no"};
  String[] suffixnames = {"yes","no"};
  String[] killnames = {"yes","no"};
  
  attributes.add(new Attribute("lexical" + lexicalnames,lexicalnames4));
  //Attribute lexical= new Attribute("lexical", fastV(lexicalnames));
  
  
  
  
  

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
	public String[] tokenize(String trainingMaterial) {
		return null;
		/*
		 * should be easy enough to implement, java has a StringTokenizer class
		 */ };

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

	
	
  /**
   * Utility to build a FastVector from an array of Strings.
   * (This will be easier in later versions of WEKA where
   * FastVector will be a subclass of ArrayList.)  
   * @param data array of strings
   * @return a FastVector cotnaining those strings
   */

}
