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
class DeviceDimension
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
	 * The activity
	 */
	private Activity activity;

	/**
	 * Constructor
	 */
	public DeviceDimension()
	{
		this.activity = null;
	}

	/**
	 * Constructor
	 * @param activity The application activity
	 */
	public DeviceDimension(Activity activity)
	{
		this.activity = activity;
	}

	/**
	 * Check if the screen is small
	 * @param orientation The phone orientation
	 * @return boolean
	 */
	public boolean isSmall(int orientation)
	{
		int width = this.getActivityMetricWidthPixel();
		return width < DeviceDimension.MAX_PORTRAIT_WIDTH && orientation == Configuration.ORIENTATION_PORTRAIT ||
			width < DeviceDimension.MAX_LANDSCAPE_WIDTH && orientation == Configuration.ORIENTATION_LANDSCAPE;
	}

	/**
	 * Get the width in pixels
	 * @return int
	 */
	protected int getActivityMetricWidthPixel()
	{
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}
}