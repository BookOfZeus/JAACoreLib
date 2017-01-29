package com.corelib;

import static org.junit.Assert.*;

import org.junit.Test;
import android.content.res.Configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Unit test for DeviceDimension.
 */
public class DeviceDimensionTest
{
	@Test
	public void testIsSmallPortrait() {
		assertTrue(DeviceDimension.isSmall(400, Configuration.ORIENTATION_PORTRAIT));

		assertTrue(DeviceDimension.isSmall(549, Configuration.ORIENTATION_PORTRAIT));

		assertFalse(DeviceDimension.isSmall(551, Configuration.ORIENTATION_PORTRAIT));
	}

	@Test
	public void testIsSmallLandscape() {
		assertTrue(DeviceDimension.isSmall(400, Configuration.ORIENTATION_LANDSCAPE));

		assertTrue(DeviceDimension.isSmall(849, Configuration.ORIENTATION_LANDSCAPE));

		assertFalse(DeviceDimension.isSmall(851, Configuration.ORIENTATION_LANDSCAPE));
	}

	@Test
	public void testConstructor() throws IllegalAccessException, InstantiationException
	{
		final Class<?> clazz = DeviceDimension.class;
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
