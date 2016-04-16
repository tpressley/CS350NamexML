package edu.odu.cs.cs350.namex;

/**
 * The personal name class stores a personal name as an arraylist
 * The prefix (IE: Mr, Dr, Mrs, Governor, Senator, Professor)will be stored in the first index of the
 * arraylist
 * The suffix (IE: Jr, Sr, III) will be stored as the second index of the
 * arraylist
 * The given name will be stored in the third index
 * The surname will be stored in the final index
 * Any middle names will be stored between the 3rd and final indices.
 */
public class PersonalName {

	private String honorific;
	private String givenName;
	private String middleNames;
	private String surName;
	private String suffix;

	PersonalName() {

		honorific = "";
		givenName = "";
		middleNames = "";
		surName = "";
		suffix = "";

	}

	/**
	 * Assume 2 string constructor only has first and last name
	 */
	PersonalName(String givenNameInput, String surNameInput) {
		honorific = "";
		givenName = givenNameInput;
		middleNames = "";
		surName = surNameInput;
		suffix = "";

	};

	/**
	 * Constructor to create one word personal names, e.g. Cher
	 * @param name
	 */
	PersonalName(String name) {

		honorific = "";
		givenName = name;
		middleNames = "";
		surName = "";
		suffix = "";
	}

	/**
	 * Constructor to create multiple word personal names
	 * @param honorificInput
	 * @param givenNameInput
	 * @param middleNameInput
	 * @param surNameInput
	 * @param suffixInput
	 */
	PersonalName(String honorificInput, String givenNameInput, String middleNameInput, String surNameInput,
			String suffixInput) {
		honorific = honorificInput;
		givenName = givenNameInput;
		middleNames = middleNameInput;
		surName = surNameInput;
		suffix = suffixInput;

	};
	
	/**
	 * Returns full name
	 * @return
	 */
	public String getName() {
		// returns full name

		return this.toString();

	};
	/**
	 * Returns honorofic if it exists
	 * @return
	 */
	public String getHonorific() {
		// returns honorific if it exists
		return honorific;
	}
	/**
	 * returns second String of arraylist
	 * @return
	 */
	public String getGivenName() {
		// returns second String of arraylist
		return givenName;

	}
	/**
	 * returns all Strings between second and
	 * last Strings in array list
	 * @return
	 */
	public String getMiddleNames() {
		// returns all Strings between second and

		// last Strings in array list
		return middleNames;

	}
	/**
	 * returns final arraylist String
	 * @return
	 */
	public String getSurName() {
		// returns final arraylist String
		return surName;

	}
	/**
	 * returns Suffix (ie jr, sr, II) if it exists.
	 * @return
	 */
	public String getSuffix() {
		// returns Suffix (ie jr, sr, II) if it exists.

		// This will store in the

		// second
		return suffix;

	}
	
	/**
	 * Returns the full name as a single string, same as getName()
	 */
	public String toString() {

		String nameString = honorific + " " + givenName + " " + middleNames + " " + surName + " " + suffix;

		nameString = nameString.replaceAll("\\s+", " ");
		return nameString.trim();

	}

}
