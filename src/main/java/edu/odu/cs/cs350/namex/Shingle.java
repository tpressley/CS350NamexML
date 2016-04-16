package edu.odu.cs.cs350.namex;

import java.util.ArrayList;

public class Shingle {

	private ArrayList<Token> tokens;

	public ArrayList<Token> getTokens() {
		return tokens;
	}

	public void setTokens(ArrayList<Token> tokens) {
		this.tokens = tokens;
	}

	//TODO Update shingle size logic
	public Shingle() {
		for (int i = 0; i < (11-l); i++) {
			tokens.add(null);
		}
	}

	public Shingle(int k, int l)
	{
		for(int i = 0; i < (2*k+1-l); i++)
		{
			tokens.add(null);
		}
	}

}