package com.corelib;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * DeviceDimension.java
 *
 * Get the device dimension information
 *
 * @author	Eric Potvin
 * @version 1.0
 */
@SuppressWarnings("ALL")
public final class DeviceDimension
{
	/**
	 * Maximum pixels for small devices (portrait)
	 */
	final static private int MAX_PORTRAIT_WIDTH = 550;
	/**
	 * Maximum pixels for small devices (landscape)
	 */
	final static private int MAX_LANDSCAPE_WIDTH = 850;

	/**
	 * constructor
	 */
	private DeviceDimension() throws InstantiationException
	{
		throw new InstantiationException("Create an instance of Timer is forbidden");
	}

	/**
	 * Check if the screen is small
	 *
	 * {@code
	 * <pre>
	 *
	 *  DisplayMetrics metrics = new DisplayMetrics();
	 *  activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
	 *  DeviceDimension.isSmall(imetrics.widthPixels, <Orientation>);
	 *  ->
	 *
	 * </pre>
	 * }
	 *
	 * @param orientation The phone orientation
	 * @return boolean
	 */
	public static boolean isSmall(int deviceWidth, int orientation)
	{
		return deviceWidth < DeviceDimension.MAX_PORTRAIT_WIDTH && orientation == Configuration.ORIENTATION_PORTRAIT ||
				deviceWidth < DeviceDimension.MAX_LANDSCAPE_WIDTH && orientation == Configuration.ORIENTATION_LANDSCAPE;
	}
}