package com.corelib;

import android.app.IntentService;
import android.content.Intent;
import android.os.StrictMode;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.String;

/**
 * DownloadService.java
 *
 * Download Service.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
@SuppressWarnings("ALL")
public class DownloadService extends IntentService
{
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

	private String response;
	private int httpCode;
	private long length;
	private String cacheFile;

	/**
	 * Constructor
	 *
	 */
	public DownloadService()
	{
		super("DownloadService");
		this.response = "";
		this.httpCode = 0;
		this.length = 0;
		this.cacheFile = "";
	}

	/**
	 * Override: onHandleIntent
	 *
	 * @param intent The intent
	 */
	@Override
	protected void onHandleIntent(Intent intent)
	{
		String url;
		//int method = DownloadService.GET;

		try {
			// Get the intent passed params
			url = intent.getStringExtra(DownloadService.URL);
			this.cacheFile = intent.getStringExtra(DownloadService.CACHE_FILE);

			// Make sure Strict mode is enabled
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
			HttpEntity httpEntity;
			HttpResponse httpResponse;

			HttpGet httpCall = new HttpGet(url);
			httpResponse = httpClient.execute(httpCall);

			this.httpCode = httpResponse.getStatusLine().getStatusCode();
			if(this.httpCode != HttpStatus.SC_OK) {
				return;
			}

			httpEntity = httpResponse.getEntity();
			length = httpEntity.getContentLength();
			this.response = EntityUtils.toString(httpEntity);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		this.publishResults();
	}

	/**
	 * publishResults
	 *
	 */
	private void publishResults()
	{
		Intent intent = new Intent(DownloadService.INTENT);
		intent.putExtra(DownloadService.RESPONSE, this.response);
		intent.putExtra(DownloadService.HTTP_CODE, this.httpCode);
		intent.putExtra(DownloadService.LENGTH, this.length);
		intent.putExtra(DownloadService.CACHE_FILE, this.cacheFile);
		sendBroadcast(intent);
	}
}