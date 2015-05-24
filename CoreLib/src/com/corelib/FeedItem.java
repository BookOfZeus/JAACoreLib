package com.corelib;

/**
 * FeedItem.java
 *
 * FeedItem object.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
public class FeedItem {

	// Properties
	int id;
	String title;
	String link;
	String guid;
	String category;
	String description;
	String date;
	String source;

	/**
	 * Constructor
	 *
	 */
	public FeedItem() {
		this.id = 0;
		this.title = "";
		this.link = "";
		this.guid = "";
		this.category = "";
		this.description = "";
		this.date = "";
		this.source = "";
	}

	/**
	 * Get the string version of the object
	 *
	 * @return Success or failure
	 */
	@Override
	public String toString() {
		return this.title + "\n" + this.date + "\n" + category;
	}

	/**
	 * Get the id
	 *
	 * @return The id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the id
	 *
	 * @param id The id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the title
	 *
	 * @return The title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title
	 *
	 * @param title The title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the link
	 *
	 * @return The link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Set the link
	 *
	 * @param link The link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Get the guid
	 *
	 * @return The guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * Set the guid
	 *
	 * @param guid The guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * Get the category
	 *
	 * @return The category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Set the category
	 *
	 * @param category The category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Get the description
	 *
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description
	 *
	 * @param description The description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the date
	 *
	 * @return The date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Set the date
	 *
	 * @param date The date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Get the source
	 *
	 * @return The source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Set the source
	 *
	 * @param source The source
	 */
	public void setSource(String source) {
		this.source = source;
	}

}
