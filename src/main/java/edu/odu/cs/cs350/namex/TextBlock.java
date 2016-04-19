package edu.odu.cs.cs350.namex;

import java.util.ArrayList;


/**
 * @author addyb_000
 *
 */
/**
 * @author addyb_000
 *
 */
public class TextBlock {

	private String textBlock;
	private ArrayList<Token> tokens;


	/**
	 * Default Contstuctor
	 */
	public TextBlock() {
		this.textBlock = "";
		this.tokens = new ArrayList<Token>();
	}

	/**
	 * initializes a textblock from a string
	 * @param String textBlock
	 */
	public TextBlock(String textBlock) {
		this.textBlock = textBlock;
		this.tokens = new ArrayList<Token>();
	}

	/**
	 * returns textBlock
	 * @return
	 */
	public String getTextBlock() {
		return this.textBlock;
	}

	/**
	 * sets the textBlock
	 * @param textBlock
	 */
	public void setTextBlock(String textBlock) {
		this.textBlock = textBlock;
	}

	/**
	 * Returns arraylist of tokens from textblock
	 * @return ArrayList<Token>
	 */
	public ArrayList<Token> getTokens() {
		return this.tokens;
	}

	/**
	 * populates arraylist with tokens. 
	 * @param tokens
	 */
	public void setTokens(ArrayList<Token> tokens) {
		this.tokens = tokens;
	}

	/**
	 * adds token to token array
	 * @param token
	 */
	public void addToken(Token token) {
		tokens.add(token);
	}

	/**
	 * removes a token from token array
	 * @param token
	 */
	public void removeToken(Token token) {
		tokens.remove(token);
	}

	/**
	 * adds token to array at specified index
	 * @param token
	 * @param index
	 */
	public void addToken(Token token, int index) {
		tokens.add(index, token);
	}

	/**
	 * removes token at specified index
	 * @param index
	 */
	public void removeToken(int index) {
		tokens.remove(index);
	}

	/**
	 *  tostring outputs tokenlexemes.
	 */
 public String toString() {

		String output = "";

		for (Token token : this.tokens) {
			output += token.getLexeme();
		}

		output += "";

		return output;
	}
}