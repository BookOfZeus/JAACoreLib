package com.corelib;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.UnsupportedEncodingException;

/**
 * Unit test for Base64.
 */
public class Base64Test {

	@Test
	public void testBase64Hello() throws UnsupportedEncodingException
	{
		String text = "hello";
		String encoded = Base64.encode(text.getBytes("UTF-8"));
		String decoded = new String(Base64.decode(encoded));
		assertTrue(text.equals(decoded));
	}

	@Test
	public void testBase64HelloWorld() throws UnsupportedEncodingException
	{
		String text = "hello world!";
		String encoded = Base64.encode(text.getBytes("UTF-8"));
		String decoded = new String(Base64.decode(encoded));
		assertTrue(text.equals(decoded));
	}

		@Test
	public void testBase64LongString() throws UnsupportedEncodingException
	{
		String text = "another, maybe not, super long string!";
		String encoded = Base64.encode(text.getBytes("UTF-8"));
		String decoded = new String(Base64.decode(encoded));
		assertTrue(text.equals(decoded));
	}
}