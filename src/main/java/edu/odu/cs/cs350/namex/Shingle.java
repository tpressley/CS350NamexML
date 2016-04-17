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

	// TODO Update shingle size logic
	public Shingle() {
		Token token = new Token("null");
		for (int i = 0; i < (5 * 2 + 1); i++) {
			tokens.add(token);
		}
	}

//	public Shingle(int k, int l) {
//		for (int i = 0; i < (2 * k + 1 - l); i++) {
//			tokens.add(null);
//		}
//	}

}