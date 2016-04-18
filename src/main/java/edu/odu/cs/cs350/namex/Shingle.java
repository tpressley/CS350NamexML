
package edu.odu.cs.cs350.namex;

import java.util.LinkedList;

public class Shingle implements Cloneable {

	private LinkedList<Token> tokens;

	public LinkedList<Token> getTokens() {
		return tokens;
	}

	public void setTokens(LinkedList<Token> tokens) {
		this.tokens = tokens;
	}

	// // TODO Update shingle size logic
	public Shingle() {
		tokens = new LinkedList<Token>();
		Token token = new Token("null");
		for (int i = 0; i < (5 * 2 + 1); i++) {
			tokens.add(token);
		}
	}

	// /*
	// * Shingle Copy Constructor
	// */
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

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	//// public Shingle(int k, int l) {
	//// for (int i = 0; i < (2 * k + 1 - l); i++) {
	//// tokens.add(null);
	//// }
	//// }

}