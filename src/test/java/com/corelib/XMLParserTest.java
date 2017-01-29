package com.corelib;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Unit test for XMLParser.
 */
public class XMLParserTest
{
	private static String XML_TEST = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
			+ "<rss version=\"2.0\">"
			+ "<channel>"
			+ "<title>XML Test file</title>"
			+ "<atom:link href=\"http://localhost/xml_test_file.xml\" rel=\"self\" type=\"application/rss+xml\" />"
			+ "<link>http://localhost/xml_test_file.xml</link>"
			+ "<description>XML Test file</description>"
			+ "<language>en-us</language>"
			+ "<copyright></copyright>"
			+ "<pubDate>Thu, 15 Oct 2015 06:15:01 -0400</pubDate>"

			+ "<item>"
			+ "<title>TITLE 1</title>"
			+ "<guid>http://localhost.com/guid1</guid>"
			+ "<link>http://localhost.com/link1</link>"
			+ "<category>Category 1</category>"
			+ "<description>Description 1</description>"
			+ "<pubDate>Thu, 15 Oct 2015 05:52:15 -0400</pubDate>"
			+ "<source url=\"http://localhost.com/source1\">Source 1</source>"
			+ "<media:content url=\"http://localhost.com/image1.png\" medium=\"image\">"
			+ "<media:title type=\"html\">media:title 1</media:title>"
			+ "</media:content>"
			+ "</item>"

			+ "<item>"
			+ "<title>TITLE 2</title>"
			+ "<guid>http://localhost.com/guid2</guid>"
			+ "<link>http://localhost.com/link2</link>"
			+ "<category>Category 2</category>"
			+ "<description>Description 2</description>"
			+ "<pubDate>Thu, 15 Oct 2015 15:52:15 -0400</pubDate>"
			+ "<source url=\"http://localhost.com/source2\">Source 2</source>"
			+ "</item>"

			+ "</channel>"
			+ "</rss>";

	private static String XML_TEST_EMPTY = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
			+ "<rss version=\"2.0\">"
			+ "<channel>"
			+ "<title>XML Test file</title>"
			+ "<atom:link href=\"http://localhost/xml_test_file.xml\" rel=\"self\" type=\"application/rss+xml\" />"
			+ "<link>http://localhost/xml_test_file.xml</link>"
			+ "<description>XML Test file</description>"
			+ "<language>en-us</language>"
			+ "<copyright></copyright>"
			+ "<pubDate>Thu, 15 Oct 2015 06:15:01 -0400</pubDate>"
			+ "<item>"
			+ "</item>"
			+ "</channel>"
			+ "</rss>";
	@Test
	public void testXMLParserSize() throws Exception
	{
		com.corelib.XMLParser xmlParser = new com.corelib.XMLParser();
		String data = XMLParserTest.XML_TEST;

		// Parse feed
		Document doc = xmlParser.getDomElement(data);
		String parent = "item"; //parent

		NodeList nl = doc.getElementsByTagName(parent);
		int len = nl.getLength();

		assertTrue(len == 2);
	}

	@Test
	public void testXMLParserItem1() throws Exception
	{
		com.corelib.XMLParser xmlParser = new com.corelib.XMLParser();
		String data = XMLParserTest.XML_TEST;

		// Parse feed
		Document doc = xmlParser.getDomElement(data);
		String parent = "item"; //parent

		NodeList nl = doc.getElementsByTagName(parent);
		int len = nl.getLength();

		Element e = (Element) nl.item(0);

		// get values
		String title = xmlParser.getValue(e, "title");
		String desc = xmlParser.getValue(e, "description");
		String link = xmlParser.getValue(e, "link");
		String guid = xmlParser.getValue(e, "guid");
		String source = xmlParser.getValue(e, "source");
		String category = xmlParser.getValue(e, "category");
		String date = xmlParser.getValue(e, "pubDate");
		String image = xmlParser.getValue(e, "media:content");

		assertTrue(title.equals("TITLE 1"));
		assertTrue(desc.equals("Description 1"));
		assertTrue(link.equals("http://localhost.com/link1"));
		assertTrue(guid.equals("http://localhost.com/guid1"));
		assertTrue(source.equals("Source 1"));
		assertTrue(category.equals("Category 1"));
		assertTrue(date.equals("Thu, 15 Oct 2015 05:52:15 -0400"));
		assertTrue(image.equals("http://localhost.com/image1.png"));
	}

	@Test
	public void testXMLParserItem2() throws Exception
	{
		com.corelib.XMLParser xmlParser = new com.corelib.XMLParser();
		String data = XMLParserTest.XML_TEST;

		// Parse feed
		Document doc = xmlParser.getDomElement(data);
		String parent = "item"; //parent

		NodeList nl = doc.getElementsByTagName(parent);
		// int len = nl.getLength();

		Element e = (Element) nl.item(1);

		// get values
		String title = xmlParser.getValue(e, "title");
		String desc = xmlParser.getValue(e, "description");
		String link = xmlParser.getValue(e, "link");
		String guid = xmlParser.getValue(e, "guid");
		String source = xmlParser.getValue(e, "source");
		String category = xmlParser.getValue(e, "category");
		String date = xmlParser.getValue(e, "pubDate");
		String image = xmlParser.getValue(e, "media:content");

		assertTrue(title.equals("TITLE 2"));
		assertTrue(desc.equals("Description 2"));
		assertTrue(link.equals("http://localhost.com/link2"));
		assertTrue(guid.equals("http://localhost.com/guid2"));
		assertTrue(source.equals("Source 2"));
		assertTrue(category.equals("Category 2"));
		assertTrue(date.equals("Thu, 15 Oct 2015 15:52:15 -0400"));
		assertTrue(image.equals(""));
	}

	@Test
	public void testEmpty() throws ParserConfigurationException, SAXException, IOException
	{
		com.corelib.XMLParser xmlParser = new com.corelib.XMLParser();
		Document doc = xmlParser.getDomElement("");

		assertTrue(doc == null);

		String data = xmlParser.getValue(null, "");
		assertTrue(data.equals(""));
	}

	@Test
	public void testValidEmpty() throws ParserConfigurationException, SAXException, IOException
	{
		com.corelib.XMLParser xmlParser = new com.corelib.XMLParser();
		Document doc = xmlParser.getDomElement(XMLParserTest.XML_TEST_EMPTY);
		String element = xmlParser.getValue(doc.getDocumentElement(), "item");

		assertTrue(element.equals(""));
	}
}