package com.corelib;

import java.security.MessageDigest;

/**
 * Hash a string

 * Create a hash of a string
 *
 * @author	Eric Potvin
 * @version 1.0
 */
@SuppressWarnings("ALL")
public class Hash
{
	/**
	 * Get the MD5 of a string
	 * @param str The string
	 * @return String
	 * @throws Exception Bytes error
	 */
	public static String md5(String str) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());

		byte byteData[] = md.digest();

		StringBuilder hexString = new StringBuilder();
		for (byte aByteData : byteData) {
			String hex = Integer.toHexString(0xff & aByteData);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
