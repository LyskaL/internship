package llyska.services;

import java.util.regex.*;

class OperandValidatorImpl {
	private static final String REGEX_FOR_NUMBER = "^[+\\-]?\\d+(?:\\.\\d+)?$";

	public static boolean isValidString(String string) {
		Pattern p = Pattern.compile(REGEX_FOR_NUMBER);
		Matcher m = p.matcher(string);
		return m.matches();
	}
	
	
}
