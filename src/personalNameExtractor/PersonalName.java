package personalNameExtractor;

import java.util.ArrayList;

/*
 * The personal name class stores a personal name as an arraylist
 * The prefix (IE: Mr, Dr, Mrs)will be stored in the first index of the
 * arraylist
 * The suffix (IE: Jr, Sr, III) will be stored as the second index of the
 * arraylist
 * The given name will be stored in the third index
 * The surname will be stored in the final index
 * Any middle names will be stored between the 3rd and final indices.
 */

public class PersonalName {

	PersonalName() {
		// default constructor for PersonalName

	}

	PersonalName(String name) {
		/*
		 * This constructor would check for an honorific first, if none is found
		 * set first arraylist item to null, It will then check for a suffix and
		 * store that as the second index. The remaining parts of the name will
		 * be stored from the third index on.
		 */

	};

	public boolean getName() { // returns full name
		boolean check = true;

		return check;

	};

	public String getHonorific() { // returns honorific if it exists
		return null;
	}

	public String getGivenName() { // returns second String of arraylist
		return null;

	}

	public String getMiddleNames() { // returns all Strings between second and
										// last Strings in array list
		return null;

	}

	public String getSurName() { // returns final arraylist String
		return null;

	}

	public String getSuffix() { // returns Suffix (ie jr, sr, II) if it exists.
								// This will store in the
								// second
		return null;

	}

}
