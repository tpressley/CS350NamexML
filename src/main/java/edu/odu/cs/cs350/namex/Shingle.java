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

	public Shingle() {
		for (int i = 0; i < 11; i++) {
			tokens.add(null);
		}
	}

	public Shingle(int k)
	{
		for(int i = 0; i < ((2*k); i++)
		{
			tokens.add(null);
		}
	}

}