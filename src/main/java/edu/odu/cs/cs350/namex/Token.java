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
	private String name;
	private double[] distribution;
	
	private String PER;
	
	private String taggedLexeme;

	private int position;
	
	public String getPER()
	{
		return PER;
	}
	
	public void setPER(String PER)
	{
		this.PER = PER;
	}
	
	public String getTaggedLexeme()
	{
		return taggedLexeme;
	}
	
	public void setTaggedLexeme(String taggedLexeme)
	{
		this.taggedLexeme = taggedLexeme;
	}

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
		
		PER = "";
		taggedLexeme = "";
	}

	public Token(String lexeme) {
		if (lexeme.equals("null")) {
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
			this.name = "other";
			
			PER = "";
			taggedLexeme = "";
		}
		else {
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
			this.name = "";
			this.distribution = new double[1];
			
			PER = "";
			taggedLexeme = lexeme;
		}

	}

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
		
		PER = "";
	}

	public double[] getDistribution() {
		return distribution;
	}

	public void setDistribution(double[] distribution) {
		this.distribution = distribution;
	}

	public int getPosition() {
		return position;
	}

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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// returns the ARFF @data row for the Token
	public String getARFF() {

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
		sb.append(killWord + ",");
		sb.append(name);

		return sb.toString();

	}

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

	public void printTokenData() {
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
	}
}