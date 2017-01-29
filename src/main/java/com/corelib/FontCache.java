package com.corelib;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import java.util.Hashtable;

/**
 * FontCache.java
 *
 * Cache TTF.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
@SuppressWarnings("ALL")
public final class FontCache
{
	/**
	 * Cached fontCache
	 */
	private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

	/**
	 * constructor
	 */
	private FontCache() throws InstantiationException
	{
		throw new InstantiationException("Create an instance of FontCache is forbidden");
	}

	/**
	 * Get the font
	 *
	 * @param name The font name
	 * @param assetManager The application asset manager
	 * @return TypeFace
	 */
	public static Typeface get(String name, AssetManager assetManager)
	{
		Typeface tf = fontCache.get(name);
		if(tf == null) {
			try {
				tf = Typeface.createFromAsset(assetManager, name);
			}
			catch (Exception e) {
				return null;
			}
			fontCache.put(name, tf);
		}
		return tf;
	}
}
