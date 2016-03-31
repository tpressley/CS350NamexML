package edu.odu.cs.cs350.namex;

import java.lang.String;
import java.util.ArrayList;
//import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;

public class Librarian {

	public ArrayList<String> extractPersonalNames(String textBlock) {

		return null; // return an ArrayList of names found within a block of
						// text
	}

	public void trainOn(String paragraph) {

	}

	// identify a name of a place correctly
	public ArrayList<String> nameOfPlace(String textBlock) {
		
		//tokenize input parameter textBlock
		//if a place name is found,
		//return it marked /Plc
		//e.g. "George Washington University/Plc"

		return null; // return an ArrayList of names of places found within a
						// block of text
	}

	// mark personal names with <PER></PER>
	public ArrayList<String> markPERtag(String textBlock) {
		
		//tokenize textBlock passed into this function
		//if a legitimate personal name is found 
		//return it marked with <PER></PER>
		// e.g. "<PER>John Doe</PER>"
	
		
		return null;
		

	}

	//
	public ArrayList<String> markNERtag(String textBlock) {
		return null;
		// returns block of text marked with <NER> </NER>
		// e.g. "<PER>John Doe</PER>"

	}

	public ArrayList<String> markClassificationtag(String textBlock) {
		return null;
		// returns text with <NOUN>,<VERB>,<ADJ> tags

	}

	/**
	 * Sets tokens
	 * 
	 * @param textBlock
	 */
	public void classifyToken(String textBlock) {

	}// end classifyToken

	/**
	 * 
	 */
	public void checkName(String textBlock) {

	}// end checkName
}
