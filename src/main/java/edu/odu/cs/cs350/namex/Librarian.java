package edu.odu.cs.cs350.namex;

import java.lang.String;
import java.util.ArrayList;
//import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;

import edu.odu.cs.cs350.namex.Trainer;

public class Librarian {

	public ArrayList<String> extractPersonalNames(String textBlock) {

		return null; // return an ArrayList of names found within a block of
						// text
	}

	public void trainOn(String paragraph) {

	}

	// identify a name of a place correctly
	public ArrayList<String> nameOfPlace(String textBlock) {

		// tokenize input parameter textBlock
		// if a place name is found,
		// get it and mark /Plc
		// to return an ArrayList<String>
		// e.g. textBlock="This article came from George Washington University"
		// returns "George Washington University/Plc"

		String mark = "/Plc";
		String place = ""; // this is an extracted name of place from textBlock
		ArrayList<String> pl = null; // a name of a place identifed from
										// textBlock
		pl.add(place);

		ArrayList<String> ret = null; // return value
		ret.add(place); // add name of place to return ArrayList<String>
		ret.add(mark); // mark with "/Plc"

		return ret; // return an ArrayList of names of places found within a
					// block of text
	}

	// mark personal names with <PER></PER>
	public ArrayList<String> markPERtag(String textBlock) {

		// tokenize textBlock passed into this function
		// if a legitimate personal name is found
		// return it marked with <PER></PER>
		// e.g. "<PER>John Doe</PER>"

		String start = "<PER>";
		String end = "</PER>";

		ArrayList<String> ret = null; // return value

		String pn = "no name yet"; // personal name extracted from textBlock

		// get personal name from textBlock and store it in String pn

		ret.add(start); // <PER>
		ret.add(pn); // personal name
		ret.add(end); // </PER>

		return ret;
	}

	//
	public ArrayList<String> markNERtag(String textBlock) {

		ArrayList<String> ret = null; // return value
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

		ret.add(start);
		// then add textBlock broken down into tokens?
		ret.add(end);

		return ret;
		// returns block of text marked with <NER> </NER>

	}

	public ArrayList<String> markClassificationTag(String textBlock) {

		ArrayList<String> ret = null; // return value

		// break down textBlock into smaller parts
		// if given token is a noun, return it marked <NOUN>
		// if verb, then return it marked <VERB>
		// if adjective, then return it marked <ADJ>

		return ret;
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

	public static void main(String[] args) {
		try {
			String textToExtract = args[0];
		} catch (Exception e) {
			System.out
					.println(e.toString() + "\nERROR: No argumenets detected, Usage: java -jar jarName \"InputText\"");
		}
		Trainer trainer = new Trainer();
		try {
			trainer.SaveLM("");
		} catch (Exception e) {
			System.out.println("ERROR: File execption while saving model, Librarian.java:142");
		}
	}
}
