package edu.odu.cs.cs350.namex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import edu.odu.cs.extract.wordlists.WordLists;

/**
 * @author Tristan
 *
 */
public class Trainer implements Serializable {
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

	public Trainer() {

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
		System.out.println("53");
		loadGazetteer(CitiesStates, WordLists.citiesAndStatesUS());
		System.out.println("55");
		loadGazetteer(CountriesTerritories, WordLists.countriesAndTerritories());
		System.out.println("57");
		loadGazetteer(Places, WordLists.places());
		System.out.println("59");
		loadGazetteer(DTICFirstNames, WordLists.firstNames());
		System.out.println("61");
		loadGazetteer(DTICLastNames, WordLists.lastNames());
		System.out.println("63");
		loadGazetteer(CommonFirstNames, WordLists.commonFirstNames());
		System.out.println("65");
		loadGazetteer(CommonLastNames, WordLists.commonLastNames());
		System.out.println("67");
		loadGazetteer(Honorifics, WordLists.honorifics());
		System.out.println("69");
		loadGazetteer(LastNamePrefixes, WordLists.lastNamePrefixes());
		System.out.println("71");
		loadGazetteer(LastNameSuffixes, WordLists.lastNamePrefixes());
		System.out.println("73");
		loadGazetteer(KillWords, WordLists.nonPersonalIdentifierCues());
		System.out.println("75");

	}

	/**
	 * Prepares shingled tokens to feed to the learning machine
	 * 
	 * @param arffFilePath
	 * @param k
	 *            = number of tokens per shingle
	 * @param inputFileName
	 * @param outputFileName
	 */
	/*
	 * public ArrayList<Shingle> getShingles(ArrayList<Token> tokens, String
	 * nothing) { int k = 5;// default is k = 5
	 * 
	 * int seqLeng = ((2 * k) + 1); ArrayList<Shingle> shingles = new
	 * ArrayList<Shingle>();
	 * 
	 * // add null tokens before and after the text block for (int i = 0; i < k;
	 * i++) {
	 * 
	 * Token nu = new Token("null");
	 * 
	 * tokens.add(0, nu); tokens.add(nu); }
	 * 
	 * for (int i = 0; i < (tokens.size() - (seqLeng - 1)); i++) { //
	 * ArrayList<Token> shingle = new ArrayList<Token>();
	 * 
	 * StringBuilder sb = new StringBuilder(); // for arffdata StringBuilder
	 * sbLexemes = new StringBuilder(); // for lexeme StringBuilder
	 * sbClassifications = new StringBuilder(); // for // classification
	 * 
	 * // int nameCount = 0; // if there are more than two classified names
	 * within the shingle, // classify it as 'yes'
	 * 
	 * for (int j = 0; j < seqLeng; j++) { // // if (tokens.get(j +
	 * i).getName().equals("beginning") || // tokens.get(j +
	 * i).getName().equals("continuing")) { // // nameCount++; } //
	 * 
	 * // System.out.print(shingleTokens.get(j + i).getARFF() + ","); //
	 * shingle.add(shingleTokens.get(j + i));
	 * 
	 * if (j == (seqLeng - 1)) { // if last element do not include ',' // to
	 * append sb.append(tokens.get(j + i).getARFF()); } else {
	 * sb.append(tokens.get(j + i).getARFF() + ","); }
	 * 
	 * sbLexemes.append(tokens.get(j + i).getLexeme() + " ");
	 * sbClassifications.append(tokens.get(j + i).getName() + " "); }
	 * 
	 * Shingle toAdd = new Shingle(sbLexemes.toString(),
	 * sbClassifications.toString(), sb.toString());
	 * 
	 * shingles.add(toAdd); }
	 * 
	 * return shingles; }
	 */

	/**
	 * Prepares shingled tokens to feed to the learning machine Returns data as
	 * a HashSet
	 * 
	 * @param arffFilePath
	 * @param k
	 *            = number of tokens per shingle
	 * @param inputFileName
	 * @param outputFileName
	 */
	/*
	 * public HashSet<String> getShingles(int k, ArrayList<Token> tokens) { int
	 * seqLen = ((2 * k) + 1); HashSet<String> shingles = new HashSet<String>();
	 * 
	 * // add null tokens before and after the text block for (int i = 0; i < k;
	 * i++) {
	 * 
	 * Token nu = new Token("null"); tokens.add(0, nu); tokens.add(nu); }
	 * 
	 * for (int i = 0; i < (tokens.size() - (seqLen - 1)); i++) { //
	 * ArrayList<Token> shingle = new ArrayList<Token>();
	 * 
	 * StringBuilder sb = new StringBuilder(); StringBuilder sbLexeme = new
	 * StringBuilder();
	 * 
	 * // int nameCount = 0; // if there are more than two classified names //
	 * within the shingle, classify it as 'yes' int begCount = 0; int contCount
	 * = 0; int killCount = 0; // # of killWords following a beginning or //
	 * continuing token for (int j = 0; j < seqLen; j++) { // // if
	 * (tokens.get(j + i).getName().equals("beginning") || // tokens.get(j +
	 * i).getName().equals("continuing")) { //nameCount++; } //
	 * 
	 * if (tokens.get(j + i).getName().equals("beginning")) { begCount++; } else
	 * if (tokens.get(j + i).getName().equals("continuing")) { contCount++; }
	 * 
	 * // if there is a killWord following a beginning or continuing // token
	 * 
	 * // if last element is killword if (tokens.get(j + i).isKillWord() == 1) {
	 * 
	 * if (tokens.get(j + i - 1).getName().equals("beginning") || tokens.get(j +
	 * i - 1).getName().equals("continuing")) { killCount++; } }
	 * 
	 * // System.out.print(shingleTokens.get(j + i).getARFF() + ","); //
	 * shingle.add(shingleTokens.get(j + i)); sb.append(tokens.get(j +
	 * i).getARFF() + ","); sbLexeme.append(tokens.get(j + i).getLexeme() + " "
	 * ); }
	 * 
	 * // if (nameCount > 1) if (begCount > 0 && contCount > 0) { // Logic for
	 * manual Shingle training classification // /* // * Scanner reader = new
	 * Scanner(System.in); // Reading from // * System.in // * // * boolean
	 * correctInput = false; // * // * while (correctInput == false) {
	 * System.out.println( // *
	 * "Does the line below contain a personal name? (1 = Yes | 2 = No)" // * );
	 * System.out.println(sbLexeme); String input = // * reader.nextLine(); //
	 * Scans the next token of the input as an // * int. // * // * if
	 * (input.equals("1") || input.isEmpty()) { sb.append("yes"); // *
	 * correctInput = true; } else if (input.equals("2")) { // *
	 * sb.append("no"); correctInput = true; } else { // * System.out.println(
	 * "Incorrect input!"); } // * // * } // *
	 * 
	 * if (killCount > 0) { sb.append("no"); } else { sb.append("yes"); } //
	 * System.out.print("yes"); } else { sb.append("no"); //
	 * System.out.print("no"); }
	 * 
	 * String toAdd = sb.toString();
	 * 
	 * shingles.add(toAdd); }
	 * 
	 * /* // Test Output for (String s : shingles) { System.out.println(s); }
	 *
	 * 
	 * return shingles; }
	 */

	/*
	 * Prepares shingled tokens to feed to the learning machine
	 * 
	 * @param arffFilePath
	 * 
	 * @param k = number of tokens per shingle
	 * 
	 * @param inputFileName
	 * 
	 * @param outputFileName
	 */

	/*
	 * Prepares shingled tokens to feed to the learning machine
	 * 
	 * @param arffFilePath
	 * 
	 * @param k = number of tokens per shingle
	 * 
	 * @param inputFileName
	 * 
	 * @param outputFileName
	 */

	/*
	 * public void prepareShinglingData(String arffFilePath, int k, String
	 * inputFileName, String outputFileName) { Librarian librarian = new
	 * Librarian();
	 * 
	 * // Trainer for Token classification LearningMachine lm = new
	 * LearningMachine();
	 * 
	 * 
	 * 
	 * try { lm.importARFF(arffFilePath); lm.train(); } catch (Exception e1) {
	 * e1.printStackTrace(); }
	 * 
	 * HashSet<TextBlock> textBlocks = Librarian.importFileHash(inputFileName);
	 * 
	 * HashSet<String> shingles = new HashSet<String>();
	 * 
	 * System.out.println("\nShingling " + textBlocks.size() + " TextBlocks..."
	 * );
	 * 
	 * for (TextBlock textBlock : textBlocks) { ArrayList<Token> tks =
	 * tokenize(textBlock.getTextBlock()); ArrayList<Token> classifiedTokens =
	 * new ArrayList<Token>();
	 * 
	 * for (Token t : tks) { t = librarian.getFeatures(t);
	 * 
	 * if (!t.getLexical().equals("whiteSpace")) { try {
	 * t.setName(lm.classify(t.toString())); classifiedTokens.add(t); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 * 
	 * // System.out.println(t.getARFF()); } shingles.addAll(getShingles(k,
	 * classifiedTokens)); }
	 * 
	 * shingleTrainer.importARFF(shingles);
	 * 
	 * try { shingleTrainer.train(); // printARFF();
	 * shingleTrainer.printEvaluationSummary();
	 * shingleTrainer.exportARFF(outputFileName); } catch (Exception e) {
	 * e.printStackTrace(); } } /* /**
	 * 
	 * @param textBlock
	 * 
	 * @return
	 */
	/*
	 * public ArrayList<Token> tokenize(String textBlock) { String[] tks =
	 * textBlock.split(
	 * "(?<=<NER>)|(?=</NER>)|(?<=<PER>)|(?=</PER>)|(?=[ ,.!()<:;}-])|(?<=[ (>{-])"
	 * ); int n = 0; ArrayList<Token> tokens = new ArrayList<Token>();
	 * 
	 * 
	 * return tokens; }
	 */

	/**
	 * Takes the input text and returns an arraylist of basic tokens, containing
	 * only the lexemes Tokens later have features analyzed by
	 * Trainer.getFeatures() Verbose option outputs token.toString() after the
	 * tokens are added to the arraylist Takes the input text and returns an
	 * arraylist of basic tokens, containing only the lexemes Tokens later have
	 * features analyzed by Trainer.getFeatures()
	 * 
	 * @param textBlock
	 * @return
	 */
	public ArrayList<Token> tokenize(ArrayList<String> untokenizedwords) {
		ArrayList<Token> tokens = new ArrayList<Token>();
		int n = 0;
		for (int i = 0; i < untokenizedwords.size(); i++) {
			tokens.add(new Token(untokenizedwords.get(i), i));
		}

		for (int i = 0; i < untokenizedwords.size(); i++) {
			// if (untokenizedwords.get(i) != "<PER>")
			tokens.add(new Token(untokenizedwords.get(i), i));
			// else {
			// while(untokenizedwords.get(i) != "</PER>")
			// {
			// i++;
			// Token tempToken = new Token(untokenizedwords.get(i), i);
			// if(n == 0)
			// {
			// tempToken.setName("beginning");
			// }
			// else
			// {
			// tempToken.setName("continuing");
			// }
			// tokens.add(tempToken);
			// }
			// n = 0;
			// }
		}
		return tokens;
	}

	/**
	 * Takes the input text and returns an arraylist of basic tokens, containing
	 * only the lexemes Tokens later have features analyzed by
	 * Trainer.getFeatures() Verbose option outputs token.toString() after the
	 * tokens are added to the arraylist
	 * 
	 * @param textBlock
	 * @param verbose
	 * @return
	 */
	public ArrayList<Token> tokenize(String textBlock) {
		String[] tks = textBlock.split("(?<=<NER>)|(?=</NER>)|(?<=<PER>)|(?=</PER>)|(?=[ ,.!()<:;}-])|(?<=[ (>{-])");

		ArrayList<Token> tokens = new ArrayList<Token>();

		for (int i = 0; i < tks.length; i++) {
			tokens.add(new Token(tks[i], i));
			System.out.println(tokens.get(i).toString());
		}

		return tokens;
	}

	/**
	 * Returns the the token count for a specific token at index Provided as
	 * part of the interface, unused within the package
	 * 
	 * @param token
	 */
	static public int getTokenCount(int index, ArrayList<Token> tokenizedText) {
		int tokenCount = 0;

		for (int i = 0; i < tokenizedText.size(); i++) {
			if (tokenizedText.get(index) == tokenizedText.get(i)) {
				tokenCount++;
			}
		}
		return tokenCount;
	};

	/**
	 * Trains the loaded Learning Machine
	 * 
	 * @param filePath
	 */
	public void trainLM(String filePath) {
		// try {
		// lm.importARFF(filePath);
		// lm.train();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * checks to see if the Token is a known name of a City or State
	 * 
	 * @param token
	 * @return
	 */
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

	/**
	 * checks to see if the Token is a known common first name
	 * 
	 * @param token
	 * @return
	 */
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

	/**
	 * checks to see if the Token is a known common last name
	 * 
	 * @param token
	 * @return
	 */
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

	/**
	 * checks to see if the Token is a known name of a Country or Territory
	 * 
	 * @param token
	 * @return
	 */
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

	/**
	 * checks to see if the Token is a word found in the English dictionary
	 * 
	 * @param token
	 * @return
	 */
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

	/**
	 * checks to see if the Token is a known DTIC first name
	 * 
	 * @param token
	 * @return
	 */
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

	/**
	 * checks to see if the Token is a known DTIC last name
	 * 
	 * @param token
	 * @return
	 */
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

	/**
	 * checks to see if the Token is an honorific
	 * 
	 * @param token
	 * @return
	 */
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

	/**
	 * checks to see if the Token is an kill word
	 * 
	 * @param token
	 * @return
	 */
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
	 * checks to see if the Token is a prefix
	 * 
	 * @param token
	 * @return
	 */
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

	/**
	 * checks to see if the Token is a suffix
	 * 
	 * @param token
	 * @return
	 */
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

	/**
	 * checks to see if the Token is a known place
	 * 
	 * @param token
	 * @return
	 */
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

	/**
	 * Sets the features of a token
	 * 
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
	 * Sets the features of all tokens in a list
	 * 
	 * @param token
	 * @return
	 */
	public void getFeatures(ArrayList<Token> tokens) {

		for (int i = 0; i < tokens.size(); i++) {
			String lexeme = tokens.get(i).getLexeme();

			tokens.get(i).setLexical(getLexicalFeature(lexeme));
			tokens.get(i).setPartOfSpeech(getPartOfSpeech(lexeme));
			tokens.get(i).setDictionaryWord(isDictionaryWord(lexeme));
			tokens.get(i).setCityState(isCityState(lexeme));
			tokens.get(i).setCountryTerritory(isCountryTerritory(lexeme));
			tokens.get(i).setPlace(isPlace(lexeme));
			tokens.get(i).setDTICFirst(isDTICFirstName(lexeme));
			tokens.get(i).setDTICLast(isDTICLastName(lexeme));
			tokens.get(i).setCommonFirst(isCommonFirstName(lexeme));
			tokens.get(i).setCommonLast(isCommonLastName(lexeme));
			tokens.get(i).setHonorific(isHonorific(lexeme));
			tokens.get(i).setPrefix(isLastNamePrefix(lexeme));
			tokens.get(i).setSuffix(isLastNameSuffix(lexeme));
			tokens.get(i).setKillWord(isKillWord(lexeme));

		}
	}

	/**
	 * Sets the features of all tokens in a list and outputs a hashSet
	 * 
	 * @param token
	 * @return
	 */
	public HashSet<Token> setFeatures(ArrayList<Token> tokens) {
		boolean isPartOfName = false;
		boolean taggedBeginning = false;

		HashSet<Token> arffTokens = new HashSet<Token>();
		HashSet<Token> beginningTokens = new HashSet<Token>();

		for (int i = 0; i < tokens.size(); i++) {
			tokens.get(i).setName(getFeatures(tokens.get(i)).getName());

			if (tokens.get(i).getLexical().equals("whiteSpace")) {
				tokens.get(i).setName("other");
			} else {
				if (tokens.get(i).getLexeme().equals("<PER>")) {
					isPartOfName = true;
					tokens.get(i).setName("other");
					tokens.remove(i);
				} else if (tokens.get(i).getLexeme().equals("</PER>")) {
					isPartOfName = false;
					taggedBeginning = false;
					tokens.remove(i);
					tokens.get(i).setName("other");
				}
				if (isPartOfName == true) {
					if (tokens.get(i).getLexical().equals("capitalized")
							|| tokens.get(i).getLexical().equals("capLetter")
							|| tokens.get(i).getLexical().equals("allCaps")) {
						if (taggedBeginning == false) {
							if ((i + 1) >= tokens.size()) {
								if (tokens.get(i + 1).getPartOfSpeech().equals("comma")) {
									tokens.get(i).setName("continuing");
									break;
								}
							} else {
								tokens.get(i).setName("beginning");
								taggedBeginning = true;
							}
						} else if (taggedBeginning == true) {
							if (tokens.get(i).isHonorific() == 1) {
								tokens.get(i).setName("beginning");
							} else if (tokens.get(i).isSuffix() == 1) {
								tokens.get(i).setName("continuing");
							} else {
								tokens.get(i).setName("continuing");
							}
						}
					} else if (tokens.get(i).isPrefix() == 1) {
						tokens.get(i).setName("continuing");
						beginningTokens.add(tokens.get(i));
					} else if (tokens.get(i).isHonorific() == 1) {
						tokens.get(i).setName("continuing");
						beginningTokens.add(tokens.get(i));
					} else {
						tokens.get(i).setName("other");
					}
				} else {
					tokens.get(i).setName("other");
				}

				arffTokens.add(tokens.get(i));
				// System.out.println(token.getARFF());
			}

			for (Token t : arffTokens) {
				if (!t.getName().equals("beginning") && !t.getName().equals("continuing")
						&& !t.getName().equals("other")) {
					// System.out.println(t.getLexeme() + " " +
					// t.toStringQuotes());
					System.out.println(t.getLexeme());
				}
			}

		}
		// System.out.println(token.toStringQuotes());

		return arffTokens;
	}

	/**
	 * Creates an ARFF file to train the learning machine
	 * 
	 * @param shingles
	 * @return
	 */
	public LinkedList<String> createArff(LinkedList<Shingle> shingles) {
		LinkedList<String> arffString = new LinkedList<String>();

		arffString.add("@relation Classification");

		arffString
				.add("@attribute Lexical1 {punct,capLetter,capitalized,allCaps,lineFeed,whiteSpace,number,other,null}");
		arffString.add("@attribute PartOfSpeech1 {article,conjunction,period,comma,hyphen,other,null}");
		arffString.add("@attribute DictionaryWord1 {0,1}");
		arffString.add("@attribute City1 {0,1}");
		arffString.add("@attribute Country1 {0,1}");
		arffString.add("@attribute Places1 {0,1}");
		arffString.add("@attribute DTICFirst1 {0,1}");
		arffString.add("@attribute DTICLast1 {0,1}");
		arffString.add("@attribute CommonFirst1 {0,1}");
		arffString.add("@attribute CommonLast1 {0,1}");
		arffString.add("@attribute Honorific1 {0,1}");
		arffString.add("@attribute Prefix1 {0,1}");
		arffString.add("@attribute Suffix1 {0,1}");
		arffString.add("@attribute Kill1 {0,1}");

		arffString
				.add("@attribute Lexical2 {punct,capLetter,capitalized,allCaps,lineFeed,whiteSpace,number,other,null}");
		arffString.add("@attribute PartOfSpeech2 {article,conjunction,period,comma,hyphen,other,null}");
		arffString.add("@attribute DictionaryWord2 {0,1}");
		arffString.add("@attribute City2 {0,1}");
		arffString.add("@attribute Country2 {0,1}");
		arffString.add("@attribute Places2 {0,1}");
		arffString.add("@attribute DTICFirst2 {0,1}");
		arffString.add("@attribute DTICLast2 {0,1}");
		arffString.add("@attribute CommonFirst2 {0,1}");
		arffString.add("@attribute CommonLast2 {0,1}");
		arffString.add("@attribute Honorific2 {0,1}");
		arffString.add("@attribute Prefix2 {0,1}");
		arffString.add("@attribute Suffix2 {0,1}");
		arffString.add("@attribute Kill2 {0,1}");

		arffString
				.add("@attribute Lexical3 {punct,capLetter,capitalized,allCaps,lineFeed,whiteSpace,number,other,null}");
		arffString.add("@attribute PartOfSpeech3 {article,conjunction,period,comma,hyphen,other,null}");
		arffString.add("@attribute DictionaryWord3 {0,1}");
		arffString.add("@attribute City3 {0,1}");
		arffString.add("@attribute Country3 {0,1}");
		arffString.add("@attribute Places3 {0,1}");
		arffString.add("@attribute DTICFirst3 {0,1}");
		arffString.add("@attribute DTICLast3 {0,1}");
		arffString.add("@attribute CommonFirst3 {0,1}");
		arffString.add("@attribute CommonLast3 {0,1}");
		arffString.add("@attribute Honorific3 {0,1}");
		arffString.add("@attribute Prefix3 {0,1}");
		arffString.add("@attribute Suffix3 {0,1}");
		arffString.add("@attribute Kill3 {0,1}");

		arffString
				.add("@attribute Lexical4 {punct,capLetter,capitalized,allCaps,lineFeed,whiteSpace,number,other,null}");
		arffString.add("@attribute PartOfSpeech4 {article,conjunction,period,comma,hyphen,other,null}");
		arffString.add("@attribute DictionaryWord4 {0,1}");
		arffString.add("@attribute City4 {0,1}");
		arffString.add("@attribute Country4 {0,1}");
		arffString.add("@attribute Places4 {0,1}");
		arffString.add("@attribute DTICFirst4 {0,1}");
		arffString.add("@attribute DTICLast4 {0,1}");
		arffString.add("@attribute CommonFirst4 {0,1}");
		arffString.add("@attribute CommonLast4 {0,1}");
		arffString.add("@attribute Honorific4 {0,1}");
		arffString.add("@attribute Prefix4 {0,1}");
		arffString.add("@attribute Suffix4 {0,1}");
		arffString.add("@attribute Kill4 {0,1}");

		arffString
				.add("@attribute Lexical5 {punct,capLetter,capitalized,allCaps,lineFeed,whiteSpace,number,other,null}");
		arffString.add("@attribute PartOfSpeech5 {article,conjunction,period,comma,hyphen,other,null}");
		arffString.add("@attribute DictionaryWord5 {0,1}");
		arffString.add("@attribute City5 {0,1}");
		arffString.add("@attribute Country5 {0,1}");
		arffString.add("@attribute Places5 {0,1}");
		arffString.add("@attribute DTICFirst5 {0,1}");
		arffString.add("@attribute DTICLast5 {0,1}");
		arffString.add("@attribute CommonFirst5 {0,1}");
		arffString.add("@attribute CommonLast5 {0,1}");
		arffString.add("@attribute Honorific5 {0,1}");
		arffString.add("@attribute Prefix5 {0,1}");
		arffString.add("@attribute Suffix5 {0,1}");
		arffString.add("@attribute Kill5 {0,1}");

		arffString
				.add("@attribute Lexical6 {punct,capLetter,capitalized,allCaps,lineFeed,whiteSpace,number,other,null}");
		arffString.add("@attribute PartOfSpeech6 {article,conjunction,period,comma,hyphen,other,null}");
		arffString.add("@attribute DictionaryWord6 {0,1}");
		arffString.add("@attribute City6 {0,1}");
		arffString.add("@attribute Country6 {0,1}");
		arffString.add("@attribute Places6 {0,1}");
		arffString.add("@attribute DTICFirst6 {0,1}");
		arffString.add("@attribute DTICLast6 {0,1}");
		arffString.add("@attribute CommonFirst6 {0,1}");
		arffString.add("@attribute CommonLast6 {0,1}");
		arffString.add("@attribute Honorific6 {0,1}");
		arffString.add("@attribute Prefix6 {0,1}");
		arffString.add("@attribute Suffix6 {0,1}");
		arffString.add("@attribute Kill6 {0,1}");

		arffString
				.add("@attribute Lexical7 {punct,capLetter,capitalized,allCaps,lineFeed,whiteSpace,number,other,null}");
		arffString.add("@attribute PartOfSpeech7 {article,conjunction,period,comma,hyphen,other,null}");
		arffString.add("@attribute DictionaryWord7 {0,1}");
		arffString.add("@attribute City7 {0,1}");
		arffString.add("@attribute Country7 {0,1}");
		arffString.add("@attribute Places7 {0,1}");
		arffString.add("@attribute DTICFirst7 {0,1}");
		arffString.add("@attribute DTICLast7 {0,1}");
		arffString.add("@attribute CommonFirst7 {0,1}");
		arffString.add("@attribute CommonLast7 {0,1}");
		arffString.add("@attribute Honorific7 {0,1}");
		arffString.add("@attribute Prefix7 {0,1}");
		arffString.add("@attribute Suffix7 {0,1}");
		arffString.add("@attribute Kill7 {0,1}");

		arffString
				.add("@attribute Lexical8 {punct,capLetter,capitalized,allCaps,lineFeed,whiteSpace,number,other,null}");
		arffString.add("@attribute PartOfSpeech8 {article,conjunction,period,comma,hyphen,other,null}");
		arffString.add("@attribute DictionaryWord8 {0,1}");
		arffString.add("@attribute City8 {0,1}");
		arffString.add("@attribute Country8 {0,1}");
		arffString.add("@attribute Places8 {0,1}");
		arffString.add("@attribute DTICFirst8 {0,1}");
		arffString.add("@attribute DTICLast8 {0,1}");
		arffString.add("@attribute CommonFirst8 {0,1}");
		arffString.add("@attribute CommonLast8 {0,1}");
		arffString.add("@attribute Honorific8 {0,1}");
		arffString.add("@attribute Prefix8 {0,1}");
		arffString.add("@attribute Suffix8 {0,1}");
		arffString.add("@attribute Kill8 {0,1}");

		arffString
				.add("@attribute Lexical9 {punct,capLetter,capitalized,allCaps,lineFeed,whiteSpace,number,other,null}");
		arffString.add("@attribute PartOfSpeech9 {article,conjunction,period,comma,hyphen,other,null}");
		arffString.add("@attribute DictionaryWord9 {0,1}");
		arffString.add("@attribute City9 {0,1}");
		arffString.add("@attribute Country9 {0,1}");
		arffString.add("@attribute Places9 {0,1}");
		arffString.add("@attribute DTICFirst9 {0,1}");
		arffString.add("@attribute DTICLast9 {0,1}");
		arffString.add("@attribute CommonFirst9 {0,1}");
		arffString.add("@attribute CommonLast9 {0,1}");
		arffString.add("@attribute Honorific9 {0,1}");
		arffString.add("@attribute Prefix9 {0,1}");
		arffString.add("@attribute Suffix9 {0,1}");
		arffString.add("@attribute Kill9 {0,1}");

		arffString.add(
				"@attribute Lexical10 {punct,capLetter,capitalized,allCaps,lineFeed,whiteSpace,number,other,null}");
		arffString.add("@attribute PartOfSpeech10 {article,conjunction,period,comma,hyphen,other,null}");
		arffString.add("@attribute DictionaryWord10 {0,1}");
		arffString.add("@attribute City10 {0,1}");
		arffString.add("@attribute Country10 {0,1}");
		arffString.add("@attribute Places10 {0,1}");
		arffString.add("@attribute DTICFirst10 {0,1}");
		arffString.add("@attribute DTICLast10 {0,1}");
		arffString.add("@attribute CommonFirst10 {0,1}");
		arffString.add("@attribute CommonLast10 {0,1}");
		arffString.add("@attribute Honorific10 {0,1}");
		arffString.add("@attribute Prefix10 {0,1}");
		arffString.add("@attribute Suffix10 {0,1}");
		arffString.add("@attribute Kill10 {0,1}");

		arffString.add(
				"@attribute Lexical11 {punct,capLetter,capitalized,allCaps,lineFeed,whiteSpace,number,other,null}");
		arffString.add("@attribute PartOfSpeech11 {article,conjunction,period,comma,hyphen,other,null}");
		arffString.add("@attribute DictionaryWord11 {0,1}");
		arffString.add("@attribute City11 {0,1}");
		arffString.add("@attribute Country11 {0,1}");
		arffString.add("@attribute Places11 {0,1}");
		arffString.add("@attribute DTICFirst11 {0,1}");
		arffString.add("@attribute DTICLast11 {0,1}");
		arffString.add("@attribute CommonFirst11 {0,1}");
		arffString.add("@attribute CommonLast11 {0,1}");
		arffString.add("@attribute Honorific11 {0,1}");
		arffString.add("@attribute Prefix11 {0,1}");
		arffString.add("@attribute Suffix11 {0,1}");
		arffString.add("@attribute Kill11 {0,1}");

		arffString.add("@attribute Name1 {beginning,continuing,other,null}");
		arffString.add("@attribute Name2 {beginning,continuing,other,null}");
		arffString.add("@attribute Name3 {beginning,continuing,other,null}");
		arffString.add("@attribute Name4 {beginning,continuing,other,null}");
		arffString.add("@attribute Name5 {beginning,continuing,other,null}");
		arffString.add("@attribute Name6 {beginning,continuing,other,null}");

		arffString.add("\n");

		for (int i = 0; i < shingles.size(); i++) {
			// System.out.println(shingles.size());
			for (int j = 0; j < shingles.get(i).getTokens().size(); j++) {
				arffString.add(shingles.get(i).getTokens().get(j).toString());
				// System.out.print(shingles.get(i).getTokens().get(j).toString());
			}
			String tempString = shingles.get(1).getTokens().get(1).getName()
					+ shingles.get(1).getTokens().get(2).getName() + shingles.get(1).getTokens().get(3).getName()
					+ shingles.get(1).getTokens().get(4).getName() + shingles.get(1).getTokens().get(5).getName()
					+ shingles.get(1).getTokens().get(6).getName();
			arffString.add(tempString + "\n");
			if (i % 500 == 0)
				System.out.println("Added line" + i);
		}
		return arffString;
	}

	/**
	 * Generates an arrayList of shingles from the tokenized input
	 * 
	 * @param tokens
	 * @return
	 */
	public LinkedList<Shingle> getShingles(ArrayList<Token> tokens) {
		LinkedList<Shingle> shingles = new LinkedList<Shingle>();
		int count = 1;
		Shingle shingle = new Shingle();
		Token nullToken = new Token("null");
		shingles.add(shingle);
		for (int i = 0; i < (tokens.size() + (2 * 5 + 1)); i++) {
			if (i > tokens.size() - 1) {
				Shingle shingleToAdd = new Shingle(shingle);
				shingleToAdd.getTokens().remove(0);
				shingleToAdd.getTokens().add(nullToken);
				shingles.add(shingleToAdd);
				shingle = shingleToAdd;
				count++;
			} else if (tokens.get(i).getLexeme().equals("<NER>")) {
				int j = i + 1;
				while (!tokens.get(j).getLexeme().equals("</NER>")) {
					Shingle shingleToAdd = new Shingle(shingle);
					shingleToAdd.getTokens().remove(0);
					shingleToAdd.getTokens().add(tokens.get(j));
					shingles.add(shingleToAdd);
					// System.out.println(tokens.get(j).toString());
					// System.err.println(j);
					count++;
					j++;
					shingle = shingleToAdd;
					if (count % 100000 == 0) {
						System.err.println(count);
					}
				}
			}

		}

		return shingles;
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
}

/**
 * Loads the training machine into the extractor
 * 
 * @param fpath
 * @return
 */
/**
 * public static LearningMachine loadLM(String fpath) { fpath += ".ser";
 * 
 * FileInputStream fInStr;
 * 
 * LearningMachine lm;
 * 
 * try { fInStr = new FileInputStream(fpath); ObjectInputStream
 * objectInputStream = new ObjectInputStream(fInStr); lm = (LearningMachine)
 * objectInputStream.readObject(); fInStr.close();
 * 
 * System.out.println("Loaded LearningMachine: " + fpath);
 * 
 * return lm; // return
 * 
 * } catch (ClassNotFoundException | IOException e) { e.printStackTrace(); }
 * 
 * return null; }
 */
/**
 * Saves the Learning Machine to a file
 * 
 * @param fpath
 * @return
 */
// public boolean saveLM(String fpath) {
// fpath = fpath + ".ser"; // add the .ser extension
//
// FileOutputStream fOutStr;
//
// try {
// fOutStr = new FileOutputStream(fpath);
// ObjectOutputStream out = new ObjectOutputStream(fOutStr);
// out.writeObject(lm);
// out.close();
//
// } catch (IOException e) {
// e.printStackTrace();
// return false;
// }
//
// System.out.println("Created Classifier: " + fpath);
// return true;
// }
//
// }