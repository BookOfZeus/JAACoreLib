package com.corelib;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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

	/**
	 * Convert pixel to DP
	 *
	 * @param dentisty The density ratio
	 * @param pixels The pixels size
	 * @return size in DP
	 */
	public static int convertPXToDP(float density, int pixels) {
		return (int) (pixels * density + 0.5f);
	}

	/**
	 * Copy input stream to an output stream
	 *
	 * @param is Input stream
	 * @param os Output stream
	 */
	public static void CopyStream(InputStream is, OutputStream os)
	{
		final int buffer_size = 1024;
		try {
			byte[] bytes=new byte[buffer_size];
			for(;;) {
				int count=is.read(bytes, 0, buffer_size);
				if(count==-1) {
					break;
				}
				os.write(bytes, 0, count);
			}
		}
		catch(Exception ex) {
			//
		}
	}

	/**
	 * Shuffle a string
	 *
	 * @param String input Input stream
	 * @return StringBuilder
	 */
	public static StringBuilder shuffle(String input){
		List<Character> characters = new ArrayList<Character>();
		for(char c:input.toCharArray()){
			characters.add(c);
		}
		StringBuilder output = new StringBuilder(input.length());
		while(characters.size()!=0){
			int randPicker = (int)(Math.random()*characters.size());
			output.append(characters.remove(randPicker));
		}
		return output;
	}
}
