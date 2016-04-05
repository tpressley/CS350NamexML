package edu.odu.cs.cs350.namex;

import java.lang.String;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

	public static void main(String[] args) throws FileNotFoundException
	{
		// Test Output
		/*
        for (String s: args) {
            System.out.println(s);
        }
        */
        
        // if CLI contains one argument that is a textBlock
        if (args.length == 1)
        {
        	String input = args[0];
        	
        	Librarian librarian = new Librarian();
        	Trainer trainer = new Trainer();
        	
        	// separate the input into textBlocks
        	ArrayList<TextBlock> textBlocks = librarian.separateNER(input);
        	
        	// tokenize each textBlock
    		for (TextBlock textBlock : textBlocks)
    		{
    			textBlock.setTokens(trainer.tokenize(textBlock.getTextBlock()));
    			
    			// call classifyTokens only when inputting pre-marked training data
    			textBlock.setTokens(librarian.classifyTokens(textBlock.getTokens()));
    			
    			System.out.println(textBlock.toString()); // test
    			
    			// print out token test arff data
    			for (Token token : textBlock.getTokens())
    			{
    				if (!token.getLexical().equals("whiteSpace") && !token.getLexical().equals("number") && !token.getLexical().equals("punct"))
    				{
    					System.out.println(token.getARFF());
    				}
    			}
    			
    			
    		}    		
        }
        
        // if CLI contains two arguments
        if (args.length == 2)
        {
        	System.out.println("2 Arguments");
        	
        	String inputFileName = args[0];
        	String outputFileName = args[1];
        	
        	Librarian librarian = new Librarian();
        	Trainer trainer = new Trainer();
        	
        	String input = librarian.importFile(inputFileName);

        	System.out.println("Imported File Successfully");
        	
        	// separate the input into textBlocks
        	ArrayList<TextBlock> textBlocks = librarian.separateNER(input);
        	
        	System.out.println("Tokenizing and Classifying " + textBlocks.size() + " text blocks...");
        	
        	// ArrayList to store ARFF data
        	List<String> ARFFList = new ArrayList<String>();
        	        	
        	int count = 0;
        	int totalCount = 0;
        	
        	// tokenize each textBlock
    		for (TextBlock textBlock : textBlocks)
    		{ 			
    			textBlock.setTokens(trainer.tokenize(textBlock.getTextBlock()));
    			
    			// call classifyTokens only when inputting pre-marked training data
    			textBlock.setTokens(librarian.classifyTokens(textBlock.getTokens()));
    			
    			//System.out.println(textBlock.toString()); // test
    			
    			// print out token test arff data
    			for (Token token : textBlock.getTokens())
    			{
    				if (!token.getLexeme().equals("PER") && !token.getLexical().equals("punct") && !token.getLexical().equals("whiteSpace") && !token.getLexical().equals("number") && token.getName() != null)
    				{
    					//System.out.println(token.toString());
    					ARFFList.add(token.getARFF());    					
    				}
    			}
    			
    			count++;
    			if (count == 500)
    			{
    				totalCount += count;
    				System.out.println("Tokenized and Classified " + totalCount + " text blocks so far.");
    				count = 0;
    			}
    		}  
    		
			String[] ARFFArray = new String[ARFFList.size()];
			ARFFArray = ARFFList.toArray(ARFFArray);
        }
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
	
	// imports a file from filePath and returns the line values as a combined String
	private String importFile(String filePath)
	{
		File file = new File(filePath);
		Scanner s;
		
		String input = "";
		
		try 
		{
			s = new Scanner(file);
			
			int count = 0;
			int totalCount = 0;
			
			while (s.hasNext())
			{
				input += s.nextLine();
				
				count++;
				if (count == 500)
				{
					totalCount += count;
					//System.out.println("Read " + totalCount + " total lines so far.");
					count = 0;
				}
				//System.out.println(input);
			}
			s.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return input;
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
	
	// classify token based on its 14 attributes
	public Token classifyToken(Token token)
	{					
		// set the lexicalFeature for the Token
		token.setLexical(getLexicalFeature(token.getLexeme()));
		
		if (!token.getLexical().equals("whiteSpace"))
		{
			// set partOfSpeech
			token.setPartOfSpeech(getPartOfSpeech(token.getLexeme()));
			
			if (token.getLexical().equals("other"))
			{
				// set dictionaryWord
				token.setDictionaryWord(isDictionaryWord(token.getLexeme()));
			}
			
			// only classify remaining Gazetteer if the current Token is a capitalized word
			if (token.getLexical().equals("capitalized"))
			{
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
		else
		{
			token.setPartOfSpeech("other");
		}  
		
		return token;
	}
	
	// classify Tokens as either beginning, continuing, or other for names between <PER></PER>
	private ArrayList<Token> classifyTokens(ArrayList<Token> tks)
	{
		ArrayList<Token> tokens = tks;
		
		boolean isPartOfName = false;
		boolean classifiedNameBeginning = false;
		
		for (int i = 1; i < tokens.size(); i++)
		{			
			if (tokens.get(i).getLexeme().equals("PER") && isPartOfName == false)
			{
				isPartOfName = true;
				classifiedNameBeginning = false;
				tokens.get(i).setName("other");
				//System.out.print("OTHER - ");
			}
			else if (tokens.get(i).getLexeme().equals("PER") && tokens.get(i - 1).getLexeme().equals("/"))
			{
				isPartOfName = false;
				tokens.get(i).setName("other");
				//System.out.print("OTHER - ");
			}
			else if (isPartOfName == true && classifiedNameBeginning == false && (tokens.get(i).getLexical().equals("capitalized")|| tokens.get(i).getLexical().equals("capLetter") || tokens.get(i).getLexical().equals("other") || tokens.get(i).getLexical().equals("allCaps")))
			{
				if (tokens.size() > (i + 1))
				{
					if (tokens.get(i + 1).getLexeme().equals(","))
					{
						tokens.get(i).setName("continuing");
						//System.out.print("CONTINUING - ");
					}
					else
					{
						tokens.get(i).setName("beginning");
						classifiedNameBeginning = true;
						//System.out.print("BEGINNING - ");
					}
				}
			}
			else if (isPartOfName == true && classifiedNameBeginning == true && (tokens.get(i).getLexical().equals("capitalized") || tokens.get(i).getLexical().equals("capLetter") || tokens.get(i).getLexical().equals("other") || tokens.get(i).getLexical().equals("allCaps")))
			{
				tokens.get(i).setName("continuing");
				//System.out.print("CONTINUING - ");
			}
			else
			{
				tokens.get(i).setName("other");
				//System.out.print("OTHER - ");
			}
			
			//System.out.println(tokens.get(i).getLexeme());
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

	/*
	 * below will be done with weka
	 * 
	 * // identify a name of a place correctly public String nameOfPlace(String
	 * textBlock) {
	 * 
	 * // to return a String // e.g. textBlock=
	 * "This article came from George Washington University." // returns
	 * "This article came from George Washington University/Plc."
	 * 
	 * String ret = ""; // return value
	 * 
	 * String plc = "/Plc"; String str1 = ""; // this is an extracted name of
	 * place from textBlock
	 * 
	 * ArrayList<Token> tokenized = new ArrayList<Token>(); tokenized =
	 * this.tokenize(textBlock); // examine input String textBlock // if a place
	 * name is found, // get it and mark /Plc
	 * 
	 * ret += plc; // marking with "/Plc"
	 * 
	 * return ret; }
	 */

	// mark personal names with <PER></PER>
	public String markPersonalNames(String textBlock) {
		Trainer trainer = new Trainer();
		// input = "Name Extraction -- Requirements Definition\nSteven J
		// Zeil\nJan 20, 2016"
		// should return
		// Name Extraction -- Requirements Definition
		// <PER>Steven J Zeil</PER>
		// Jan 20, 2016

		// tokenize textBlock passed into this function
		// if a legitimate personal name is found
		// return original text with the personal name(s) marked with
		// <PER></PER>

		String ret = ""; // return value

		ArrayList<Token> t1 = new ArrayList<Token>();
		t1 = trainer.tokenize(textBlock);
		for (int i = 0; i < t1.size(); i++) {
			if (t1.get(i).isDTICFirst() == 1 || t1.get(i).isDTICLast() == 1 || t1.get(i).isHonorific() == 1
					|| t1.get(i).isCommonFirst() == 1 || t1.get(i).isCommonLast() == 1) {

			}

			ret += t1.get(i).toString();
		}

		String start = "<PER>";
		String end = "</PER>";

		ret += start; // marking <PER>

		// personal name goes here

		ret += end; // marking </PER>

		return ret;
	}

	// return text surrounded with <NER></NER>
	public String markNERtag(String textBlock) {
		Trainer trainer = new Trainer();
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
		ArrayList<Token> tb1 = trainer.tokenize(textBlock);

		ret.add(start); // <NER>

		// then add tokenized version of textBlock
		for (int i = 0; i < tb1.size(); i++) {
			ret.add((tb1.get(i)).toString());
		}

		ret.add(end); // </NER>

		retval = ret.toString();

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