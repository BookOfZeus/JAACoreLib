package com.corelib;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.Math;

/**
 * Password.java
 *
 * Password generator/validator.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
class Password {

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
	private static final String NUM 	= "01234567890123456789";
	/**
	 * Default special characters
	 */
	private static final String SPECIAL	= "!@#$%^&*_=+-/|!@#$%^&*_=+-/|";
	/**
	 * Use upper case?
	 */
	private boolean useUpper;
	/**
	 * Use lower case?
	 */
	private boolean useLower;
	/**
	 * Use numbers?
	 */
	private boolean useDigit;
	/**
	 * Use special chars?
	 */
	private boolean useSpecial;

	/**
	 * Contructor()
	 */
	public Password() {
		this.useUpper = true;
		this.useLower = true;
		this.useDigit = true;
		this.useSpecial = true;
	}

	/**
	 * generate()
	 * Generate a password using default settings
	 *
	 * @return String
	 */
	public String generate() {
		return this.generate(8);
	}

	/**
	 * generate()
	 * Generate a password using custom settings
	 *
	 * @param len Length of the string
	 * @return String
	 */
	public String generate(int len) {
		len = Math.min(len, 14);

		StringBuilder out = new StringBuilder("");

		if(this.useUpper) {
			out.append(Password.ALPHA_U);
			out = Utils.shuffle(out.toString());
		}
		if(this.useLower) {
			out.append(Password.ALPHA_L);
			out = Utils.shuffle(out.toString());
		}
		if(this.useDigit) {
			out.append(Password.NUM).append(Password.NUM);
			out = Utils.shuffle(out.toString());
		}
		if(this.useSpecial) {
			out.append(Password.SPECIAL);
			out = Utils.shuffle(out.toString());
		}
		return Utils.shuffle(out.toString()).toString().substring(0, len);
	}

	/**
	 * setUseUpper()
	 * Enable or disable the usage of upper case letters
	 *
	 * @param useUpper Use the upper case or not
	 */
	public void setUseUpper(boolean useUpper) {
		this.useUpper = useUpper;
	}

	/**
	 * setUseLower()
	 * Enable or disable the usage of lower case letters
	 *
	 * @param useLower Use the lower case or not
	 */
	public void setUseLower(boolean useLower) {
		this.useLower = useLower;
	}

	/**
	 * setUseDigit()
	 * Enable or disable the usage of numbers
	 *
	 * @param useDigit Use numbers or not
	 */
	public void setUseDigit(boolean useDigit) {
		this.useDigit = useDigit;
	}

	/**
	 * setUseSpecial()
	 * Enable or disable the usage of special characters
	 *
	 * @param useSpecial Use special chars or not
	 */
	public void setUseSpecial(boolean useSpecial) {
		this.useSpecial = useSpecial;
	}

	/**
	 * getScore()
	 * Get the score of a password (out of 100)
	 *
	 * @param pass The password
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
		return Math.min(100, score + (diff * factor));
	}

	/**
	 * getFactor()
	 * Get the factor
	 *
	 * @param len Length
	 * @return Integer
	 */
	private static int getFactor(int len) {
		if(len < 8) {
			return (8 - len) * -1;
		}
		return len - 7;
	}
}
