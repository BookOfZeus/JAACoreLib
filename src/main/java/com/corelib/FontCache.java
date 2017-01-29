package com.corelib;

import android.content.Context;
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
	 * Get the font
	 *
	 * @param name The font name
	 * @param context The application context
	 * @return TypeFace
	 */
	public static Typeface get(String name, Context context)
	{
		Typeface tf = fontCache.get(name);
		if(tf == null) {
			try {
				tf = Typeface.createFromAsset(context.getAssets(), name);
			}
			catch (Exception e) {
				return null;
			}
			fontCache.put(name, tf);
		}
		return tf;
	}
}
