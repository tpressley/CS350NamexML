package edu.odu.cs.cs350.namex;

import java.lang.String;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import edu.odu.cs.cs350.namex.Trainer;
import edu.odu.cs.extract.wordlists.WordLists;

public class Librarian {
  LearningMachine lm;
  Trainer trainer;
  
  public Librarian() {
    lm = new LearningMachine();
    trainer = new Trainer();

  }

	public static void main(String[] args) throws FileNotFoundException {
		// Test Output
		/*
		 * for (String s: args) { System.out.println(s); }
		 */

		if (args.length == 0) {
			System.out.println("Hello World!");
		}

		// if CLI contains one argument that is a textBlock
		else if (args.length == 1) {
			System.out.println(args[0]);
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
//				trainer.prepareData(inputFileName, outputFileName, false);
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
	      ArrayList<String> textFile = new ArrayList<String>();
	      Trainer trainer = new Trainer();
	      System.out.println();
	        Scanner sc = new Scanner(new File(args[1]));
	        while(sc.hasNext()){
	            String s = sc.next();
	            String[] wordsplit =s.split("[\\p{Punct}\\s]+");
	            for (int i = 0 ; i<wordsplit.length; i++)
	            textFile.add(wordsplit[i]);
	            
	            
	        }
	        for (int i=0; i< textFile.size();i++)
	        {
	          System.out.println(textFile.get(i));
	        }
	      
	      
	      
	       inputFileName = args[1];
	      
		
			}
		}
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
				textBlocks.addAll(detectNERTag(s.nextLine()));
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
				textBlocks.addAll(detectNERTag(s.nextLine()));
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return textBlocks;
	}




  // User Story #861
  // Status - Completed
  // CLI processes each <NER> block separately via Extractor
  // interface. (A, maybe L)
  public static ArrayList<TextBlock> detectNERTag(String input) {
    
    
    
  	String[] tbs = input.split("<NER>|</NER>");
  
  	ArrayList<TextBlock> textBlocks = new ArrayList<TextBlock>();
  
  	for (String tb : tbs) {
  		if (!tb.equals("")) {
  			textBlocks.add(new TextBlock(tb));
  		}
  	}
  
  	return textBlocks;
  }



}