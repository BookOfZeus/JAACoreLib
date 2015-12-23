package com.corelib;

import android.util.Log;

/**
 * Debug.java
 *
 * Debug class.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
public class Debug extends com.corelib.CoreLib {

	/**
	 * Enable or disable debug
	 */
	private static final boolean ENABLE = true;

	/**
	 * Contructor()
	 */
	public Debug() {
	}

	/**
	 * v()
	 * Verbose debug
	 *
	 * @param String tag
	 * @param String msg
	 * @param String method
	 */
	public static void v(String tag, String msg, String method) {
		if(Debug.ENABLE) {
			Log.v(" -- " + tag + ": ", "(" + method + ") " + msg);
		}
	}

	/**
	 * v()
	 * Verbose debug
	 *
	 * @return String
	 */
	public static void v(String tag, String msg) {
		if(Debug.ENABLE) {
			Log.v(" -- " + tag + ": ", msg);
		}
	}
}
