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
	      Trainer trainer = new Trainer();
	      System.out.println();
	        Scanner sc = new Scanner(new File(args[1]));
	        while(sc.hasNext()){
	            String s = sc.next();
	            String[] wordsplit =s.split("[\\p{Punct}\\s]+");
	            for (int i = 0 ; i<wordsplit.length; i++)
	            {
	              if (wordsplit[i].trim().length() > 0)
	              {
	            textFile.add(wordsplit[i]);
	           // tokens.add((trainer.getFeatures((wordsplit[i]));
	            }
	            }  
	        }
	        for (int i=0; i< textFile.size();i++)
	        {
	          System.out.println(textFile.get(i));
	        }
	      
	        
	        
	        
	      
	       inputFileName = args[1];
	      
		
			}
		}
	}


	
	

	
	





  // User Story #861
  // Status - Completed
  // CLI processes each <NER> block separately via Extractor
  // interface. (A, maybe L)




}