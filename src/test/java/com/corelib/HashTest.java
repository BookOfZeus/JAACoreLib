package com.corelib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Unit test for Hash.
 */
public class HashTest
{
	/**
	 * Testing
	 *   test = 098F6BCD4621D373CADE4E832627B4F6
	 *
	 * @throws Exception will throw an exception if error
	 */
	@Test
	public void testHashTest() throws Exception
	{
		String p = Hash.md5("test");
		assertTrue(p.equalsIgnoreCase("098F6BCD4621D373CADE4E832627B4F6"));
	}

	/**
	 * Testing
	 *   hello = 5D41402ABC4B2A76B9719D911017C592
	 *
	 * @throws Exception will throw an exception if error
	 */
	@Test
	public void testBase64HelloWorld() throws Exception
	{
		String p = Hash.md5("hello");
		assertTrue(p.equalsIgnoreCase("5D41402ABC4B2A76B9719D911017C592"));
	}

	/**
	 * Testing
	 *   i_think_this_is_working = ec00a286a1b837f84d8fbb9539720756
	 *
	 * @throws Exception will throw an exception if error
	 */
	@Test
	public void testBase64LongString() throws Exception
	{
		String p = Hash.md5("i_think_this_is_working");
		assertTrue(p.equalsIgnoreCase("ec00a286a1b837f84d8fbb9539720756"));
	}

	@Test
	public void testConstructor() throws IllegalAccessException, InstantiationException
	{
		final Class<?> clazz = Hash.class;
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




