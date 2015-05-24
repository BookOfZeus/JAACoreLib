package com.corelib;

import android.app.IntentService;
import android.content.Intent;
import android.os.StrictMode;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.String;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DownloadService.java
 *
 * Download Service.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
public class DownloadService extends IntentService {
	/**
	 * The class tag name
	 */
	final static private String TAG = "DownloadService";

	/**
	 * The URL tag name
	 */
	public static final String URL = "url";
	/**
	 * The http code tag name
	 */
	public static final String HTTP_CODE = "httpcode";
	/**
	 * The result tag name
	 */
	public static final String RESPONSE = "result";
	/**
	 * The length tag name
	 */
	public static final String LENGTH = "length";
	/**
	 * The cacheFile tag name
	 */
	public static final String CACHE_FILE = "cacheFile";
	/**
	 * The IntentService tag name
	 */
	public static final String INTENT = "IntentService";

	// Properties
	private String response;
	private int httpCode;
	private long length;

	/**
	 * Constructor
	 *
	 */
	public DownloadService() {
		super("DownloadService");
	}

	/**
	 * Override: onHandleIntent
	 *
	 * @param intent The intent
	 */
	@Override
	protected void onHandleIntent(Intent intent) {

		// Setup the variables that we will use from the intent
		String url = null;
		//com.corelib.URLHandler urlHandler = null;
		//int method = DownloadService.GET;
		long length = 0;
		int responseCode = 0;
		String response = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		String cacheFile = "";

		try {

			// Get the intent passed params
			url = intent.getStringExtra(DownloadService.URL);
			cacheFile = intent.getStringExtra(DownloadService.CACHE_FILE);

			// Make sure Strick mode is enabled
			if (android.os.Build.VERSION.SDK_INT > 8) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}

			// Set http request params
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
			HttpConnectionParams.setSoTimeout(httpParams, 3000);

			// http client
			DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;

			// Checking http request method type
			/*
			if (method == com.corelib.DownloadService.POST) {
				HttpPost httpCall = new HttpPost(url);
				if (params != null || !params.isEmpty()) {
					httpCall.setEntity(new UrlEncodedFormEntity(params));
				}
				httpResponse = httpClient.execute(httpCall);
			}
			else {
				if (params != null || !params.isEmpty()) {
					String paramString = URLEncodedUtils.format(params, "utf-8");
					url += "?" + paramString;
				}
				HttpGet httpCall = new HttpGet(url);
				httpResponse = httpClient.execute(httpCall);
			}
			*/
			HttpGet httpCall = new HttpGet(url);
			httpResponse = httpClient.execute(httpCall);

			httpCode = httpResponse.getStatusLine().getStatusCode();
			if(httpCode != HttpStatus.SC_OK) {
				return;
			}

			httpEntity = httpResponse.getEntity();
			length = httpEntity.getContentLength();
			response = EntityUtils.toString(httpEntity);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		this.publishResults(response, httpCode, length, cacheFile);
	}

	/**
	 * publishResults
	 *
	 * @param response The response
	 * @param httpCode The HTTP Code received
	 * @param length The length of the request/response
	 * @param cacheFile The cached file
	 */
	private void publishResults(String response, int httpCode, long length, String cacheFile) {
		Intent intent = new Intent(DownloadService.INTENT);
		intent.putExtra(DownloadService.RESPONSE, response);
		intent.putExtra(DownloadService.HTTP_CODE, httpCode);
		intent.putExtra(DownloadService.LENGTH, length);
		intent.putExtra(DownloadService.CACHE_FILE, cacheFile);
		sendBroadcast(intent);
	}
}
