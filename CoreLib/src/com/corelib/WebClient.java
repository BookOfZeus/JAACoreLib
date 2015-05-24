package com.corelib;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * WebClient.java
 *
 * WebClient manipulation.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
public class WebClient extends WebViewClient {

	// Properties
	String domain;

	/**
	 * Constructor
	 *
	 * @param domain The domain
	 */
	public WebClient(String domain) {
		this.domain = domain;
	}

	/**
	 * shouldOverrideUrlLoading()
	 *
	 * Prevent domain injections
	 *
	 * @param	WebView	view	The view
	 * @param	String	url		The url
	 * @return	True of False
	 */
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		if(Uri.parse(url).getHost().endsWith(this.domain)) {
			return false;
		}
		view.loadUrl(url);
		return true;
	}

}