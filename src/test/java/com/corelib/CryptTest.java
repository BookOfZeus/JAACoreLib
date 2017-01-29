package com.corelib;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Set;

/**
 * Unit test for Crypt.
 */
public class CryptTest
{
	@Test
	public void testCryptBasic() throws Exception
	{
		String str;
		String enc;
		String dec;
		String key = "k94q8h494q8h41ek"; // 16 chars

		Crypt crypt = new Crypt(key);

		str = "basic";
		enc = crypt.encrypt(str);
		dec = crypt.decrypt(enc);

		assertTrue(str.equals(dec));
	}

	@Test
	public void testEmpty() throws Exception
	{
		String str;
		String enc;
		String dec;
		String key = ""; // 0 bites, should use default

		Crypt c = new Crypt(key);

		//
		str = "test1";
		enc = c.encrypt(str);
		dec = c.decrypt(enc);

		assertTrue(str.equals(dec));
	}

	@Test
	public void testShowAlgorithms()
	{
		Set<String> algorithm = Crypt.getAvailableAlgorithms();
		assertTrue(algorithm != null);
		assertTrue(algorithm.size() != 0);
	}
}