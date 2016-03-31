package edu.odu.cs.cs350.namex;

public class Token {
	private boolean capitalized;
	private boolean killWord;
	private boolean article;
	private String text;

	public Token() {

	}

	public Token(String text) {

	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCapitalized() {
		return capitalized;
	}

	public void setCapitalized(boolean capitalized) {
		this.capitalized = capitalized;
	}

	public boolean isKillWord() {
		return killWord;
	}

	public void setKillWord(boolean killWord) {
		this.killWord = killWord;
	}

	public boolean isArticle() {
		return article;
	}

	public void setArticle(boolean article) {
		this.article = article;
	}
}
