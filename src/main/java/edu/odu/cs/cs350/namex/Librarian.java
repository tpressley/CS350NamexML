package edu.odu.cs.cs350.namex;

import java.lang.String;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
//import weka.core.Instances;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.odu.cs.cs350.namex.Trainer;

public class Librarian {

	// ArrayLists to that store Gazetteer lists
	private ArrayList<String> DictionaryWords;
	private ArrayList<String> CitiesStates;
	private ArrayList<String> CountriesTerritories;
	private ArrayList<String> Places;
	private ArrayList<String> DTICFirstNames;
	private ArrayList<String> DTICLastNames;
	private ArrayList<String> CommonFirstNames;
	private ArrayList<String> CommonLastNames;
	private ArrayList<String> Honorifics;
	private ArrayList<String> LastNamePrefixes;
	private ArrayList<String> LastNameSuffixes;
	private ArrayList<String> KillWords;

	public static void main(String[] args) {
		// if CLI contains one argument that is a textBlock
		if (args.length == 1) {
			String input = args[0];

			Librarian librarian;
			try {
				librarian = new Librarian();

				// separate the input into textBlocks
				ArrayList<TextBlock> textBlocks = librarian.separateNER(input);

				// tokenize each textBlock
				for (TextBlock textBlock : textBlocks) {
					textBlock.setTokens(librarian.tokenize(textBlock.getTextBlock()));

					System.out.println(textBlock.toString()); // test

					// print out token test arff data
					for (Token token : textBlock.getTokens()) {
						if (!token.getLexical().equals("whiteSpace") && !token.getLexical().equals("number")) {
							System.out.println(token.getARFF());
						}
					}
				}
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}

		// if CLI contains two arguments
		if (args.length == 2) {
			String fileName = args[0];
			File file = new File(fileName);

		}

		// Reads user input
		/*
		 * Scanner scanner = new Scanner(System.in); System.out.print(
		 * "Enter a file name: "); System.out.flush(); String filename =
		 * scanner.nextLine(); File file = new File(filename);
		 */

		// Test Output
		/*
		 * for (String s: args) { System.out.println(s); }
		 */

		// Previous Implementation of main
		/*
		 * try { String textToExtract = args[0]; } catch (Exception e) {
		 * System.out .println(e.toString() +
		 * "\nERROR: No argumenets detected, Usage: java -jar jarName \"InputText\""
		 * ); } Trainer trainer = new Trainer(); try { trainer.SaveLM(""); }
		 * catch (Exception e) { System.out.println(
		 * "ERROR: File execption while saving model, Librarian.java:142"); }
		 */
	}

	public Librarian() throws FileNotFoundException {
		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString() + "/resources/";

		// Test Output
		// System.out.println("Importing Gazetteer lists from: " + relativePath
		// + "\n");

		// Instantiate Gazetteer ArrayLists
		DictionaryWords = importGazetteerList(relativePath + "dictionary_words.txt");
		CitiesStates = importGazetteerList(relativePath + "us_cities_states.txt");
		CountriesTerritories = importGazetteerList(relativePath + "countries_territories.txt");
		Places = importGazetteerList(relativePath + "places.txt");
		DTICFirstNames = importGazetteerList(relativePath + "DTIC_first_names.txt");
		DTICLastNames = importGazetteerList(relativePath + "DTIC_last_names.txt");
		CommonFirstNames = importGazetteerList(relativePath + "common_first_names.txt");
		CommonLastNames = importGazetteerList(relativePath + "common_last_names.txt");
		Honorifics = importGazetteerList(relativePath + "honorifics.txt");
		LastNamePrefixes = importGazetteerList(relativePath + "prefixes.txt");
		LastNameSuffixes = importGazetteerList(relativePath + "suffixes.txt");
		KillWords = importGazetteerList(relativePath + "kill_words.txt");

		// Test Output
		// System.out.println("\nAll Gazetteer files imported successfully!\n");
	}

	// import Gazetteer list from .txt file from given file path
	private ArrayList<String> importGazetteerList(String filePath) throws FileNotFoundException {
		// Test Output
		// System.out.println("Importing Data from: " + filePath);

		File file = new File(filePath);
		Scanner s = new Scanner(file);
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNext()) {
			list.add(s.next());
		}
		s.close();
		return list;
	}

	// separates an input string containing one or more <NER></NER> text blocks
	// and stores each individual text block in an ArrayList
	public ArrayList<TextBlock> separateNER(String input) {
		String[] tbs = input.split("<NER>|</NER>");

		ArrayList<TextBlock> textBlocks = new ArrayList<TextBlock>();

		for (String tb : tbs) {
			if (!tb.equals("")) {
				textBlocks.add(new TextBlock(tb));
			}
		}

		return textBlocks;
	}

	// tokenizes the input textBlock and stores the value in Token objects then
	// classifies each Token attribute
	public ArrayList<Token> tokenize(String textBlock) {
		// split the string
		String[] tks = textBlock.split("(?=[\" ,.!?\n()-:;@#$%^&*{}<>])|(?<=[\" ,.!?\n()-:;@#$%^&*{}<>])");

		ArrayList<Token> tokens = new ArrayList<Token>();

		for (int i = 0; i < tks.length; i++) {
			// store the lexeme in a new Token object
			Token token = new Token(tks[i]);

			// set the lexicalFeature for the Token
			token.setLexical(getLexicalFeature(token.getLexeme()));

			if (!token.getLexical().equals("whiteSpace")) {
				// set partOfSpeech
				token.setPartOfSpeech(getPartOfSpeech(tks[i]));

				if (token.getLexical().equals("other")) {
					// set dictionaryWord
					token.setDictionaryWord(isDictionaryWord(token.getLexeme()));
				}

				// only classify remaining Gazetteer if the current Token is a
				// capitalized word
				if (token.getLexical().equals("capitalized")) {
					// set cityState
					token.setCityState(isCityState(token.getLexeme()));

					// set countryTerritory
					token.setCountryTerritory(isCountryTerritory(token.getLexeme()));

					// set place
					token.setPlace(isPlace(token.getLexeme()));

					// set DTICFirst
					token.setDTICFirst(isDTICFirstName(token.getLexeme()));

					// set DTICLast
					token.setDTICLast(isDTICLastName(token.getLexeme()));

					// set commonFirst
					token.setCommonFirst(isCommonFirstName(token.getLexeme()));

					// set commonLast
					token.setCommonLast(isCommonLastName(token.getLexeme()));

					// set honorific
					token.setHonorific(isHonorific(token.getLexeme()));

					// set prefix
					token.setPrefix(isLastNamePrefix(token.getLexeme()));

					// set suffix
					token.setSuffix(isLastNameSuffix(token.getLexeme()));

					// set killWord
					token.setKillWord(isKillWord(token.getLexeme()));
				}
			}

			// add the Token to the ArrayList of Tokens
			tokens.add(token);
		}

		return tokens;
	}

	// returns the Lexical attribute for a given token
	public String getLexicalFeature(String token) {
		// ArrayList of puncts
		ArrayList<String> puncts = new ArrayList<String>();

		puncts.add("!");
		puncts.add("?");
		puncts.add(".");
		puncts.add(",");
		puncts.add(":");
		puncts.add(";");
		puncts.add("(");
		puncts.add(")");
		puncts.add("-");
		puncts.add("\"");
		puncts.add("<");
		puncts.add(">");
		puncts.add("\\");
		puncts.add("/");
		puncts.add("@");
		puncts.add("#");
		puncts.add("$");
		puncts.add("%");
		puncts.add("^");
		puncts.add("&");
		puncts.add("*");
		puncts.add("=");
		puncts.add("_");
		puncts.add("+");
		puncts.add("`");
		puncts.add("~");

		if (puncts.contains(token)) // punct
		{
			return "punct";
		} else if (token.matches("^[A-Z]{1}$")) // CapLetter
		{
			return "capLetter";
		} else if (token.matches("^[A-Z]{1}[a-z]{1,}$")) // capitalized
		{
			return "capitalized";
		} else if (token.matches("^[A-Z]{1,}$")) // ALL-CAPS
		{
			return "allCaps";
		} else if (token.matches("^\n$")) // lineFeed
		{
			return "lineFeed";
		} else if (token.matches("^ $")) // whiteSpace
		{
			return "whiteSpace";
		} else if (token.matches("^[0-9]{1,}$")) // number
		{
			return "number";
		} else {
			return "other";
		}

	}

	// returns the PartOfSpeech attribute for a given token
	public String getPartOfSpeech(String token) {
		ArrayList<String> Articles = new ArrayList<String>();

		Articles.add("a");
		Articles.add("an");
		Articles.add("the");

		if (Articles.contains(token)) {
			return "article";
		} else if (token.equals("and")) {
			return "conjunction";
		} else if (token.equals(".")) {
			return "period";
		} else if (token.equals(",")) {
			return "comma";
		} else if (token.equals("-")) {
			return "hyphen";
		} else {
			return "other";
		}
	}

	// checks to see if the Token is a word found in the English dictionary
	public int isDictionaryWord(String token) {
		if (DictionaryWords.contains(token)) {
			return 1;
		} else {
			return 0;
		}
	}

	// checks to see if the Token is a known name of a City or State in the
	// United States
	public int isCityState(String token) {
		if (CitiesStates.contains(token)) {
			return 1;
		} else {
			return 0;
		}
	}

	// checks to see if the Token is a known name of a Country or Territory
	public int isCountryTerritory(String token) {
		if (CountriesTerritories.contains(token)) {
			return 1;
		} else {
			return 0;
		}
	}

	// checks to see if the Token is a known place
	public int isPlace(String token) {
		if (Places.contains(token)) {
			return 1;
		} else {
			return 0;
		}
	}

	// checks to see if the Token is a known DTIC first name
	public int isDTICFirstName(String token) {
		if (DTICFirstNames.contains(token)) {
			return 1;
		} else {
			return 0;
		}
	}

	// checks to see if the Token is a known DTIC last name
	public int isDTICLastName(String token) {
		if (DTICLastNames.contains(token)) {
			return 1;
		} else {
			return 0;
		}
	}

	// checks to see if the Token is a known common first name
	public int isCommonFirstName(String token) {
		if (CommonFirstNames.contains(token)) {
			return 1;
		} else {
			return 0;
		}
	}

	// checks to see if the Token is a known common last name
	public int isCommonLastName(String token) {
		if (CommonLastNames.contains(token)) {
			return 1;
		} else {
			return 0;
		}
	}

	// checks to see if the Token is an honorific
	public int isHonorific(String token) {
		if (Honorifics.contains(token)) {
			return 1;
		} else {
			return 0;
		}
	}

	// checks to see if the Token is a prefix
	public int isLastNamePrefix(String token) {
		if (LastNamePrefixes.contains(token)) {
			return 1;
		} else {
			return 0;
		}
	}

	// checks to see if the Token is a suffix
	public int isLastNameSuffix(String token) {
		if (LastNameSuffixes.contains(token)) {
			return 1;
		} else {
			return 0;
		}
	}

	// checks to see if the Token is an kill word
	public int isKillWord(String token) {
		if (KillWords.contains(token)) {
			return 1;
		} else {
			return 0;
		}
	}

	public void trainOn(String paragraph) {

	}
	
	
	
	/* below will be done with weka

	// identify a name of a place correctly
	public String nameOfPlace(String textBlock) {

		// to return a String
		// e.g. textBlock="This article came from George Washington University."
		// returns "This article came from George Washington University/Plc."

		String ret = ""; // return value

		String plc = "/Plc";
		String str1 = ""; // this is an extracted name of place from textBlock

		ArrayList<Token> tokenized = new ArrayList<Token>();
		tokenized = this.tokenize(textBlock);
		// examine input String textBlock
		// if a place name is found,
		// get it and mark /Plc

		ret += plc; // marking with "/Plc"

		return ret;
	}
	*/
	
	
	

	// mark personal names with <PER></PER>
	public String markPERtag(String textBlock) {

		// tokenize textBlock passed into this function
		// if a legitimate personal name is found
		// return it marked with <PER></PER>
		// e.g. "<PER>John Doe</PER>"

		String ret = ""; // return value

		String start = "<PER>";
		String end = "</PER>";

		ret += start; // marking <PER>

		return ret;
	}

	//
	public String markNERtag(String textBlock) {

		ArrayList<String> ret = new ArrayList<String>();
		String retval = ""; // return value

		// takes in a string textBlock and mark with tag <NER></NER>

		// e.g. if textBlock = "This document was written in 1983"
		// return "<NER>This document was written in 1983</NER>

		// pseudocode:
		// first break down textBlock string
		// mark beginning of ret <NER>
		// add each token to ArrayList<String> ret
		// mark end of ret </NER>
		String start = "<NER>";
		String end = "</NER>";

		// tokenize textBlock string
		ArrayList<Token> tb1 = this.tokenize(textBlock);

		ret.add(start); // <NER>

		// then add tokenized version of textBlock
		for (int i = 0; i < tb1.size(); i++) {
			ret.add((tb1.get(i)).toString());
		}

		ret.add(end); // </NER>

		return retval;
		// returns block of text marked with <NER> </NER>

	}

	/*
	 * 
	 * // Returns the value of a textBlock within a set of <NER></NER> tags
	 * public String markNERtag(ArrayList<Token> tokens) { // create the output
	 * String and set the initial value to <NER> String output = "<NER>";
	 * 
	 * // add each Token's lexeme value to the output String for (Token token :
	 * tokens) { output += token.getLexeme(); }
	 * 
	 * // close the output String with a </NER> tag output += "</NER>";
	 * 
	 * return output; } then for testMarkNER: String text1 =
	 * "Hello, my name is John Doe."; String assumedText1 =
	 * "<NER>Hello, my name is John Doe.</NER>";
	 * 
	 * String text2 = "This paper was written by Pythagoras."; String
	 * assumedText2 = "<NER>This paper was written by Pythagoras.</NER>";
	 * 
	 * // tokenize each String ArrayList<Token> tokens1 = lib.tokenize(text1);
	 * ArrayList<Token> tokens2 = lib.tokenize(text2);
	 * 
	 * System.out.println(lib.markNERtag(tokens1));
	 * System.out.println(lib.markNERtag(tokens2));
	 * 
	 * assertEquals(assumedText1, lib.markNERtag(tokens1));
	 * assertEquals(assumedText2, lib.markNERtag(tokens2)); change the comment
	 * to markNER to
	 * "returns the values of the inputted ArrayList of Tokens between <NER></NER> tags"
	 * "returns a String value"
	 * 
	 */

	public ArrayList<String> markClassificationTag(String textBlock) {

		ArrayList<String> ret = null; // return value

		// break down textBlock into smaller parts
		// if given token is a noun, return it marked <NOUN>
		// if verb, then return it marked <VERB>
		// if adjective, then return it marked <ADJ>

		return ret;
		// returns text with <NOUN>,<VERB>,<ADJ> tags

	}
}