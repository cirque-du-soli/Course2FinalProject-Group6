//This is our main Class file, containing all our methods
//Name & Student ID
//Name & Student ID


public class Validator {

	public static void main(String[] args) {
		

	}
	//check if input argument is a letter or a number, if yes return true, not return false
	public static boolean isAlphaNum(char chr) {
		if(Character.isLetterOrDigit(chr)) {
			return true;
		}
		return false;
	}
	
	//check if input argument is a '-','.', or if the boolean argument is true also allow for the underscore (_) to return true, return false otherwise.
	public static boolean isSpeciaChar(char chr, boolean isUnderscore) {
		if (chr == '-' || chr == '.' || (isUnderscore && chr == '_')) {
			return true;
		}
		return false;
	}
	
	//check if input argument is a character allowed in the prefix. return true if the character can be used in the prefix, false otherwise
	public static boolean isPrefixChar(char chr) {
		if(isAlphaNum(chr) || isSpeciaChar(chr, false)) {
			return true;
		}
		return false;
	}
	
	public static String fetchAfterAt(String str) {
		int indexOfAt =  str.indexOf('@');
		return str.substring(indexOfAt+1);
	}
	
	

}
