package com.corelib;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/*
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.AlgorithmParameters;
import java.util.Random;
import java.security.SecureRandom;
*/
/**
 * Crypt.java
 *
 * String encryption/decryption.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
public class Crypt {

	/**
	 * Default 32 bits public key
	 */
	final static private String DEFAULT_KEY = "DefaultPublicKey";

	// Properties
	private String key;
	private String initVector = "ASecretHiddenKey"; // 16 bytes IV

	/**
	 * Constructor
	 *
	 * @param salt Custom Salt
	 */
	public Crypt(String key) {
		if(key.equals("")) {
			this.key = this.DEFAULT_KEY;
		}
		else {
			this.key = key;
		}
	}

	/**
	 * encrypt()
	 * Encrypt a string
	 *
	 * @param  String text
	 * @return Byte[]
	 */
	public String encrypt(String text) throws Exception {
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(text.getBytes());
		return Base64.encode(encrypted);
	}

	/**
	 * decrypt()
	 * Decrypt a string
	 *
	 * @param  String Encrypted string
	 * @return String
	 */
	public String decrypt(String encrypted) throws Exception {
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		byte[] original = cipher.doFinal(Base64.decode(encrypted));
		return new String(original);
	}

	/**
	 * showAlgorithms()
	 * Show the list of algorithms
	 */
	public void showAlgorithms() {
		for (Object obj : java.security.Security.getAlgorithms("Cipher")) {             
			System.out.println(obj);                                                      
		}                                                                               
	}

}
