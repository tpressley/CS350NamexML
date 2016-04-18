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
	private int prefix; // last name prefix; 'von' 'de'
	private int suffix;
	private int killWord;
	private String name;
	private double[] distribution;

	private int position;

	/**
	 * Default constructor
	 */
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
		this.name = "";
		this.distribution = new double[1];
		this.position = 0;
	}

	/**
	 * Constructor with String parameter
	 * 
	 * @param lexeme
	 */
	public Token(String lex) {
		
		if (lex.equalsIgnoreCase("null")) {
			this.lexeme = null;
			this.lexical = null;
			this.partOfSpeech = null;
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
			this.name = "null";
			this.position = 0;
		} 
		else if (lex.trim().equals("")) {
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
			this.name = "null";
			this.position = 0;
		} 
		
			this.lexeme = lex;
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
			this.name = "";
			this.distribution = new double[1];
			this.position = 0;
	}

	/**
	 * Constructor
	 * 
	 * @param lexeme
	 * @param position
	 */
	public Token(String lexeme, int position) {
		this.lexeme = lexeme;
		this.lexical = "other";
		this.partOfSpeech = "other";
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
		this.position = position;
		this.name = "";
		this.distribution = new double[1];
	}

	/**
	 * returns array for distribution
	 * 
	 * @return
	 */
	public double[] getDistribution() {
		return distribution;
	}

	/**
	 * setter for array for distribution
	 * 
	 * @param distribution
	 */
	public void setDistribution(double[] distribution) {
		this.distribution = distribution;
	}

	/**
	 * returns position
	 * 
	 * @return
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * setter for position
	 * 
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Returns the lexeme of this token
	 * 
	 * @return
	 */
	public String getLexeme() {
		return this.lexeme;
	}

	/**
	 * Sets the lexeme of this token
	 * 
	 * @return
	 */
	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}

	/**
	 * Returns the Lexical attribute of this token
	 * 
	 * @return
	 */
	public String getLexical() {
		return this.lexical;
	}

	/**
	 * Sets the Lexical attribute of this token
	 * 
	 * @return
	 */
	public void setLexical(String lexical) {
		this.lexical = lexical;
	}

	/**
	 * Returns the partOfSpeech attribute of this token
	 * 
	 * @return
	 */
	public String getPartOfSpeech() {
		return this.partOfSpeech;
	}

	/**
	 * Sets the partOfSpeech attribute of this token
	 * 
	 * @return
	 */
	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}

	/**
	 * Indicates whether or not the token is a dictionary word
	 * 
	 * @return
	 */
	public int isDictionaryWord() {
		return this.dictionaryWord;
	}

	/**
	 * Sets whether or not the token is a dictionary word
	 * 
	 * @return
	 */
	public void setDictionaryWord(int i) {
		if (i == 1 || i == 0)
			this.dictionaryWord = i;
	}

	// cityState
	/**
	 * returns '1' if citystate, '0' if not
	 * 
	 * @return
	 */
	public int isCityState() {
		return this.cityState;
	}

	/**
	 * setter for citystate
	 * 
	 * @param cityState
	 */
	public void setCityState(int i) {
		if (i == 1 || i == 0)
			this.cityState = i;
	}

	// countryTerritory
	/**
	 * returns '1' if countryterritory, '0' if not
	 * 
	 * @return
	 */
	public int isCountryTerritory() {
		return this.countryTerritory;
	}

	/**
	 * setter for countryterritory
	 * 
	 * @param countryTerritory
	 */
	public void setCountryTerritory(int i) {
		if (i == 1 || i == 0)
			this.countryTerritory = i;
	}

	// place
	/**
	 * returns '1' if name of place, '0' if not
	 * 
	 * @return
	 */
	public int isPlace() {
		return this.place;
	}

	/**
	 * setter for place
	 * 
	 * @param place
	 */
	public void setPlace(int i) {
		if (i == 1 || i == 0)
			this.place = i;

	}

	// DTICFirst
	/**
	 * returns '1' if a DTIC first name
	 * 
	 * @return
	 */
	public int isDTICFirst() {
		return this.DTICFirst;
	}

	/**
	 * setter for DTICFirst
	 * 
	 * @param DTICFirst
	 */
	public void setDTICFirst(int i) {
		if (i == 1 || i == 0)
			this.DTICFirst = i;

	}

	// DTICLast
	/**
	 * returns '1' if a DTIC last name
	 * 
	 * @return
	 */
	public int isDTICLast() {
		return this.DTICLast;
	}

	/**
	 * setter for DTICLast
	 * 
	 * @param DTICLast
	 */
	public void setDTICLast(int i) {
		if (i == 1 || i == 0)
			this.DTICLast = i;

	}

	// commonFirst
	/**
	 * returns '1' if a common first name
	 * 
	 * @return
	 */
	public int isCommonFirst() {
		return this.commonFirst;
	}

	/**
	 * setter for commonFirst
	 * 
	 * @param commonFirst
	 */
	public void setCommonFirst(int i) {
		if (i == 1 || i == 0)
			this.commonFirst = i;
	}

	// commonLast
	/**
	 * returns '1' if a common last name
	 * 
	 * @return
	 */
	public int isCommonLast() {
		return this.commonLast;
	}

	/**
	 * setter for commonLast
	 * 
	 * @param commonLast
	 */
	public void setCommonLast(int i) {
		if (i == 1 || i == 0)
			this.commonLast = i;
	}

	// honorific
	/**
	 * returns '1' if honorific
	 * 
	 * @return
	 */
	public int isHonorific() {
		return this.honorific;
	}

	/**
	 * setter for honorific
	 * 
	 * @param honorific
	 */
	public void setHonorific(int i) {
		if (i == 1 || i == 0)
			this.honorific = i;
	}

	// prefix
	/**
	 * returns '1' if a prefix
	 * 
	 * @return
	 */
	public int isPrefix() {
		return this.prefix;
	}

	/**
	 * setter for prefix
	 * 
	 * @param prefix
	 */
	public void setPrefix(int i) {
		if (i == 1 || i == 0)
			this.prefix = i;
	}

	// suffix
	/**
	 * returns '1' if a suffix
	 * 
	 * @return
	 */
	public int isSuffix() {
		return this.suffix;
	}

	/**
	 * setter for suffix
	 * 
	 * @param suffix
	 */
	public void setSuffix(int i) {
		if (i == 1 || i == 0)
			this.suffix = i;
	}

	// killWord
	/**
	 * returns '1' if a killword e.g. "bridge""university"
	 * 
	 * @return
	 */
	public int isKillWord() {
		return this.killWord;
	}

	/**
	 * setter for killword
	 * 
	 * @param killWord
	 */
	public void setKillWord(int i) {
		if (i == 1 || i == 0)
			this.killWord = i;
	}

	// name
	/**
	 * returns name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * returns the ARFF @data row for the Token
	 * 
	 * public String getARFFstring() {
	 * 
	 * StringBuilder sb = new StringBuilder();
	 * 
	 * sb.append(lexical + ","); sb.append(partOfSpeech + ",");
	 * sb.append(dictionaryWord + ","); sb.append(cityState + ",");
	 * sb.append(countryTerritory + ","); sb.append(place + ",");
	 * sb.append(DTICFirst + ","); sb.append(DTICLast + ",");
	 * sb.append(commonFirst + ","); sb.append(commonLast + ",");
	 * sb.append(honorific + ","); sb.append(prefix + ","); sb.append(suffix +
	 * ","); sb.append(killWord + ","); sb.append(name);
	 * 
	 * return sb.toString(); }
	 */
	// User Story #850
	// Status - Completed
	// Tokens converted into a set of features (T)
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(lexical + ",");
		sb.append(partOfSpeech + ",");
		sb.append(dictionaryWord + ",");
		sb.append(cityState + ",");
		sb.append(countryTerritory + ",");
		sb.append(place + ",");
		sb.append(DTICFirst + ",");
		sb.append(DTICLast + ",");
		sb.append(commonFirst + ",");
		sb.append(commonLast + ",");
		sb.append(honorific + ",");
		sb.append(prefix + ",");
		sb.append(suffix + ",");
		sb.append(killWord);

		return sb.toString();

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
		output += name + ",";
		output += position + ",\",";

		return output;
	}

	public boolean printTokenData() throws Exception {

		try {
			System.out.println("            lexeme:   " + lexeme);
			System.out.println("    classification:   " + name);

			System.out.println("         beginning:   " + Math.round(distribution[0] * 100.00) + "%");
			System.out.println("        continuing:   " + Math.round(distribution[1] * 100.00) + "%");
			System.out.println("             other:   " + Math.round(distribution[2] * 100.00) + "%");

			System.out.println("           lexical:   " + lexical);
			System.out.println("    part of speech:   " + partOfSpeech);
			System.out.println("   dictionary word:   " + dictionaryWord);
			System.out.println("        city/state:   " + cityState);
			System.out.println(" country/territory:   " + countryTerritory);
			System.out.println("             place:   " + place);
			System.out.println("        DTIC first:   " + DTICFirst);
			System.out.println("         DTIC last:   " + DTICLast);
			System.out.println("      common first:   " + commonFirst);
			System.out.println("       common last:   " + commonLast);
			System.out.println("         honorific:   " + honorific);
			System.out.println("            prefix:   " + prefix);
			System.out.println("            suffix:   " + suffix);
			System.out.println("         kill word:   " + killWord + "\n");
		} catch (Exception e789) {
			return false;
		}
		return true;
	}
}