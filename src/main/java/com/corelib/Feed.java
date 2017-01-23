package com.corelib;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Feed.java
 *
 * Feed manipulation.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
public class Feed {
	/**
	 * The class tag name
	 */
	private final static String TAG = "Feed";

	/**
	 * The Feed is an RSS
	 */
	public static final int RSS = 0;
	/**
	 * The Feed is a sitemap
	 */
	public static final int SITEMAP = 1;

	/**
	 * RSS parent key string
	 */
	private static final String KEY_RSS_PARENT   = "item";
	/**
	 * RSS title key string
	 */
	private static final String KEY_RSS_TITLE	= "title";
	/**
	 * RSS guid key string
	 */
	private static final String KEY_RSS_GUID	 = "guid";
	/**
	 * RSS link key string
	 */
	private static final String KEY_RSS_LINK	 = "link";
	/**
	 * RSS category key string
	 */
	private static final String KEY_RSS_CATEGORY = "category";
	/**
	 * RSS description key string
	 */
	private static final String KEY_RSS_DESC	 = "description";
	/**
	 * RSS pubDate key string
	 */
	private static final String KEY_RSS_DATE	 = "pubDate";
	/**
	 * RSS source key string
	 */
	private static final String KEY_RSS_SOURCE   = "source";
	/**
	 * Image key string
	 */
	private static final String KEY_RSS_IMAGE   = "media:content";

	/**
	 * Sitemap parent key string
	 */
	private static final String KEY_SITEMAP_PARENT   = "url";
	/**
	 * Sitemap loc key string
	 */
	private static final String KEY_SITEMAP_LOC	  = "loc";
	/**
	 * Sitemap last modification key string
	 */
	private static final String KEY_SITEMAP_LAST_MOD = "lastmod";
	/**
	 * Sitemap change frequency key string
	 */
	private static final String KEY_SITEMAP_FREQ	 = "changefreq";

	/**
	 * ERROR: The url is empty
	 */
	final static public int ERROR_EMPTY_URL = 1;
	/**
	 * ERROR: Cannot establish an Internet connection
	 */
	final static public int ERROR_FEED_NOT_FOUND = 2;
	/**
	 * ERROR: The feed is empty
	 */
	final static public int ERROR_EMPTY_FEED = 3;
	/**
	 * ERROR: Cannot the document
	 */
	final static public int ERROR_FEED_OLD = 4;
	/**
	 * ERROR: The DOM is not valid
	 */
	final static public int ERROR_INVALID_DOM = 5;

	// Properties
	private ArrayList<com.corelib.FeedItem> feeds;
	private int mode;
	private String file;
	private int timeMode;
	private int timeFrame;

	/**
	 * Constructor
	 *
	 * @param mode The mode (RSS or Sitemap)
	 * @param filename The filename of the feed
	 */
	public Feed(int mode, String filename) {
		this.feeds = new ArrayList<com.corelib.FeedItem>();
		this.file = filename;
		this.mode = mode;

		// Set the default time frame
		this.timeMode = Calendar.HOUR;
		this.timeFrame = 12;
	}

	/**
	 * Set the time mode (HOUR, MINUTE, DAY etc...)
	 *
	 * @param timeMode The time mode
	 */
	public void setTimeMode(int timeMode) {
		this.timeMode = timeMode;
	}

	/**
	 * Set the time frame
	 *
	 * @param timeFrame The amount of time
	 */
	public void setTimeFrame(int timeFrame) {
		this.timeFrame = timeFrame;
	}

	/**
	 * Process the RSS Feed
	 *
	 * @throws java.lang.Exception XML Exception
	 */
	public void processFeed() throws Exception {

		this.resetFeed();

		if(this.file.equals("")) {
			return;
		}

		//
		// Load the xml data
		//

		try {
			// Get new data
			com.corelib.XMLParser xmlParser = new com.corelib.XMLParser();
			String data = xmlParser.getXml(this.file);

			// Validate feed
			if(data.equals("")) {
				throw new Exception(Feed.TAG + ":" + Feed.ERROR_EMPTY_FEED);
			}

			// Parse feed
			Document doc = xmlParser.getDomElement(data);

			if(doc == null) {
				throw new Exception(Feed.TAG + ":" + Feed.ERROR_INVALID_DOM);
			}

			// Loop through the nodes
			String parent = Feed.KEY_RSS_PARENT;
			if(this.mode == Feed.SITEMAP) {
				parent = Feed.KEY_SITEMAP_PARENT;
			}

			NodeList nl = doc.getElementsByTagName(parent);
			int len = nl.getLength();
			if(len <= 0) {
				return;
			}

			for (int i = 0; i < len; i++) {
				Element e = (Element) nl.item(i);
				com.corelib.FeedItem fi = new com.corelib.FeedItem();
				if(this.mode == Feed.SITEMAP) {
					String loc = xmlParser.getValue(e, KEY_SITEMAP_LOC);
					if(loc.equals("")) {
						continue;
					}
					fi.setTitle(com.corelib.URLHandler.getNameFromUrl(loc));
					fi.setLink(loc);
					fi.setDate(xmlParser.getValue(e, KEY_SITEMAP_LAST_MOD));
				}
				else {
					String title = xmlParser.getValue(e, KEY_RSS_TITLE);
					if(title.equals("")) {
						continue;
					}
					fi.setTitle(title);
					fi.setDescription(xmlParser.getValue(e, KEY_RSS_DESC));
					fi.setLink(xmlParser.getValue(e, KEY_RSS_LINK));
					fi.setGuid(xmlParser.getValue(e, KEY_RSS_GUID));
					fi.setSource(xmlParser.getValue(e, KEY_RSS_SOURCE));
					fi.setCategory(xmlParser.getValue(e, KEY_RSS_CATEGORY));
					fi.setDate(xmlParser.getValue(e, KEY_RSS_DATE));
					fi.setImage(xmlParser.getValue(e, KEY_RSS_IMAGE));
				}
				this.feeds.add(fi);
			}
		}
		catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Get the feed list
	 *
	  * @return The list of feeds
	 */
	public ArrayList<com.corelib.FeedItem> getFeeds() {
		return this.feeds;
	}

	/**
	 * Empty the feed list
	 *
	 */
	private void resetFeed() {
		this.feeds = new ArrayList<com.corelib.FeedItem>();
	}
}
