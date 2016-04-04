package edu.odu.cs.cs350.namex;

public class Token {

	private String lexeme;
	private String lexical;
	private String partOfSpeech;
	private int dictionaryWord;
	private int cityState;
	private int countryTerritory;
	private int place;
	private int DTICFirst;
	private int DTICLast;
	private int commonFirst;
	private int commonLast;
	private int honorific;
	private int prefix;
	private int suffix;
	private int killWord;
	private int name;

	public Token() {
		this.lexeme = "";
		this.lexical = "";
		this.partOfSpeech = "";
		this.dictionaryWord = 0;
		this.cityState = 0;
		this.countryTerritory = 0;
		this.place = 0;
		this.DTICFirst = 0;
		this.DTICLast = 0;
		this.commonFirst = 0;
		this.commonLast = 0;
		this.honorific = 0;
		this.prefix = 0;
		this.suffix = 0;
		this.killWord = 0;
	}

	public Token(String lexeme) {
		this.lexeme = lexeme;
		this.lexical = "";
		this.partOfSpeech = "";
		this.dictionaryWord = 0;
		this.cityState = 0;
		this.countryTerritory = 0;
		this.place = 0;
		this.DTICFirst = 0;
		this.DTICLast = 0;
		this.commonFirst = 0;
		this.commonLast = 0;
		this.honorific = 0;
		this.prefix = 0;
		this.suffix = 0;
		this.killWord = 0;
	}

	// lexeme
	public String getLexeme() {
		return this.lexeme;
	}

	public String toString() {
		return this.lexeme;

	}

	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}

	// lexical
	public String getLexical() {
		return this.lexical;
	}

	public void setLexical(String lexical) {
		this.lexical = lexical;
	}

	// partOfSpeech
	public String getPartOfSpeech() {
		return this.partOfSpeech;
	}

	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}

	// dictionaryWord
	public int isDictionaryWord() {
		return this.dictionaryWord;
	}

	public void setDictionaryWord(int dictionaryWord) {
		this.dictionaryWord = dictionaryWord;
	}

	// cityState
	public int isCityState() {
		return this.cityState;
	}

	public void setCityState(int cityState) {
		this.cityState = cityState;
	}

	// countryTerritory
	public int isCountryTerritory() {
		return this.countryTerritory;
	}

	public void setCountryTerritory(int countryTerritory) {
		this.countryTerritory = countryTerritory;
	}

	// place
	public int isPlace() {
		return this.place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	// DTICFirst
	public int isDTICFirst() {
		return this.DTICFirst;
	}

	public void setDTICFirst(int DTICFirst) {
		this.DTICFirst = DTICFirst;
	}

	// DTICLast
	public int isDTICLast() {
		return this.DTICLast;
	}

	public void setDTICLast(int DTICLast) {
		this.DTICLast = DTICLast;
	}

	// commonFirst
	public int isCommonFirst() {
		return this.commonFirst;
	}

	public void setCommonFirst(int commonFirst) {
		this.commonFirst = commonFirst;
	}

	// commonLast
	public int isCommonLast() {
		return this.commonLast;
	}

	public void setCommonLast(int commonLast) {
		this.commonLast = commonLast;
	}

	// honorific
	public int isHonorific() {
		return this.honorific;
	}

	public void setHonorific(int honorific) {
		this.honorific = honorific;
	}

	// prefix
	public int isPrefix() {
		return this.prefix;
	}

	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}

	// suffix
	public int isSuffix() {
		return this.suffix;
	}

	public void setSuffix(int suffix) {
		this.suffix = suffix;
	}

	// killWord
	public int isKillWord() {
		return this.killWord;
	}

	public void setKillWord(int killWord) {
		this.killWord = killWord;
	}

	// name
	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	// toString
	public String getARFF() {

		String output = lexeme + ",";
		output += lexical + ",";
		output += partOfSpeech + ",";
		output += dictionaryWord + ",";
		output += cityState + ",";
		output += countryTerritory + ",";
		output += place + ",";
		output += DTICFirst + ",";
		output += DTICLast + ",";
		output += commonFirst + ",";
		output += commonLast + ",";
		output += honorific + ",";
		output += prefix + ",";
		output += suffix + ",";
		output += killWord + ",";
		output += name;

		return output;

	}

	// toString surrounded by quotes
	// used for training
	public String toStringQuotes() {

		String output = "\"" + lexeme + ",";
		output += lexical + ",";
		output += partOfSpeech + ",";
		output += dictionaryWord + ",";
		output += cityState + ",";
		output += countryTerritory + ",";
		output += place + ",";
		output += DTICFirst + ",";
		output += DTICLast + ",";
		output += commonFirst + ",";
		output += commonLast + ",";
		output += honorific + ",";
		output += prefix + ",";
		output += suffix + ",";
		output += killWord + ",";
		output += name + ",\",";

		return output;

	}
}