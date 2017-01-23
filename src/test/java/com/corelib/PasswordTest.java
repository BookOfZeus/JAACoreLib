package com.corelib;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit test for Password.
 */
public class PasswordTest {

	@Test
	public void testPasswordLength()
	{
		String p;

		Password p1 = new Password(true, true, true, true);
		p = p1.generate();
		assertTrue(p.length() == 8);

		Password p2 = new Password(true, true, true, true);
		p = p2.generate(1444);
		assertTrue(p.length() == 14);
	}

	@Test
	public void testPasswordGenerateLower()
	{
		Password pLower = new Password(false, true, false, false);

		String pass = pLower.generate(4);
		double score = Password.getScore(pass);
		assertTrue(score == 9);
	}

	@Test
	public void testPasswordGenerateUpper()
	{
		Password pUpper = new Password(true, false, false, false);

		String pass = pUpper.generate(4);
		double score = Password.getScore(pass);
		assertTrue(score == 9);
	}

	@Test
	public void testPasswordGenerateDigit()
	{
		Password pDigit = new Password(false, false, true, false);

		String pass = pDigit.generate(4);
		double score = Password.getScore(pass);
		assertTrue(score == 9);
	}

	@Test
	public void testPasswordGenerateSpecial()
	{
		Password pSpec = new Password(false, false, false, true);

		String pass = pSpec.generate(4);
		double score = Password.getScore(pass);
		assertTrue(score == 9);
	}

	@Test
	public void testPasswordGenerateDigitSpecial()
	{
		Password pDS = new Password(false, false, true, true);

		String pass = pDS.generate(4);
		double score = Password.getScore(pass);
		assertTrue(score >= 33);

		Password pDS2 = new Password(false,false, true,true);

		pass = pDS2.generate(8);
		score = Password.getScore(pass);
		assertTrue(score >= 50 && score <= 65);
	}

	@Test
	public void testPasswordGenerateGood()
	{
		double score;

		score = Password.getScore("good");
		assertTrue(score == 9);

		score = Password.getScore("Good");
		assertTrue(score == 34.0);

		score = Password.getScore("G0od");
		assertTrue(score >= 69);

		score = Password.getScore("G0@d");
		assertTrue(score == 100);
	}

	@Test
	public void testPasswordGenerateHello()
	{
		double score;

		score = Password.getScore("hello");
		assertTrue(score == 13);

		score = Password.getScore("Hello");
		assertTrue(score == 32);

		score = Password.getScore("H3llo");
		assertTrue(score == 67);

		score = Password.getScore("H3ll0");
		assertTrue(score == 71);

		score = Password.getScore("H3l!0");
		assertTrue(score == 97);
	}

	@Test
	public void testPasswordGeneratePassword()
	{
		double score;

		score = Password.getScore("password");
		assertTrue(score == 29);

		score = Password.getScore("Password");
		assertTrue(score == 62);

		score = Password.getScore("P@ssword");
		assertTrue(score >= 81);

		score = Password.getScore("P@sswoRd");
		assertTrue(score >= 80);

		score = Password.getScore("P@ssw0Rd");
		assertTrue(score == 100);

		score = Password.getScore("P@5sw0Rd");
		assertTrue(score == 100);

		score = Password.getScore("P@5sw0R!");
		assertTrue(score == 100);
	}

	@Test
	public void testPasswordGenerateVeryGood()
	{
		double score;

		score = Password.getScore("verygood");
		assertTrue(score == 29);

		score = Password.getScore("verygoodall");
		assertTrue(score == 41);

		score = Password.getScore("BACADJAGGJG");
		assertTrue(score == 41);

		score = Password.getScore("ZYUSMBJSSKJAJGWQ");
		assertTrue(score == 61);
	}
}