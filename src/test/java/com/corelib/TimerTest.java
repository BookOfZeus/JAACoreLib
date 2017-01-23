package com.corelib;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit test for Timer.
 */
public class TimerTest {

	/**
	 * Convert 1 seconds
	 */
	@Test
	public void testTimer1Sec()
	{
		String p = Timer.convertSecondsToMinutes(1);
		assertTrue(p.equals("00:01"));
	}

	/**
	 * Convert 900 seconds
	 */
	@Test
	public void testBase64HelloWorld()
	{
		String p = Timer.convertSecondsToMinutes(900);
		assertTrue(p.equals("15:00"));
	}

	/**
	 * Convert 467 seconds
	 */
	@Test
	public void testBase64LongString()
	{
		String p = Timer.convertSecondsToMinutes(467);
		assertTrue(p.equals("07:47"));
	}
}