package com.corelib;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Crypt.java
 *
 * String encryption/decryption.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
class Crypt {

	/**
	 * Default 16 bits public key
	 */
	final static private String DEFAULT_KEY = "DefaultPublicKey";

	private final String key;
	private final String initVector = "ASecretHiddenKey"; // 16 bytes IV

	/**
	 * Constructor
	 *
	 * @param key Custom Salt
	 */
	public Crypt(String key)
	{
		if(key.equals("")) {
			this.key = Crypt.DEFAULT_KEY;
		}
		else {
			this.key = key;
		}
	}

	/**
	 * encrypt()
	 * Encrypt a string
	 *
	 * @param text The string to encrypt
	 * @return Byte[]
	 * @throws Exception Encryption Exception
	 */
	public String encrypt(String text) throws Exception
	{
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
		byte[] encrypted = cipher.doFinal(text.getBytes());
		return Base64.encode(encrypted);
	}

	/**
	 * decrypt()
	 * Decrypt a string
	 *
	 * @param encrypted Encrypted string
	 * @return String
	 * @throws Exception Decryption Exception
	 */
	public String decrypt(String encrypted) throws Exception
	{
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
		byte[] original = cipher.doFinal(Base64.decode(encrypted));
		return new String(original);
	}

	/**
	 * showAlgorithms()
	 * Show the list of algorithms
	 */
	public void showAlgorithms()
	{
		for (Object obj : java.security.Security.getAlgorithms("Cipher")) {             
			System.out.println(obj);                                                      
		}                                                                               
	}
}