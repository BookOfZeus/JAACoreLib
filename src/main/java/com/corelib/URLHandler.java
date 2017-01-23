package com.corelib;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URLHandler.java
 *
 * URLs Handler class
 *
 * @author	Eric Potvin
 * @version 1.0
 */
public class URLHandler {

	private URL url;

	/**
	 * Constructor
	 *
	 * @param url The URL
	 */
	public URLHandler(URL url) {
		this.url = url;
	}

	/**
	 * Constructor
	 *
	 * @param url The URL
	 * @throws MalformedURLException Bad URL format
	 */
	public URLHandler(String url) throws MalformedURLException {
		this(new URL(url));
	}

	/**
	 * Get the path of the URL
	 *
	 * @return path
	 */
	public String getPath() {
		return this.url.getPath();
	}

	/**
	 * Get the name of the file from a URL with extension
	 *
	 * @param url The full URL
	 * @return The name of the file from that URL
	 * @throws Exception URL Exception
	 */
	public static String getNameFromUrl(String url) throws Exception {

		URLHandler urlHandler = new URLHandler(url);
		String path = urlHandler.getPath();

		if(path.length() == 0) {
			return "";
		}
		if(path.charAt(path.length() -1) == '/') {
			path = path.substring(0, path.length() -1);
		}
		path = path.substring( path.lastIndexOf('/') +1, path.length());

		if(path.contains("?")) {
			path = path.substring(0, path.lastIndexOf('?'));
		}

		if(path.contains(".")) {
			path = path.substring(0, path.lastIndexOf('.'));
		}
		path = path.replace("-", " ");
		path = path.toLowerCase();
		return com.corelib.Utils.capitalize(path);
	}
}
