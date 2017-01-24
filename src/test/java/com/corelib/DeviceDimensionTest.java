package com.corelib;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Test;
import android.content.res.Configuration;

/**
 * Unit test for DeviceDimension.
 */
public class DeviceDimensionTest
{
	@Test
	public void testIsSmallPortrait() {
		DeviceDimension mock = mock(DeviceDimension.class);
		when(mock.getActivityMetricWidthPixel()).thenReturn(400);
		when(mock.isSmall(Configuration.ORIENTATION_PORTRAIT)).thenCallRealMethod();
		assertTrue(mock.isSmall(Configuration.ORIENTATION_PORTRAIT));

		when(mock.getActivityMetricWidthPixel()).thenReturn(549);
		assertTrue(mock.isSmall(Configuration.ORIENTATION_PORTRAIT));

		when(mock.getActivityMetricWidthPixel()).thenReturn(551);
		assertFalse(mock.isSmall(Configuration.ORIENTATION_PORTRAIT));
	}

	@Test
	public void testIsSmallLandscape() {
		DeviceDimension mock = mock(DeviceDimension.class);
		when(mock.getActivityMetricWidthPixel()).thenReturn(400);
		when(mock.isSmall(Configuration.ORIENTATION_LANDSCAPE)).thenCallRealMethod();
		assertTrue(mock.isSmall(Configuration.ORIENTATION_LANDSCAPE));

		when(mock.getActivityMetricWidthPixel()).thenReturn(849);
		assertTrue(mock.isSmall(Configuration.ORIENTATION_LANDSCAPE));

		when(mock.getActivityMetricWidthPixel()).thenReturn(851);
		assertFalse(mock.isSmall(Configuration.ORIENTATION_LANDSCAPE));
	}
}
