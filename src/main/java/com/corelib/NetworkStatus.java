package com.corelib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * NetworkStatus.java
 *
 * Network manipulation.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
class NetworkStatus {

	private Context context;

	/**
	 * Constructor
	 *
	 * @param context The context
	 */
	public NetworkStatus(Context context)
	{
		this.context = context;
	}

	/**
	 * Check if we have internet connection
	 *
	 * @return boolean
	 */
	public boolean canConnectToInternet()
	{
		ConnectivityManager cm = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(cm == null) {
			return false;
		}
		NetworkInfo activeNetworkInfo[] = cm.getAllNetworkInfo();
		if(activeNetworkInfo == null) {
			return false;
		}
		for (NetworkInfo anActiveNetworkInfo : activeNetworkInfo) {
			if (anActiveNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
				return true;
			}
		}
		return false;
	}
}
