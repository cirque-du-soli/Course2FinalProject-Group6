//Jing Wei -- Student # 2343458
//Soliloquy Yarrow -- Student # 2342261

public class Validator {

	public static void main(String[] args) {

		// TESTS:
		System.out.println("test usernames:");
		System.out.println("isUsername (\"-User2\")   " + isUsername("-User2"));
		System.out.println("isUsername (\".cc123\")   " + isUsername(".cc123"));
		System.out.println("isUsername (\"userName\") " + isUsername("userName"));
		System.out.println("isUsername (\"#sd99\")    " + isUsername("#sd99"));
		System.out.println("isUsername(\"-.aemil\")   " + isUsername("-.aemil"));

		System.out.println("test passwords:");
		System.out.println("safePassword (\"H3ll0-WoRld\")  " + safePassword("H3ll0-WoRld")); // returns true???error in assignment 
		System.out.println("safePassword (\"W0w.Pr0ject\")  " + safePassword("W0w.Pr0ject")); // returns true
		System.out.println("safePassword (\"A1.b2-\")       " + safePassword("A1.b2-")); // returns false
		System.out.println("safePassword (\"D1nn3r-T1m3\")  " + safePassword("D1nn3r-T1m3")); // returns false
		System.out.println("safePassword (\"Pa55w0RDis0k\") " + safePassword("Pa55w0RDis0k")); // returns false
		
		/////////////////////// TO TEST: ///////////////////////////////
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

	// check if input argument is a letter or a number, if yes return true, not
	// return false
	public static boolean isAlphaNum(char chr) {
		if (Character.isLetterOrDigit(chr)) {
			return true;
		}
		return false;
	}

	// check if input argument is a '-','.', or if the boolean argument is true also
	// allow for the underscore (_) to return true, return false otherwise.
	public static boolean isSpecialChar(char chr, boolean isUnderscore) {
		if (chr == '-' || chr == '.' || (isUnderscore && chr == '_')) {
			return true;
		}
		return false;
	}

	// check if input argument is a character allowed in the prefix. return true if
	// the character can be used in the prefix, false otherwise
	public static boolean isPrefixChar(char chr) {
		if (isAlphaNum(chr) || isSpecialChar(chr, true)) { // true input because
			return true;
		}
		return false;
	}

	public static String fetchAfterAt(String str) {
		int indexOfAt = str.indexOf('@');
		return str.substring(indexOfAt + 1);
	}

	////// Soli, March 6

	public static boolean isDomainChar(char chr) {
		if (isAlphaNum(chr) || isSpecialChar(chr, false)) {
			return true;
		}
		return false;
	}

	// Check if string contains exactly one '@' character.
	public static boolean singleAtSign(String str) {
		int totalAts = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '@') {
				totalAts++;
			}
		}
		if (totalAts == 1) {
			return true;
		}
		return false;
	}

	// Fetch beginning part of email address, before the '@'. Assume only 1 '@'
	// sign.
	public static String fetchBeforeAt(String str) {
		int indexOfAt = str.indexOf('@');
		return str.substring(0, indexOfAt);
	}

	public static boolean isPrefix(String str) {
		// if the length of input string is 0, then return false;
		if (str.length() == 0)
			return false;
		char chr;
		// if First character is not alphanumeric, then return false;
		if (!isAlphaNum(str.charAt(0))) {
			return false;
		}
		for (int i = 1; i < str.length(); i++) {
			chr = str.charAt(i);
			// if any char in input string is not y alphanumeric characters, underscores
			// (_), periods (.), and dashes (-).then return false
			if (!isPrefixChar(chr)) {
				return false;
			} else if (isSpecialChar(chr, true) && isSpecialChar(str.charAt(i + 1), true)) {
				// if the char in position i is a (-)(_)(.),and followed by another (-)(_)(.),
				// then return false.
				return false;
			}

		}
		return true;
	}

	public static boolean isDomain(String str) {
		// if the first char in input string is a period, then return false
		if (str.charAt(0) == '.')
			return false;
		String[] strArr = null;
		strArr = str.split("\\.");
		//if strArr.length >2 means there are 2 or more '.' in the input string, is length ==1 mean no '.' in the input string return false
		if (strArr.length>2 || strArr.length==1) {
			return false;
		}
		char chr;
		// first portion validation
		if (strArr[0].length() == 1)
			return false;
		for (int i = 0; i < strArr[0].length(); i++) {
			chr = strArr[0].charAt(i);
			// if first portion not contains only alphanumeric characters, periods, and
			// dashes. return false;
			if (!isDomainChar(chr)) {
				return false;
			}
		}
		// second portion validation, contains only letters
		if (strArr[1].length() < 2)
			return false;
		for (int i = 1; i < strArr[1].length(); i++) {
			chr = strArr[1].charAt(i);
			if (!Character.isLetter(chr)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEmail(String str) {
		//if input string is not contain a single @ sign, then return false;
		if(!singleAtSign(str))
			return false;

		// if isPrefix return false, then return false;
		if (!isPrefix(fetchBeforeAt(str))) return false;

		// if isDomain return false, then return false;
		if (!isDomain(fetchAfterAt(str))) return false;

		return true;
	}

	public static String isUsername(String str) {

		String outputStr = "";
		boolean validUsername = true;
		boolean containsAlphaNum = false; // must contain one alphaNum

		boolean alphaNumFollows = true; // dash or period must be followed by an alphanum

		if (str.length() <= 7 && str.length() >= 1 && isSpecialChar(str.charAt(0), false)) { // if valid length & first char = '.' or '-'

			for (int i = 0; i < str.length(); i++) { // iterate through each character

				if (isDomainChar(str.charAt(i)) || str.charAt(i) == '!') { // if valid character

					if (i < str.length() - 1 // check all chars except last one. Last char i = str.length
							&& (isSpecialChar(str.charAt(i), false)) // if char = . or -
							&& !isAlphaNum(str.charAt(i + 1))) { // following char must be alphaNum

						validUsername = false;

					}

					if (isAlphaNum(str.charAt(i))) { // must make sure there's one alphaNum at least
						containsAlphaNum = true;
					}

				} else { // if not a valid character
					validUsername = false;
				}
			}

		} else { // if invalid length, or if first char is not . or -, skip for loop
			validUsername = false;
		}

		// must have at least 1 alphanum character
		if (!containsAlphaNum) {
			validUsername = false;
		}

		// return lowercase valid username, or empty string "" if invalid
		if (validUsername) {
			outputStr = str.toLowerCase();
		}
		return outputStr;

	}

	public static boolean safePassword(String str) {

		boolean isSafe = true;

		boolean containsUpper = false; // must contain one uppercase letter
		boolean containsLower = false; // must contain one lowercase letter
		boolean containsNum = false; // must contain one number
		boolean containsPDU = false; // must contain one Period, Dash, or Underscore

		boolean containsAlphaNum = false; // must contain one alphaNum
		
		boolean noRepeats = true; // no repeating characters

		if (str.length() >= 7 && str.length() <= 15) { // if valid length

			for (int i = 0; i < str.length(); i++) { // iterate through each character

				if (isDomainChar(str.charAt(i)) || str.charAt(i) == '_') { // check if valid character

					if (isAlphaNum(str.charAt(i))) { // must make sure there's one alphaNum at least
						containsAlphaNum = true;

						if (Character.isUpperCase(str.charAt(i))) {
							containsUpper = true;

						} else if (Character.isLowerCase(str.charAt(i))) {
							containsLower = true;

						} else if (Character.isDigit(str.charAt(i))) {
							containsNum = true;
						}

					} else if (isSpecialChar(str.charAt(i), true)) { // is a period, dash, or underscore
						containsPDU = true;
						
					}
				} else { // invalid character
					isSafe = false;
				}

				if (i < (str.length() - 1) // avoid checking last char, out of bounds
						&& str.charAt(i) == str.charAt(i + 1)) {
					noRepeats = false;
				}

			} // End of For Loop

		} else { // invalid length; bypass for loop
			isSafe = false;
		}

		// check criteria
		if (!containsUpper || !containsLower || !containsNum || !containsPDU || !containsAlphaNum || !noRepeats) {
			isSafe = false;
		}

		// OUTPUT
		return isSafe;
	}

}
