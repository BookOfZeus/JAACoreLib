package com.corelib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * FeedDB.java
 *
 * Database Feed manipulation.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
class FeedDB extends SQLiteOpenHelper {
	/**
	 * The class tag name
	 */
	final static private String TAG = "FeedDB";

	/**
	 * The database version
	 */
	private static final int DATABASE_VERSION = 1;

	/**
	 * The feed database name
	 */
	private static final String DATABASE_NAME = "feed";

	/**
	 * The feed table name
	 */
	private static final String TABLE_NAME = "feed_item";

	/**
	 * The feed table field id
	 */
	private static final String FIELD_ID = "id";
	/**
	 * The feed table field title
	 */
	private static final String FIELD_TITLE = "title";
	/**
	 * The feed table field link
	 */
	private static final String FIELD_LINK = "link";
	/**
	 * The feed table field category
	 */
	private static final String FIELD_CATEGORY = "category";
	/**
	 * The feed table field description
	 */
	private static final String FIELD_DESCRIPTION = "description";
	/**
	 * The feed table field date
	 */
	private static final String FIELD_DATE = "date";
	/**
	 * The feed table field source
	 */
	private static final String FIELD_SOURCE = "source";

	/**
	 * Constructor
	 *
	 * @param context The context
	 */
	public FeedDB(Context context)
	{
		super(context, FeedDB.DATABASE_NAME, null, FeedDB.DATABASE_VERSION);
	}

	/**
	 * Create the database
	 *
	 * @param db The SQLite database
	 */
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String createTable =
				"CREATE TABLE IF NOT EXISTS " + FeedDB.DATABASE_NAME + "." + FeedDB.TABLE_NAME + "("
				+ FeedDB.FIELD_ID + " INTEGER PRIMARY KEY,"
				+ FeedDB.FIELD_TITLE + " TEXT,"
				+ FeedDB.FIELD_LINK + " TEXT,"
				+ FeedDB.FIELD_CATEGORY + " TEXT,"
				+ FeedDB.FIELD_DESCRIPTION + " TEXT,"
				+ FeedDB.FIELD_DATE + " TEXT,"
				+ FeedDB.FIELD_SOURCE + " TEXT,"
				+ ")";
		db.execSQL(createTable);
	}

	/**
	 * Upgrading the database
	 *
	 * @param db The SQLite database
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + FeedDB.TABLE_NAME);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Add a feed
	 *
	 * @param feed A FeedItem Object
	 */
	public void addFeed(com.corelib.FeedItem feed)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues contentValues = this.populate(feed);

		// Inserting Row
		db.insert(FeedDB.TABLE_NAME, null, contentValues);
		db.close();
	}

	/**
	 * Get a feed
	 *
	 * @param id The FeedItem id
	 * @return A FeedItem object
	 */
	public com.corelib.FeedItem getFeed(int id)
	{
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(
				FeedDB.TABLE_NAME,
				new String[] {
						FeedDB.FIELD_ID,
						FeedDB.FIELD_TITLE,
						FeedDB.FIELD_LINK,
						FeedDB.FIELD_CATEGORY,
						FeedDB.FIELD_DESCRIPTION,
						FeedDB.FIELD_DATE,
						FeedDB.FIELD_SOURCE
				},
				FeedDB.FIELD_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null
		);

		if (cursor != null) {
			cursor.moveToFirst();
		}

		return this.populate(cursor);
	}

	/**
	 * Get all feeds
	 *
	 * @return A list of FeedItem objects
	 */
	public List<com.corelib.FeedItem> getAllFeed()
	{
		List<com.corelib.FeedItem> feedList = new ArrayList<>();

		String selectQuery = "SELECT  * FROM " + FeedDB.TABLE_NAME;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				feedList.add(this.populate(cursor));
			}
			while (cursor.moveToNext());
		}

		return feedList;
	}

	/**
	 * Get the number of feeds
	 *
	 * @return The number of feeds
	 */
	public int getFeedCount()
	{
		String countQuery = "SELECT  * FROM " + FeedDB.TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		return cursor.getCount();
	}

	/**
	 * Update a feed
	 *
	 * @param feedItem A FeedItem object
	 * @return The number of rows affected
	 */
	public int updateFeed(com.corelib.FeedItem feedItem)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues contentValues = this.populate(feedItem);

		// updating row
		return db.update(FeedDB.TABLE_NAME, contentValues, FeedDB.FIELD_ID + " = ?",
				new String[] { String.valueOf(feedItem.getId()) });
	}

	/**
	 * Delete a feed
	 *
	 * @param id The FeedItem id
	 */
	public void deleteFeed(int id)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(FeedDB.TABLE_NAME, FeedDB.FIELD_ID + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
	}

	/**
	 * Delete all feeds
	 *
	 */
	public void deleteAllFeed()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM " + FeedDB.TABLE_NAME);
		db.execSQL("VACUUM");
		db.close();
	}

	/**
	 * Populate a ContentValue from a FeedItem object
	 *
	 * @param feedItem A FeedItem object
	 * @return The content value object
	 */
	private ContentValues populate(com.corelib.FeedItem feedItem)
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(FeedDB.FIELD_TITLE, feedItem.getTitle());
		contentValues.put(FeedDB.FIELD_LINK, feedItem.getLink());
		contentValues.put(FeedDB.FIELD_CATEGORY, feedItem.getCategory());
		contentValues.put(FeedDB.FIELD_DESCRIPTION, feedItem.getDescription());
		contentValues.put(FeedDB.FIELD_DATE, feedItem.getDate());
		contentValues.put(FeedDB.FIELD_SOURCE, feedItem.getSource());
		return contentValues;
	}

	/**
	 * Populate a FeedItem from a Cursor
	 *
	 * @param cursor A sqlite cursor
	 * @return A FeedItem Object
	 */
	private com.corelib.FeedItem populate(Cursor cursor)
	{
		com.corelib.FeedItem feedItem = new com.corelib.FeedItem();
		feedItem.setId(Integer.parseInt(cursor.getString(0)));
		feedItem.setTitle(cursor.getString(1));
		feedItem.setLink(cursor.getString(2));
		feedItem.setCategory(cursor.getString(3));
		feedItem.setDescription(cursor.getString(4));
		feedItem.setDate(cursor.getString(5));
		feedItem.setSource(cursor.getString(6));
		return feedItem;
	}
}