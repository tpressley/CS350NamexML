
package edu.odu.cs.cs350.namex;

import java.util.LinkedList;

public class Shingle implements Cloneable {

	private LinkedList<Token> tokens;

	/**
	 * default constructor for Shingle
	 */
	public Shingle() {
		tokens = new LinkedList<Token>();

		Token token = new Token("null");
		for (int i = 0; i < (5 * 2 + 1); i++) {
			tokens.add(token);
		}
	}

	/**
	 * Copy Constructor for Shingle
	 */
	public Shingle(Shingle shingle) {
		tokens = new LinkedList<Token>();
		for (int i = 0; i < shingle.getTokens().size(); i++) {
			Token token = new Token();
			if (shingle.getTokens().get(i) != null) {
				token = shingle.getTokens().get(i);
			}
			this.tokens.add(token);
		}
	}

	/**
	 * returns member variable 'tokens' of type LinkedList<Token>
	 * 
	 * @return
	 */
	public LinkedList<Token> getTokens() {
		return tokens;
	}

	/**
	 * setter for member variable 'tokens', a LinkedList<Token>
	 * 
	 * @param tokens
	 */
	public void setTokens(LinkedList<Token> tokens) {
		this.tokens = tokens;
	}

	/**
	 * clones a shingle
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}


}