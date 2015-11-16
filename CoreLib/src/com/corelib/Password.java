package com.corelib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.lang.Math;
import java.text.DecimalFormat;

/**
 * Password.java
 *
 * Password generator/validator.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
public class Password extends com.corelib.CoreLib {

	/**
	 * Default alpha lowercase letters
	 */
	private static final String ALPHA_L	= "abcdefghijklmnopqrstuvwxyz";
	/**
	 * Default alpha uppercase letters
	 */
	private static final String ALPHA_U	= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * Default numeric values
	 */
	private static final String NUM 	= "0123456789";
	/**
	 * Default special characters
	 */
	private static final String SPECIAL	= "!@#$%^&*_=+-/";

	/**
	 * generate()
	 * Generate a password using default settings
	 *
	 * @return String
	 */
	public static String generate() {
		return Password.generate(8);
	}

	/**
	 * generate()
	 * Generate a password using custom settings
	 *
	 * @param  int len Length of the string
	 * @return String
	 */
	public static String generate(int len) {

		int lenAlphaL = Password.ALPHA_L.length();
		int lenAlphaU = Password.ALPHA_U.length();
		int lenNum = Password.NUM.length();
		int lenSpecial = Password.SPECIAL.length();

		char c = '\0';
		Random charRand = new Random();
		Random location = new Random();
		List<String> pass = new ArrayList<String>();
		StringBuilder out = new StringBuilder("");

		for(int i = 0; i < len; i++) {
			switch(charRand.nextInt(4)) {
				case 0:
					c = Password.ALPHA_L.charAt(location.nextInt(lenAlphaL));
					break;
				case 1:
					c = Password.ALPHA_U.charAt(location.nextInt(lenAlphaU));
					break;
				case 2:
					c = Password.NUM.charAt(location.nextInt(lenNum));
					break;
				case 3:
				default:
					c = Password.SPECIAL.charAt(location.nextInt(lenSpecial));
					break;
			}
			pass.add(Character.toString(c));
		}

		Collections.shuffle(pass);

		for (String o : pass) {
			out.append(o);
		}
		return out.toString();
	}

	/**
	 * getScore()
	 * Get the score of a password (out of 100)
	 *
	 * @param  String pass The password
	 * @return Integer
	 */
	public static double getScore(String pass) {
		int len = pass.length();
		double score = 100;
		char c = '\0';

		int cntAlphaU = 0;
		int cntAlphaL = 0;
		int cntDigit = 0;
		int cntSpecial = 0;

		int coef = 0;

		// if len == 1 or less
		if(len < 2) {
			return 0;
		}


		for(int i = 0; i < len; i++) {
			c = pass.charAt(i);
			if(Character.isDigit(c)) {
				cntDigit++;
			}
			if(Character.isLowerCase(c)) {
				cntAlphaL++;
			}
			if(Character.isUpperCase(c)) {
				cntAlphaU++;
			}
			if(Password.SPECIAL.contains(c+"")) {
				cntSpecial++;
			}
		}

		// remove 25 per stack of 0
		score -= (cntDigit == 0 ? 25 : 0);
		score -= (cntAlphaL == 0 ? 25 : 0);
		score -= (cntAlphaU == 0 ? 25 : 0);
		score -= (cntSpecial == 0 ? 25 : 0);

		// calculate delta between non zero values
		List<Integer> delta = new ArrayList<Integer>();
		if(cntDigit > 0) {
			delta.add(cntDigit);
		}
		if(cntAlphaL > 0) {
			delta.add(cntAlphaL);
		}
		if(cntAlphaU> 0) {
			delta.add(cntAlphaU);
		}
		if(cntSpecial > 0) {
			delta.add(cntSpecial);
		}

		Collections.sort(delta);

		int ls = delta.size();
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		double factor = Password.getFactor(len) * (100 / (double)score);

		if(ls > 2) {
			for(int i = ls; i > 1; i--) {
				min = Math.min(min, Math.min(delta.get(i-1), delta.get(i-2)));
				max = Math.max(max, Math.max(delta.get(i-1), delta.get(i-2)));
			}

		}
		else if (ls == 2) {
			min = Math.min(delta.get(0), delta.get(1));
			max = Math.max(delta.get(0), delta.get(1));
		}

		int diff = max - min;
		return (double)Math.min(100, score + (diff * factor));
	}

	/**
	 * getFactor()
	 * Get the factor
	 *
	 * @param  int	l Length
	 * @return Integer
	 */
	private static int getFactor(int l) {
		if(l < 8) {
			return (8 - l) * -1;
		}
		return l - 7;
	}

}
