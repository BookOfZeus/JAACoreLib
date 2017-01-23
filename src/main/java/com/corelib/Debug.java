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
public class Debug {

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
	 * @param tag The class tag
	 * @param msg The message
	 * @param method The class method called
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
	 * @param tag The class tag
	 * @param msg The message
	 */
	public static void v(String tag, String msg) {
		if(Debug.ENABLE) {
			Log.v(" -- " + tag + ": ", msg);
		}
	}
}