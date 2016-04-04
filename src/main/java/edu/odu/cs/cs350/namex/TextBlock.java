package edu.odu.cs.cs350.namex;

import java.util.ArrayList;

public class TextBlock {

	private String textBlock;
	private ArrayList<Token> tokens;

	public TextBlock() {
		this.textBlock = "";
		this.tokens = new ArrayList<Token>();
	}

	public TextBlock(String textBlock) {
		this.textBlock = textBlock;
		this.tokens = new ArrayList<Token>();
	}

	public String getTextBlock() {
		return this.textBlock;
	}

	public void setTextBlock(String textBlock) {
		this.textBlock = textBlock;
	}

	public ArrayList<Token> getTokens() {
		return this.tokens;
	}

	public void setTokens(ArrayList<Token> tokens) {
		this.tokens = tokens;
	}

	public void addToken(Token token) {
		tokens.add(token);
	}

	public void removeToken(Token token) {
		tokens.remove(token);
	}

	public void addToken(Token token, int index) {
		tokens.add(index, token);
	}

	public void removeToken(int index) {
		tokens.remove(index);
	}

	public String toString() {
		
		String output = "";

		for (Token token : this.tokens) {
			output += token.getLexeme();
		}

		output += "";

		return output;
	}
}