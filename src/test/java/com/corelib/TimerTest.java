package com.corelib;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Unit test for Timer.
 */
public class TimerTest
{
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

	@Test
	public void testConstructor() throws IllegalAccessException, InstantiationException
	{
		final Class<?> clazz = Timer.class;
		final Constructor<?> classConstructor = clazz.getDeclaredConstructors()[0];
		classConstructor.setAccessible(true);

		Throwable targetException = null;

		try {
			classConstructor.newInstance((Object[])null);
		} catch (InvocationTargetException ex) {
			targetException = ex.getTargetException();
		}
		assertNotNull(targetException);
		assertEquals(targetException.getClass(), InstantiationException.class);
	}
}