package com.corelib;

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
class NetworkStatus
{
	/**
	 * connectivityManager to use
	 */
	private ConnectivityManager connectivityManager;

	/**
	 * Constructor
	 *
	 * <pre>
	 * {@code
	 * NetworkStatus ns = new NetworkStatus(
	 *   <application>.context.getSystemService(Context.CONNECTIVITY_SERVICE)
	 * );
	 * </pre>
	 *
	 * @param context The context
	 */
	public NetworkStatus(ConnectivityManager connectivityManager)
	{
		this.connectivityManager = connectivityManager;
	}

	/**
	 * Check if we have internet connection
	 *
	 * @return boolean
	 */
	public boolean canConnectToInternet()
	{
		if(this.connectivityManager == null) {
			return false;
		}
		NetworkInfo activeNetworkInfo[] = this.connectivityManager.getAllNetworkInfo();
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