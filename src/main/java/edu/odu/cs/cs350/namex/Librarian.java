package edu.odu.cs.cs350.namex;

import java.lang.String;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import edu.odu.cs.cs350.namex.Trainer;
import edu.odu.cs.extract.wordlists.WordLists;

public class Librarian {
	LearningMachine lm;
	Trainer trainer;

	/**
	 * default constructor
	 */
	public Librarian() {
		lm = new LearningMachine();
		trainer = new Trainer();
	}
	
	/**
	 * copy constructor
	 */
	public Librarian(Librarian toCopy){
		this.lm = toCopy.lm;
		this.trainer = toCopy.trainer;
	}
	
	
	
	
	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// Test Output
		/*
		 * for (String s: args) { System.out.println(s); }
		 */

		// Generate ARFF Training Data
		if (args.length == 3) {
			if (args[0].equalsIgnoreCase("train")) {

				System.out.println("*******************************");
				System.out.println(" Generating ARFF Training Data");
				System.out.println("*******************************\n");

				String inputFileName = args[1];
				String outputFileName = args[2];
				ArrayList<String> textFile = new ArrayList<String>();
				ArrayList<Token> tokens = new ArrayList<Token>();
				LinkedList<Shingle> shingles = new LinkedList<Shingle>();
				Trainer trainer = new Trainer();
				System.out.println();

				// read in file and split by punctuation, excluding whitespace.
				Scanner sc = new Scanner(new File(args[1]));
				while (sc.hasNext()) {
					String s = sc.next();
					String[] wordsplit = s.split("(?=[ ,.!()<:;}-])|(?<=[ (>{-])");
					for (int i = 0; i < wordsplit.length; i++) {
						if (wordsplit[i].trim().length() > 0) {
							textFile.add(wordsplit[i]);
							// tokens.add((trainer.getFeatures((wordsplit[i]));
						}
					}
				}
				sc.close();

				// separate string into tokens
				tokens = trainer.tokenize(textFile);
				// add features to tokens
				trainer.getFeatures(tokens);
				shingles = trainer.getShingles(tokens);
				trainer.createArff(shingles);

			}
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

	// User Story #861
	// Status - Completed
	// CLI processes each <NER> block separately via Extractor
	// interface. (A, maybe L)

}