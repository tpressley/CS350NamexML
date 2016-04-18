package edu.odu.cs.cs350.namex;

import java.lang.String;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
	
	LearningMachine lm;
	Trainer trainer;

	/**
	 * default constructor
	 */
	public Librarian() {
		//lm = new LearningMachine();
		//trainer = new Trainer();
		
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
		loadGazetteer(LastNameSuffixes, WordLists.lastNamePrefixes());
		//System.out.println("73");
		loadGazetteer(KillWords, WordLists.nonPersonalIdentifierCues());
		//System.out.println("75");
	}
	
	/**
	 * copy constructor
	 */
	/*
	public Librarian(Librarian toCopy){
		this.lm = toCopy.lm;
		this.trainer = toCopy.trainer;
	}
	*/
	
	/**
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
			
			ArrayList<Token> tokens = Trainer.tokenize(args[0]);           // Step 1: tokenize the input string
			tokens = librarian.getAllFeatures(tokens);                     // Step 2: get the features for each token
			String shingle = lm.getShingle(tokens, k);                     // Step 3: shingle and classify the tokens

			System.out.println(shingle);
		}

		// if CLI contains two arguments
		else if (args.length == 2) {
			System.out.println("2 Arguments");

			String inputFileName = args[0];
			String outputFileName = args[1];

			Trainer trainer = new Trainer();

			ArrayList<Token> tokens = new ArrayList<Token>();

			/*
			 * ArrayList<TextBlock> textBlocks = new ArrayList<TextBlock>();
			 * 
			 * HashSet<String> fileLines = Librarian.importFile(inputFileName);
			 * for (String line : fileLines) {
			 * textBlocks.addAll(Librarian.separateNER(line)); }
			 */

			ArrayList<TextBlock> textBlocks = importFile(inputFileName);

			for (TextBlock textBlock : textBlocks) {
				tokens.addAll(trainer.tokenize(textBlock.getTextBlock()));
			}

			// System.out.println("Imported File Successfully!");

			try {
				//trainer.prepareData(inputFileName, outputFileName, false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Generate ARFF Training Data
		else if (args.length == 3) {
			if (args[0].equalsIgnoreCase("train")) {
				System.out.println("*******************************");
				System.out.println(" Generating ARFF Training Data");
				System.out.println("*******************************\n");

				String inputFileName = args[1];
				String outputFileName = args[2];

				System.out.println(" Input FilePath: " + inputFileName);
				System.out.println("Output FilePath: " + outputFileName);

				Trainer trainer = new Trainer();

				try {
					//trainer.prepareData(inputFileName, outputFileName, false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					System.out.println("*******************************");
					System.out.println(" Saving Learning Machine to LearningMachine.model");
					System.out.println("*******************************\n");
					// trainer.SaveClassifier();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// User Story #861
	// Status - Completed
	
	// CLI processes each <NER> block separately via Extractor
	// User Story #861
	// Status - Completed
	// interface. (A, maybe L)
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
	
	
	// imports a file from filePath and returns the line values as a
	// HashSet<String>
	// public static HashSet<String> importFile(String filePath)
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
	
	// imports a file from filePath and returns the line values as a
	// HashSet<String>
	public static HashSet<TextBlock> importFileHash(String filePath) {
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


	
	// checks to see if the Token is a word found in the English dictionary
	public int isDictionaryWord(String token) {
		if (DictionaryWords.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;

		/*
		 * for (String s : DictionaryWords) { if (s.equalsIgnoreCase(token)) {
		 * return 1; } } return 0;
		 */
	}

	// checks to see if the Token is a known name of a City or State in the
	// United States
	public int isCityState(String token) {
		if (CitiesStates.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;

		/*
		 * for (String s : CitiesStates) { if (s.equalsIgnoreCase(token)) {
		 * return 1; } } return 0;
		 */
	}

	// checks to see if the Token is a known name of a Country or Territory
	public int isCountryTerritory(String token) {
		if (CountriesTerritories.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;

		/*
		 * for (String s : CountriesTerritories) { if
		 * (s.equalsIgnoreCase(token)) { return 1; } } return 0;
		 */
	}

	// checks to see if the Token is a known place
	public int isPlace(String token) {
		if (Places.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;

		/*
		 * for (String s : Places) { if (s.equalsIgnoreCase(token)) { return 1;
		 * } } return 0;
		 */
	}

	// checks to see if the Token is a known DTIC first name
	public int isDTICFirstName(String token) {
		if (DTICFirstNames.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;

		/*
		 * for (String s : DTICFirstNames) { if (s.equalsIgnoreCase(token)) {
		 * return 1; } }
		 * 
		 * return 0;
		 */
	}

	// checks to see if the Token is a known DTIC last name
	public int isDTICLastName(String token) {
		if (DTICLastNames.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;

		/*
		 * for (String s : DTICLastNames) { if (s.equalsIgnoreCase(token)) {
		 * return 1; } } return 0;
		 */
	}

	// checks to see if the Token is a known common first name
	public int isCommonFirstName(String token) {
		if (CommonFirstNames.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;

		/*
		 * for (String s : CommonFirstNames) { if (s.equalsIgnoreCase(token)) {
		 * return 1; } } return 0;
		 */
	}

	// checks to see if the Token is a known common last name
	public int isCommonLastName(String token) {
		if (CommonLastNames.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;

		/*
		 * for (String s : CommonLastNames) { if (s.equalsIgnoreCase(token)) {
		 * return 1; } } return 0;
		 */
	}

	// checks to see if the Token is an honorific
	public int isHonorific(String token) {
		if (Honorifics.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;

		/*
		 * for (String s : Honorifics) { if (s.equalsIgnoreCase(token)) { return
		 * 1; } } return 0;
		 */
	}

	// checks to see if the Token is a prefix
	public int isLastNamePrefix(String token) {
		if (LastNamePrefixes.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;

		/*
		 * for (String s : LastNamePrefixes) { if (s.equalsIgnoreCase(token)) {
		 * return 1; } } return 0;
		 */
	}

	// checks to see if the Token is a suffix
	public int isLastNameSuffix(String token) {
		if (LastNameSuffixes.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;

		/*
		 * for (String s : LastNameSuffixes) { if (s.equalsIgnoreCase(token)) {
		 * return 1; } } return 0;
		 */
	}

	// checks to see if the Token is an kill word
	public int isKillWord(String token) {
		if (KillWords.contains(token.toLowerCase())) {
			return 1;
		}
		return 0;

		/*
		 * for (String s : KillWords) { if (s.equalsIgnoreCase(token)) { return
		 * 1; } } return 0;
		 */
	}



	/**
	 * Idenfies lexical features of a lexeme
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
	 * Returns the part of speech of a token
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
	 * Loads gazetteer lists from WordLists into HashSet<String>
	 * 
	 * @param
	 */
	private void loadGazetteer(HashSet<String> gazetteer, Iterable<String> wordlist) {
		for (String word : wordlist) {
			gazetteer.add(word.toLowerCase());
		}
	}
	
	/**
	 * returns true if two Librarian objects are equal 
	 */
	public boolean equals(Librarian two){
		
		if(two.lm.equals(this.lm) && two.trainer.equals(this.trainer))
			return true;
		
		return false;
	}


	// User Story #856
	// Status - Completed
	// Dictionary features identified correctly (L)
	// User Story #854
	// Status - Completed
	// Misc features (honorifics, kill words, etc) identified correctly. (L)
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