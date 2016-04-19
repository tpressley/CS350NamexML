package edu.odu.cs.cs350.namex;

/**
 * The personal name class stores a personal name as an arraylist The prefix
 * (IE: Mr, Dr, Mrs, Governor, Senator, Professor)will be stored in the first
 * index of the arraylist The suffix (IE: Jr, Sr, III) will be stored as the
 * second index of the arraylist The given name will be stored in the third
 * index The surname will be stored in the final index Any middle names will be
 * stored between the 3rd and final indices.
 */
public class PersonalName {

	private String honorific;
	private String givenName;
	private String middleNames;
	private String surName;
	private String suffix;

	/**
	 * default constructor for PersonalName
	 */
	public PersonalName() {

		honorific = "";
		givenName = "";
		middleNames = "";
		surName = "";
		suffix = "";

	}

	/**
	 * constructor for PersonalName two-string constructor; only has first and
	 * last name
	 */
	public PersonalName(String firstname, String lastname) {
		honorific = "";
		givenName = firstname;
		middleNames = "";
		surName = lastname;
		suffix = "";
	}

	/**
	 * constructor for PersonalName to create one word personal names, e.g. Cher
	 * 
	 * @param name
	 */
	public PersonalName(String name) {

		honorific = "";
		givenName = name;
		middleNames = "";
		surName = "";
		suffix = "";
	}

	/**
	 * constructor for PersonalName
	 * 
	 * @param honor
	 * @param first
	 * @param middle
	 * @param last
	 * @param suff
	 */
	public PersonalName(String honor, String first, String middle, String last, String suff) {
		honorific = honor;
		givenName = first;
		middleNames = middle;
		surName = last;
		suffix = suff;

	};

	/**
	 * getter for full name returns full name in String
	 * 
	 * @return
	 */
	public String getName() {
		return this.toString();
	};

	/**
	 * getter for honorific; returns honorofic
	 * 
	 * @return
	 */
	public String getHonorific() {
		return honorific;
	}

	/**
	 * getter for given name
	 * 
	 * @return
	 */
	public String getGivenName() {
		return givenName;

	}

	/**
	 * getter for middle names
	 * 
	 * @return
	 */
	public String getMiddleNames() {

		return middleNames;

	}

	/**
	 * getter for surname
	 * 
	 * @return
	 */
	public String getSurName() {
		// returns final arraylist String
		return surName;

	}

	/**
	 * getter for suffix
	 * 
	 * @return
	 */
	public String getSuffix() {
		// returns Suffix (ie jr, sr, II) if it exists.

		return suffix;

	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String nameString = honorific + " " + givenName + " " + middleNames + " " + surName + " " + suffix;

		nameString = nameString.replaceAll("\\s+", " ");
		return nameString.trim();

	}

}
