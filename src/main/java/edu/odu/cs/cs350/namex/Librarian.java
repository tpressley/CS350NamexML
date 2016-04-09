package edu.odu.cs.cs350.namex;

import java.lang.String;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
//import weka.core.Instances;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.odu.cs.cs350.namex.Trainer;
import edu.odu.cs.extract.wordlists.WordLists;

public class Librarian {

	// Iterable<String> to that store Gazetteer lists
	private HashSet<String> DictionaryWords;
	private HashSet<String> CitiesStates;
	private HashSet<String> CountriesTerritories;
	private HashSet<String> Places;
	private HashSet<String> DTICFirstNames;
	private HashSet<String> DTICLastNames;
	private HashSet<String> CommonFirstNames;
	private HashSet<String> CommonLastNames;
	private HashSet<String> Honorifics;
	private HashSet<String> LastNamePrefixes;
	private HashSet<String> LastNameSuffixes;
	private HashSet<String> KillWords;

	public static void main(String[] args) throws FileNotFoundException
	{
		// Test Output
		/*
        for (String s: args) {
            System.out.println(s);
        }
        */
		
		if (args.length == 0)
		{
			System.out.println("Hello World!");
		}
        
        // if CLI contains one argument that is a textBlock
		else if (args.length == 1)
        {
        	System.out.println(args[0]);
        }
        
        // if CLI contains two arguments
        else if (args.length == 2)
        {
        	System.out.println("2 Arguments");
        	
        	String inputFileName = args[0];
        	String outputFileName = args[1];
        	
        	Trainer trainer = new Trainer();
        	
        	ArrayList<Token> tokens = new ArrayList<Token>();
        	
        	/*
        	ArrayList<TextBlock> textBlocks = new ArrayList<TextBlock>();
        	
        	HashSet<String> fileLines = Librarian.importFile(inputFileName);
        	for (String line : fileLines)
    		{
    			textBlocks.addAll(Librarian.separateNER(line));
    		}
    		*/
        	
        	ArrayList<TextBlock> textBlocks = importFile(inputFileName);
        	
        	for (TextBlock textBlock : textBlocks)
        	{
        		tokens.addAll(trainer.tokenize(textBlock.getTextBlock()));
        	}
        	
        	//System.out.println("Imported File Successfully!");
        	
        	trainer.generateARFF(inputFileName, outputFileName);
        }
        
        // Generate ARFF Training Data
        else if (args.length == 3)
        {
        	if (args[0].equalsIgnoreCase("train"))
        	{
        		System.out.println("*******************************");
            	System.out.println(" Generating ARFF Training Data");
            	System.out.println("*******************************\n");
            	
            	String inputFileName = args[1];
            	String outputFileName = args[2];
            	
            	System.out.println(" Input FilePath: " + inputFileName);
            	System.out.println("Output FilePath: " + outputFileName);
            	
            	Trainer trainer = new Trainer();
            	
            	trainer.generateARFF(inputFileName, outputFileName);
        	}
        }
	}

	public Librarian() {
		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString() + "/resources/";

		// Instantiate Gazetteer Iterable<String> by using extract-wordlists
		DictionaryWords = new HashSet<String>();
		CitiesStates = new HashSet<String>();
		CountriesTerritories = new HashSet<String>();
		Places = new HashSet<String>();
		DTICFirstNames = new HashSet<String>();
		DTICLastNames = new HashSet<String>();
		CommonFirstNames = new HashSet<String>();
		CommonLastNames = new HashSet<String>();
		Honorifics = new HashSet<String>();
		LastNamePrefixes = new HashSet<String>();
		LastNameSuffixes = new HashSet<String>();
		KillWords = new HashSet<String>();
						
		loadGazetteer(DictionaryWords, WordLists.englishDictionary());
		loadGazetteer(CitiesStates, WordLists.citiesAndStatesUS());
		loadGazetteer(CountriesTerritories, WordLists.countriesAndTerritories());
		loadGazetteer(Places, WordLists.places());
		loadGazetteer(DTICFirstNames, WordLists.firstNames());
		loadGazetteer(DTICLastNames, WordLists.lastNames());
		loadGazetteer(CommonFirstNames, WordLists.commonFirstNames());
		loadGazetteer(CommonLastNames, WordLists.commonLastNames());
		loadGazetteer(Honorifics, WordLists.honorifics());
		loadGazetteer(LastNamePrefixes, WordLists.lastNamePrefixes());
		loadGazetteer(LastNameSuffixes, WordLists.lastNamePrefixes());
		loadGazetteer(KillWords, WordLists.nonPersonalIdentifierCues());

		// Test Output
		//System.out.println("\nAll Gazetteer files imported successfully!\n");
	}
	
	// Loads gazetteer lists from WordLists into HashSet<String>
	private void loadGazetteer(HashSet<String> gazetteer, Iterable<String> wordlist)
	{
		for (String word : wordlist)
		{
			gazetteer.add(word.toLowerCase());
		}
	}
	
	// imports a file from filePath and returns the line values as a HashSet<String>
	//public static HashSet<String> importFile(String filePath)
	public static ArrayList<TextBlock> importFile(String filePath)
	{
		File file = new File(filePath);
		Scanner s;		
		
		ArrayList<TextBlock> textBlocks = new ArrayList<TextBlock>();
			
		try 
		{
			s = new Scanner(file);
			
			int count = 0;
			int totalCount = 0;
			
			while (s.hasNext())
			{
				textBlocks.addAll(separateNER(s.nextLine()));
			}
			s.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return textBlocks;
	}

	// separates an input string containing one or more <NER></NER> text blocks
	// and stores each individual text block in an ArrayList
	public static ArrayList<TextBlock> separateNER(String input) {
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
		String lexeme = token.getLexeme();
		
		token.setLexical(getLexicalFeature(lexeme));
		token.setPartOfSpeech(getPartOfSpeech(lexeme));
		token.setDictionaryWord(isDictionaryWord(lexeme));
		token.setCityState(isCityState(lexeme));
		token.setCountryTerritory(isCountryTerritory(lexeme));
		token.setPlace(isPlace(lexeme));
		token.setDTICFirst(isDTICFirstName(lexeme));
		token.setDTICLast(isDTICLastName(lexeme));
		token.setCommonFirst(isCommonFirstName(lexeme));
		token.setCommonLast(isCommonLastName(lexeme));
		token.setHonorific(isHonorific(lexeme));
		token.setPrefix(isLastNamePrefix(lexeme));
		token.setSuffix(isLastNameSuffix(lexeme));
		token.setKillWord(isKillWord(lexeme));
		
		return token;
	}
	
	// classify Tokens as either beginning, continuing, or other for names between <PER></PER>
	public HashSet<Token> classifyTokens(ArrayList<Token> tokens)
	{		
		boolean isPartOfName = false;
		boolean classifiedNameBeginning = false;
		
		HashSet<Token> arffTokens = new HashSet<Token>();
		
		for (Token token : tokens)
		{
			token = classifyToken(token);
			
			if (token.getLexical().equals("whiteSpace") 
					|| token.getLexeme().equals("")
					|| token.getLexeme().equals(" "))
			{
				token.setName("other");
			}
			else if (token.getLexical().equals("punct")
					|| token.getLexical().equals("lineFeed") 
					|| token.getLexical().equals("number"))
			{
				// comment to prevent the code from sending the ARFF data of this token to weka
				token.setName("other");
				arffTokens.add(token);
			}
			else
			{
				if (token.getLexeme().equals("PER"))
				{
					//System.out.println(token.getLexeme());
					//System.out.println(tokens.get((token.getPosition() - 1)).getLexeme());
					// if the previous token was a '/', then this current token is the closing PER tag
					if (tokens.get((token.getPosition() - 1)).getLexeme().equals("/"))
					{
						// reset the boolean variables
						isPartOfName = false;
						classifiedNameBeginning = false;
					}
					else
					{
						isPartOfName = true;
					}
					
					token.setName("other");
					arffTokens.add(token);
				}
				else
				{
					if (isPartOfName == true)
					{
						if (classifiedNameBeginning == false)
						{
							token.setName("beginning");
							arffTokens.add(token);
							classifiedNameBeginning = true;
						}
						else
						{
							token.setName("continuing");
							arffTokens.add(token);
						}
					}
					else
					{
						token.setName("other");
						arffTokens.add(token);
					}
				}
				//token.setName("Classified");
			}
		}
		//System.out.println(token.toStringQuotes());
	
		

		
		return arffTokens;
	}

	// returns the Lexical attribute for a given token
	public String getLexicalFeature(String token) {
		// ArrayList of puncts
		HashSet<String> puncts = new HashSet<String>();

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
		} 
		else if (token.matches("^[A-Z]{1}$")) // CapLetter
		{
			return "capLetter";
		} 
		else if (token.matches("^[A-Z]{1}[a-z]{1,}$")) // capitalized
		{
			return "capitalized";
		} 
		else if (token.matches("^[A-Z]{1,}$")) // ALL-CAPS
		{
			return "allCaps";
		} 
		else if (token.matches("^\n$")) // lineFeed
		{
			return "lineFeed";
		} 
		else if (token.matches("^ $")) // whiteSpace
		{
			return "whiteSpace";
		} 
		else if (token.matches("^[0-9]{1,}$")) // number
		{
			return "number";
		} 
		else 
		{
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
	public int isDictionaryWord(String token)
	{
		if (DictionaryWords.contains(token.toLowerCase()))
		{
			return 1;
		}
		return 0;
		
		/*
		for (String s : DictionaryWords)
		{
			if (s.equalsIgnoreCase(token))
			{
				return 1;
			}
		}
		return 0;
		*/
	}
	
	// checks to see if the Token is a known name of a City or State in the United States
	public int isCityState(String token)
	{
		if (CitiesStates.contains(token.toLowerCase()))
		{
			return 1;
		}
		return 0;
		
		/*
		for (String s : CitiesStates)
		{
			if (s.equalsIgnoreCase(token))
			{
				return 1;
			}
		}
		return 0;
		*/
	}
	
	// checks to see if the Token is a known name of a Country or Territory
	public int isCountryTerritory(String token)
	{
		if (CountriesTerritories.contains(token.toLowerCase()))
		{
			return 1;
		}
		return 0;
		
		/*
		for (String s : CountriesTerritories)
		{
			if (s.equalsIgnoreCase(token))
			{
				return 1;
			}
		}
		return 0;
		*/
	}
	
	// checks to see if the Token is a known place
	public int isPlace(String token)
	{
		if (Places.contains(token.toLowerCase()))
		{
			return 1;
		}
		return 0;
		
		/*
		for (String s : Places)
		{
			if (s.equalsIgnoreCase(token))
			{
				return 1;
			}
		}
		return 0;
		*/
	}
	
	// checks to see if the Token is a known DTIC first name
	public int isDTICFirstName(String token)
	{
		if (DTICFirstNames.contains(token.toLowerCase()))
		{
			return 1;
		}
		return 0;
		
		/*
		for (String s : DTICFirstNames)
		{
			if (s.equalsIgnoreCase(token))
			{
				return 1;
			}
		}
		
		return 0;
		*/
	}
	
	// checks to see if the Token is a known DTIC last name
	public int isDTICLastName(String token)
	{
		if (DTICLastNames.contains(token.toLowerCase()))
		{
			return 1;
		}
		return 0;
		
		/*
		for (String s : DTICLastNames)
		{
			if (s.equalsIgnoreCase(token))
			{
				return 1;
			}
		}
		return 0;
		*/
	}

	// checks to see if the Token is a known common first name
	public int isCommonFirstName(String token)
	{
		if (CommonFirstNames.contains(token.toLowerCase()))
		{
			return 1;
		}
		return 0;
		
		/*
		for (String s : CommonFirstNames)
		{
			if (s.equalsIgnoreCase(token))
			{
				return 1;
			}
		}
		return 0;
		*/
	}
	
	// checks to see if the Token is a known common last name
	public int isCommonLastName(String token)
	{
		if (CommonLastNames.contains(token.toLowerCase()))
		{
			return 1;
		}
		return 0;
		
		/*
		for (String s : CommonLastNames)
		{
			if (s.equalsIgnoreCase(token))
			{
				return 1;
			}
		}
		return 0;
		*/
	}
	
	// checks to see if the Token is an honorific
	public int isHonorific(String token)
	{
		if (Honorifics.contains(token.toLowerCase()))
		{
			return 1;
		}
		return 0;
		
		/*
		for (String s : Honorifics)
		{
			if (s.equalsIgnoreCase(token))
			{
				return 1;
			}
		}
		return 0;
		*/
	}
	
	// checks to see if the Token is a prefix
	public int isLastNamePrefix(String token)
	{
		if (LastNamePrefixes.contains(token.toLowerCase()))
		{
			return 1;
		}
		return 0;
		
		/*
		for (String s : LastNamePrefixes)
		{
			if (s.equalsIgnoreCase(token))
			{
				return 1;
			}
		}
		return 0;
		*/
	}
	
	// checks to see if the Token is a suffix
	public int isLastNameSuffix(String token)
	{
		if (LastNameSuffixes.contains(token.toLowerCase()))
		{
			return 1;
		}
		return 0;
		
		/*
		for (String s : LastNameSuffixes)
		{
			if (s.equalsIgnoreCase(token))
			{
				return 1;
			}
		}
		return 0;
		*/
	}
	
	// checks to see if the Token is an kill word
	public int isKillWord(String token)
	{
		if (KillWords.contains(token.toLowerCase()))
		{
			return 1;
		}
		return 0;
		
		/*
		for (String s : KillWords)
		{
			if (s.equalsIgnoreCase(token))
			{
				return 1;
			}
		}
		return 0;
		*/
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