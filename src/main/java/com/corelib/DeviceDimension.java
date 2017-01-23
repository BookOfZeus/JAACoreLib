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
public class DeviceDimension {

	/**
	 * Maximum pixels for small devices (portrait)
	 */
	final static private int MAX_PORTRAIT_WIDTH = 550;
	/**
	 * Maximum pixels for small devices (landscape)
	 */
	final static private int MAX_LANDSCAPE_WIDTH = 850;

	/**
	 * Check if the screen is small
	 * @param activity The application activity
	 * @param orientation The phone orientation
	 * @return boolean
	 */
	public static boolean isSmall(Activity activity, int orientation) {

		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		return width < DeviceDimension.MAX_PORTRAIT_WIDTH && orientation == Configuration.ORIENTATION_PORTRAIT
			? true
			: width < DeviceDimension.MAX_LANDSCAPE_WIDTH && orientation == Configuration.ORIENTATION_LANDSCAPE;
	}
}

