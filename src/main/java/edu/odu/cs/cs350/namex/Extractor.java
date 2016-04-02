package edu.odu.cs.cs350.namex;

import java.util.ArrayList;

public class Extractor 
{
	
	public Extractor()
	{
		
	}
	
	// returns the textBlock with <PER></PER> tags surrounding personal names found
	public String extractPersonalNames(String textBlock) {

		return null; 

	}
	
	// tokenize the input string and keeps the delimiter
	public String[] tokenize(String textBlock)
	{		
		
		return textBlock.split("(?=[\" ,.!?\n()-:;])|(?<=[\" ,.!?\n()-:;])");     
		
	}
	
	public boolean isLexicalFeature(String token)
	{
		
		ArrayList<String> puncts = new ArrayList<String>();
		
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
		
		if (puncts.contains(token))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
}
