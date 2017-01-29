package com.corelib;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit test for FontCache.
 */
public class FontCacheTest
{
	@Test
	public void testConstructor() throws IllegalAccessException, InstantiationException
	{
		final Class<?> clazz = FontCache.class;
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