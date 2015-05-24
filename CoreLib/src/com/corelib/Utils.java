package com.corelib;

/**
 * Utils.java
 *
 * Utility class.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
public class Utils {

	/**
	 * Capitalize a string
	 *
	 * @param str The string
	 * @return The capitalized string
	 */
	public static String capitalize(String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	/**
	 * Add padding to the right of a string
	 *
	 * @param s The string
	 * @param n Number of characters
	 * @return The padded string
	 */
	public static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);  
	}

	/**
	 * Add padding to the left of a string
	 *
	 * @param s The string
	 * @param n Number of characters
	 * @return The padded string
	 */
	public static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);  
	}

}
