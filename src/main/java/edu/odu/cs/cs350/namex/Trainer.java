package edu.odu.cs.cs350.namex;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;

public class Trainer implements Serializable {
	private static final long serialVersionUID = 1969136929013924126L;

	private LearningMachine learningMachine;

	public Trainer() {
		learningMachine = new LearningMachine();
	}

	public Trainer(int k) {
		learningMachine = new LearningMachine(k);
	}

	public Trainer(LearningMachine learningMachine) {
		this.learningMachine = learningMachine;
	}

	public LearningMachine getLearningMachine() {
		return learningMachine;
	}

	public void setLearningMachine(LearningMachine learningMachine) {
		this.learningMachine = learningMachine;
	}

	// User Story #1095
	// Status - Implementation
	// As a Trainer, I want Shingling applied either to lists of
	// tokens or to lists of feature sets.
	public ArrayList<Shingle> getShingles(ArrayList<Token> tokens, String nothing) {
		int k = 5;// default is k = 5

		int sequenceLength = ((2 * k) + 1);
		ArrayList<Shingle> shingles = new ArrayList<Shingle>();

		// add null tokens before and after the text block
		for (int i = 0; i < k; i++) {
			tokens.add(0, new Token("null"));
			tokens.add(new Token("null"));
		}

		// System.out.println("\nSize: " + shingleTokens.size());
		// System.out.println("Sequence Length: " + sequenceLength);

		for (int i = 0; i < (tokens.size() - (sequenceLength - 1)); i++) {
			// ArrayList<Token> shingle = new ArrayList<Token>();

			StringBuilder sb = new StringBuilder();
			StringBuilder sbLexemes = new StringBuilder();
			StringBuilder sbClassifications = new StringBuilder();

			// int nameCount = 0; // if there are more than two classified names
			// within the shingle, classify it as 'yes'
			for (int j = 0; j < sequenceLength; j++) {
				if (tokens.get(j + i).getName().equals("beginning")
						|| tokens.get(j + i).getName().equals("continuing")) {
					// nameCount++;
				}

				// System.out.print(shingleTokens.get(j + i).getARFF() + ",");
				// shingle.add(shingleTokens.get(j + i));

				if (j == (sequenceLength - 1)) {
					sb.append(tokens.get(j + i).getARFF());
				} else {
					sb.append(tokens.get(j + i).getARFF() + ",");
				}

				sbLexemes.append(tokens.get(j + i).getLexeme() + " ");
				sbClassifications.append(tokens.get(j + i).getName() + " ");
			}

			shingles.add(new Shingle(sbLexemes.toString(), sbClassifications.toString(), sb.toString()));
		}

		return shingles;
	}

	// User Story #1095
	// Status - Implementation
	// As a Trainer, I want Shingling applied either to lists of
	// tokens or to lists of feature sets.
	public HashSet<String> getShingles(int k, ArrayList<Token> tokens) {
		int sequenceLength = ((2 * k) + 1);
		HashSet<String> shingles = new HashSet<String>();

		// add null tokens before and after the text block
		for (int i = 0; i < k; i++) {
			tokens.add(0, new Token("null"));
			tokens.add(new Token("null"));
		}

		// System.out.println("\nSize: " + shingleTokens.size());
		// System.out.println("Sequence Length: " + sequenceLength);

		for (int i = 0; i < (tokens.size() - (sequenceLength - 1)); i++) {
			// ArrayList<Token> shingle = new ArrayList<Token>();

			StringBuilder sb = new StringBuilder();
			StringBuilder sbLexeme = new StringBuilder();

			// int nameCount = 0; // if there are more than two classified names
			// within the shingle, classify it as 'yes'
			int beginningCount = 0;
			int continuingCount = 0;
			int killWordCount = 0; // # of killWords following a beginning or
									// continuing token
			for (int j = 0; j < sequenceLength; j++) {
				/*
				 * if (tokens.get(j + i).getName().equals("beginning") ||
				 * tokens.get(j + i).getName().equals("continuing")) {
				 * nameCount++; }
				 */

				if (tokens.get(j + i).getName().equals("beginning")) {
					beginningCount++;
				} else if (tokens.get(j + i).getName().equals("continuing")) {
					continuingCount++;
				}

				// if there is a killWord following a beginning or continuing
				// token
				if (tokens.get(j + i).isKillWord() == 1) {
					if (tokens.get(j + i - 1).getName().equals("beginning")
							|| tokens.get(j + i - 1).getName().equals("continuing")) {
						killWordCount++;
					}
				}

				// System.out.print(shingleTokens.get(j + i).getARFF() + ",");
				// shingle.add(shingleTokens.get(j + i));
				sb.append(tokens.get(j + i).getARFF() + ",");
				sbLexeme.append(tokens.get(j + i).getLexeme() + " ");
			}

			// if (nameCount > 1)
			if (beginningCount > 0 && continuingCount > 0) {
				// Logic for manual Shingle training classification
				/*
				 * Scanner reader = new Scanner(System.in); // Reading from
				 * System.in
				 * 
				 * boolean correctInput = false;
				 * 
				 * while (correctInput == false) { System.out.println(
				 * "Does the line below contain a personal name? (1 = Yes | 2 = No)"
				 * ); System.out.println(sbLexeme); String input =
				 * reader.nextLine(); // Scans the next token of the input as an
				 * int.
				 * 
				 * if (input.equals("1") || input.isEmpty()) { sb.append("yes");
				 * correctInput = true; } else if (input.equals("2")) {
				 * sb.append("no"); correctInput = true; } else {
				 * System.out.println("Incorrect input!"); }
				 * 
				 * }
				 */

				if (killWordCount > 0) {
					sb.append("no");
				} else {
					sb.append("yes");
				}
				// System.out.print("yes");
			} else {
				sb.append("no");
				// System.out.print("no");
			}

			shingles.add(sb.toString());
		}

		/*
		 * // Test Output for (String s : shingles) { System.out.println(s); }
		 */

		return shingles;
	}

	// User Story #851
	// Status - Completed
	// As a Trainer, I want the program to properly prepare data
	// to train the learning machine.
	public void prepareData(String inputFileName, String outputFileName, boolean showSummary) {
		Librarian librarian = new Librarian();

		ArrayList<TextBlock> textBlocks = Librarian.importFile(inputFileName);
		ArrayList<String> arffData = new ArrayList<String>();

		System.out.println("\nClassifying Tokens from " + textBlocks.size() + " TextBlocks...");

		for (TextBlock textBlock : textBlocks) {
			ArrayList<Token> tks = tokenize(textBlock.getTextBlock());
			HashSet<Token> classifiedTokens = librarian.getFeatures(tks);

			for (Token t : classifiedTokens) {
				// System.out.println(t.getARFF());

				if (!t.getLexical().equals("whiteSpace")) {
					arffData.add(t.getARFF());
				}
			}
		}

		String[] ARFFArray = new String[arffData.size()];
		ARFFArray = arffData.toArray(ARFFArray);

		learningMachine = new LearningMachine();
		learningMachine.importARFF(ARFFArray);

		try {
			learningMachine.train();
			// printARFF();
			if (showSummary == true) {
				learningMachine.printEvaluationSummary();
			}
			learningMachine.exportARFF(outputFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// User Story #851
	// Status - Completed
	// As a Trainer, I want the program to properly prepare data
	// to train the learning machine.
	public void prepareShinglingData(String arffFilePath, int k, String inputFileName, String outputFileName) {
		Librarian librarian = new Librarian();

		// Trainer for Token classification
		LearningMachine lm = new LearningMachine();

		// Trainer for Shingle classification
		LearningMachine shingleTrainer = new LearningMachine(k);

		try {
			lm.importARFF(arffFilePath);
			lm.train();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		HashSet<TextBlock> textBlocks = Librarian.importFileHash(inputFileName);

		HashSet<String> shingles = new HashSet<String>();

		System.out.println("\nShingling " + textBlocks.size() + " TextBlocks...");

		for (TextBlock textBlock : textBlocks) {
			ArrayList<Token> tks = tokenize(textBlock.getTextBlock());
			ArrayList<Token> classifiedTokens = new ArrayList<Token>();

			for (Token t : tks) {
				t = librarian.getFeatures(t);

				if (!t.getLexical().equals("whiteSpace")) {
					try {
						t.setName(lm.classify(t.toString()));
						classifiedTokens.add(t);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// System.out.println(t.getARFF());
			}
			shingles.addAll(getShingles(k, classifiedTokens));
		}

		shingleTrainer.importARFF(shingles);

		try {
			shingleTrainer.train();
			// printARFF();
			shingleTrainer.printEvaluationSummary();
			shingleTrainer.exportARFF(outputFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// User Story #860
	// Status - Completed
	// Text blocks divided into tokens, with punctuation separate from
	// alphabetics (T)
	public ArrayList<Token> tokenize(String textBlock) {

		// split the string
		// String[] tks = textBlock.split("(?=[\"
		// ,.!?\n()-:;@#$%^&*{}<>])|(?<=[\" ,.!?\n()-:;@#$%^&*{}<>])");

		// new tokenize regex
		String[] tks = textBlock.split("(?<=<NER>)|(?=</NER>)|(?<=<PER>)|(?=</PER>)|(?=[ ,.!()<:;}-])|(?<=[ (>{-])");

		ArrayList<Token> tokens = new ArrayList<Token>();

		for (int i = 0; i < tks.length; i++) {
			tokens.add(new Token(tks[i], i));
		}

		return tokens;
	}

	// User Story #860
	// Status - Completed
	// Text blocks divided into tokens, with punctuation separate from
	// alphabetics (T)
	public ArrayList<Token> tokenize(String textBlock, boolean verbose) {
		// split the string
		// String[] tks = textBlock.split("(?=[\"
		// ,.!?\n()-:;@#$%^&*{}<>])|(?<=[\" ,.!?\n()-:;@#$%^&*{}<>])");

		// new tokenize regex
		String[] tks = textBlock.split("(?<=<NER>)|(?=</NER>)|(?<=<PER>)|(?=</PER>)|(?=[ ,.!()<:;}-])|(?<=[ (>{-])");

		ArrayList<Token> tokens = new ArrayList<Token>();

		for (int i = 0; i < tks.length; i++) {
			tokens.add(new Token(tks[i], i));
			System.out.println(tokens.get(i).toString());
		}

		return tokens;
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

	// User Story #853
	// Status - Completed
	// As Trainer, I want to use existing data to train the learning machine
	public void trainLM(String filePath) {
		try {
			learningMachine.importARFF(filePath);
			learningMachine.train();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// User Story #848
	// Status - Completed
	// Load trained machine into the extractor (L,T)
	public static LearningMachine loadLearningMachine(String filePath) {
		filePath = filePath + ".ser";

		FileInputStream fileInputStream;

		LearningMachine lm;

		try {
			fileInputStream = new FileInputStream(filePath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			lm = (LearningMachine) objectInputStream.readObject();
			fileInputStream.close();

			System.out.println("Loaded LearningMachine: " + filePath);
			return lm;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	// User Story #849
	// Status - Completed
	// Save trained learning machine in a file. (T)
	public void saveLearningMachine(String filePath) {
		filePath = filePath + ".ser"; // add the .ser extension

		FileOutputStream outputFile;

		try {
			outputFile = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(outputFile);
			out.writeObject(learningMachine);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Created Classifier: " + filePath);
	}

}