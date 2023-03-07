//Jing Wei -- Student # xyz
//Soliloquy Yarrow -- Student # 2342261

public class Validator {

	public static void main(String[] args) {

		// TESTS:
		System.out.println("the char B is AlphaNum: " + isAlphaNum('B'));
		System.out.println("the fetchBeforeAt: you@regreat);: " + fetchBeforeAt("you@regreat"));
		System.out.println("the char B is AlphaNum: " + isAlphaNum('B'));
		System.out.println("the char B is AlphaNum: " + isAlphaNum('B'));

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

	// same as isPrefixChar, except NO underscores.
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
	
	// Fetch beginning part of email address, before the '@'. Assume only 1 '@' sign.
	public static String fetchBeforeAt(String str) {
		int indexOfAt = str.indexOf('@');
		return str.substring(0, indexOfAt);
	}
}
