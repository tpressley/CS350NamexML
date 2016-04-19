package edu.odu.cs.cs350.namex;

import java.lang.String;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import edu.odu.cs.cs350.namex.Trainer;
import edu.odu.cs.extract.wordlists.WordLists;

public class Librarian {
	
	// Stores Gazetteer lists
	private HashSet<String> CitiesStates;
	private HashSet<String> CommonFirstNames;
	private HashSet<String> CommonLastNames;
	private HashSet<String> CountriesTerritories;
	private HashSet<String> DictionaryWords;
	private HashSet<String> DTICFirstNames;
	private HashSet<String> DTICLastNames;
	private HashSet<String> Honorifics;
	private HashSet<String> KillWords;
	private HashSet<String> LastNamePrefixes;
	private HashSet<String> LastNameSuffixes;
	private HashSet<String> Places;
	
	/**
	 * default constructor for Librarian
	 * 
	 */
	public Librarian() {		
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
		//System.out.println("53");
		loadGazetteer(CitiesStates, WordLists.citiesAndStatesUS());
		//System.out.println("55");
		loadGazetteer(CountriesTerritories, WordLists.countriesAndTerritories());
		//System.out.println("57");
		loadGazetteer(Places, WordLists.places());
		//System.out.println("59");
		loadGazetteer(DTICFirstNames, WordLists.firstNames());
		//System.out.println("61");
		loadGazetteer(DTICLastNames, WordLists.lastNames());
		//System.out.println("63");
		loadGazetteer(CommonFirstNames, WordLists.commonFirstNames());
		//System.out.println("65");
		loadGazetteer(CommonLastNames, WordLists.commonLastNames());
		//System.out.println("67");
		loadGazetteer(Honorifics, WordLists.honorifics());
		//System.out.println("69");
		loadGazetteer(LastNamePrefixes, WordLists.lastNamePrefixes());
		//System.out.println("71");
		loadGazetteer(LastNameSuffixes, WordLists.lastNameSuffixes());
		//System.out.println("73");
		loadGazetteer(KillWords, WordLists.nonPersonalIdentifierCues());
		//System.out.println("75");
	}
	
	/**
	 * copy constructor for Librarian
	 */
	
	public Librarian(Librarian toCopy){
		this.CitiesStates = toCopy.CitiesStates;
		this.DictionaryWords = toCopy.DictionaryWords;
		this.CountriesTerritories = toCopy.CountriesTerritories;
		this.Places = toCopy.Places;
		this.DTICFirstNames = toCopy.DTICFirstNames;
		this.DTICLastNames = toCopy.DTICLastNames;
		this.CommonFirstNames = toCopy.CommonFirstNames;
		this.CommonLastNames = toCopy.CommonLastNames;
		this.Honorifics = toCopy.Honorifics;
		this.LastNamePrefixes = toCopy.LastNamePrefixes;
		this.LastNameSuffixes = toCopy.LastNameSuffixes;
		this.KillWords = toCopy.KillWords;
	}
	
	
	/**
	 * main in Librarian.java
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		// ********** CONFIGURATIONS **********
		
		// file path of the learning machine to be used by main without .ser extension
		String LMFilePath = "/data/lm/learningMachine";   
		
		Path currentRelativePath = Paths.get("");
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		LMFilePath = relativePath + "/src/main" + LMFilePath;
		
		int k = 5;                                                  // default value of k
		
		// ********** END CONFIGURATIONS **********
		
		if (args.length == 0) {
			System.out.println("Hello World!");
		}

		// if CLI contains one argument that is a textBlock
		else if (args.length == 1) {
			
			// print the textblock input
			//System.out.println(args[0]);
			
			// ********** Initialize Variables **********
			Librarian librarian = new Librarian();
			LearningMachine lm = Trainer.loadLM(LMFilePath);
			try {
				//lm.printEvaluationSummary();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                                   // test output
			// ********** End Initialize Variables **********
			// Step 1: tokenize the input string
			ArrayList<Token> tokens = Trainer.tokenize(args[0]);  
			// Step 2: get the features for each token
			tokens = librarian.getAllFeatures(tokens);
			// Step 3: shingle and classify the tokens
			String shingle = lm.getShingle(tokens, k);                     

			System.out.println(shingle);
		}

		// if CLI contains two arguments
		else if (args.length == 2) {
			
			// ********** Initialize Variables **********
			Librarian librarian = new Librarian();
			LearningMachine lm = Trainer.loadLM(LMFilePath);
			
			ArrayList<TextBlock> inputs = Librarian.importFile(args[0]);
			
			try {
				//lm.printEvaluationSummary();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                                            // test output
			// ********** End Initialize Variables **********
			
			try {

				String content = "This is the content to write into file";

				File file = new File(args[1]);

				// if file does not exists, create it
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				
				for (TextBlock input : inputs)
				{
					ArrayList<Token> tokens = Trainer.tokenize(input.getTextBlock());   // Step 1: tokenize the input string
					tokens = librarian.getAllFeatures(tokens);                          // Step 2: get the features for each token
					String shingle = lm.getShingle(tokens, k);                          // Step 3: shingle and classify the tokens
					
					System.out.print(shingle);
					bw.write(shingle + "\n");
				}
				
				bw.close();

				System.out.println("Done");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * returns ArrayList of separated <NER> blocks
	 * @param input
	 * @return
	 */
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
	

	/**
	 * returns ArrayList of imported file from given path
	 * @param filePath
	 * @return
	 */
	public static ArrayList<TextBlock> importFile(String filePath) {
		File file = new File(filePath);
		Scanner s;

		ArrayList<TextBlock> textBlocks = new ArrayList<TextBlock>();

		try {
			s = new Scanner(file);

			while (s.hasNext()) {
				textBlocks.addAll(separateNER(s.nextLine()));
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return textBlocks;
	}
	
	
	
	/**
	 * returns HashSet of imported file
	 * @param filePath
	 * @return
	 */
	public static HashSet<TextBlock> importFileHash(String filePath) 
	{
		File file = new File(filePath);
		Scanner s;

		HashSet<TextBlock> textBlocks = new HashSet<TextBlock>();

		try {
			s = new Scanner(file);

			while (s.hasNext()) {
				textBlocks.addAll(separateNER(s.nextLine()));
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return textBlocks;
	}



	/**
	 * returns '1' if dictionary word
	 * @param token
	 * @return
	 */
	public int isDictionaryWord(String token) {
		if (DictionaryWords.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;
	}

	
	/**
	 * returns '1' if citystate
	 * @param token
	 * @return
	 */
	public int isCityState(String token) {
		if (CitiesStates.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;
	}

	
	/**
	 * returns '1' if countryterritory
	 * @param token
	 * @return
	 */
	public int isCountryTerritory(String token) {
		if (CountriesTerritories.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;
	}

	
	/**
	 * returns '1' if place
	 * @param token
	 * @return
	 */
	public int isPlace(String token) {
		if (Places.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;
	}

	
	/**
	 * returns '1' if DTIC first name
	 * @param token
	 * @return
	 */
	public int isDTICFirstName(String token) {
		if (DTICFirstNames.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;
	}

	
	/**
	 * returns '1' if DTIC last name
	 * @param token
	 * @return
	 */
	public int isDTICLastName(String token) {
		if (DTICLastNames.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;
	}

	
	/**
	 * returns '1' if common first name
	 * @param token
	 * @return
	 */
	public int isCommonFirstName(String token) {
		if (CommonFirstNames.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;
	}

	/**
	 * returns '1' if common last name
	 * @param token
	 * @return
	 */
	public int isCommonLastName(String token) {
		if (CommonLastNames.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;
	}

	
	/**
	 * returns '1' if honorific
	 * @param token
	 * @return
	 */
	public int isHonorific(String token) {
		if (Honorifics.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;
	}

	
	/**
	 * returns '1' if prefix to last name
	 * @param token
	 * @return
	 */
	public int isLastNamePrefix(String token) {
		if (LastNamePrefixes.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;
	}


	/**
	 * returns '1' if last name suffix
	 * @param token
	 * @return
	 */
	public int isLastNameSuffix(String token) {
		if (LastNameSuffixes.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;
	}

	
	/**
	 * returns '1' if killword
	 * @param token
	 * @return
	 */
	public int isKillWord(String token) {
		if (KillWords.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;
	}

	/**
	 * identifies lexical features of a lexeme
	 * 
	 * @param lexeme
	 * @return
	 */
	public String getLexicalFeature(String lexeme) {
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

		if (puncts.contains(lexeme)) // punct
		{
			return "punct";
		} else if (lexeme.matches("^[A-Z]{1}$")) // CapLetter
		{
			return "capLetter";
		} else if (lexeme.matches("^[A-Z]{1}[a-z]{1,}$")) // capitalized
		{
			return "capitalized";
		} else if (lexeme.matches("^[A-Z]{1,}$")) // ALL-CAPS
		{
			return "allCaps";
		} else if (lexeme.matches("^\n$")) // lineFeed
		{
			return "lineFeed";
		} else if (lexeme.matches("^ $")) // whiteSpace
		{
			return "whiteSpace";
		} else if (lexeme.matches("^[0-9]{1,}$")) // number
		{
			return "number";
		} else {
			return "other";
		}

	}

	/**
	 * returns the part of speech of a token
	 * 
	 * @param token
	 * @return
	 */
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

	/**
	 * loads gazetteer lists from WordLists into HashSet<String>
	 * 
	 * @param
	 */
	private void loadGazetteer(HashSet<String> gazetteer, Iterable<String> wordlist) {
		for (String word : wordlist) {
			gazetteer.add(word.toLowerCase());
		}
	}
	
	
	/**
	 * returns true if two librarians are equal
	 * @param two
	 * @return
	 */
	public boolean equals(Librarian two)
	{
		if (this.equals(two))
		{
			return true;
		}
		return false;
	}


	
	/**
	 * returns Token with features set
	 * @param token
	 * @return
	 */
	public Token getFeatures(Token token) {
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
	
	/**
	 * returns ArrayList of tokens to set features
	 * @param tokens
	 * @return
	 */
	public ArrayList<Token> getAllFeatures(ArrayList<Token> tokens) {
		
		for (Token token : tokens)
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
		}

		return tokens;
	}
}