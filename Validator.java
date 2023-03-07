//Jing Wei -- Student # 2343458
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
	
	public static boolean isPrefix(String str) {
		//if the length of input string is 0, then return false;
		if(str.length()==0) return false;
		char chr;
		//if First character is not alphanumeric, then return false;
		if(!isAlphaNum(str.charAt(0))) {
			return false;
		}
		for(int i=1; i<str.length(); i++) {
			chr = str.charAt(i);
			//if any char in input string is not y alphanumeric characters, underscores (_), periods (.), and dashes (-).then return false
			if(!isPrefixChar(chr)) {
				return false;
			}else if (isSpeciaChar(chr,true) && isSpeciaChar(str.charAt(i+1),true)) {
				//if the char in position i is a (-)(_)(.),and followed by another (-)(_)(.), then return false.
				return false;
			}
			
		}
		return true;
	}
	
	public static boolean isDomain(String str) {
		// if the first char in input string is a period, then return false
		if (str.charAt(0)=='.') return false;
		String[] strArr = null;
		strArr = str.split("\\.");
		//if strArr.length >2 means there are 2 or more '.' in the input string, return false
		if (strArr.length>2) {
			return false;
		}
		char chr;
		// first portion validation
		if (strArr[0].length()==1) return false;
		for(int i=0; i<strArr[0].length(); i++) {
			chr = strArr[0].charAt(i);
			//if first portion not contains only alphanumeric characters, periods, and dashes. return false;
			if (!isAlphaNum(chr) && !isSpeciaChar(chr,false)) {
				return false;
			}
		}
		//second portion validation, contains only letters
		if (strArr[1].length()<2) return false;
		for(int i=1; i<strArr[1].length();i++) {
			chr = strArr[1].charAt(i);
			if(!Character.isLetter(chr)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isEmail(String str) {
		String[] strArr = null;
		strArr = str.split("@");
		//if input string have more than two @, then return false
		if (strArr.length>2) return false; 
		
		//if isPrefix return false, then return false;
		if (!isPrefix(strArr[0])) return false;
		
		//if isDomain return false, then return false;
		if (!isDomain(strArr[1])) return false;
		
		return true;
	}
	

	
}
