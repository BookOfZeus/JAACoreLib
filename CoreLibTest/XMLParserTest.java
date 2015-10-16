import com.corelib.*;
import java.util.Calendar;
import java.io.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Calendar;

public class XMLParserTest extends CoreLibTest {

	private static String XML_TEST      = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
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

	// Start test

	private int test_size_items() {

		int valid = 0;

		try {
			com.corelib.XMLParser xmlParser = new com.corelib.XMLParser();
			String data = this.XML_TEST;

			// Parse feed
			Document doc = xmlParser.getDomElement(data);
			String parent = "item"; //parent

			NodeList nl = doc.getElementsByTagName(parent);
			int len = nl.getLength();

			valid += this.assertTrue("test.test_size_items failed: the number of elements should be 2", len == 2);

			valid += this.assertTrue("test.test_size_items failed: the number of elements should be 2", len != 0);

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return valid;
	}

	private int test_item_1() {

		int valid = 0;

		try {
			com.corelib.XMLParser xmlParser = new com.corelib.XMLParser();
			String data = this.XML_TEST;

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

			// bad test
			valid += this.assertTrue("test.test_title failed: the title should be TITLE 1", !title.equals(""));
			valid += this.assertTrue("test.test_title failed: the desc should be Description 1", !desc.equals(""));
			valid += this.assertTrue("test.test_title failed: the link should be http://localhost.com/link1", !link.equals(""));
			valid += this.assertTrue("test.test_title failed: the guid should be http://localhost.com/guid1", !guid.equals(""));
			valid += this.assertTrue("test.test_title failed: the source should be Source 1", !source.equals(""));
			valid += this.assertTrue("test.test_title failed: the category should be Category 1", !category.equals(""));
			valid += this.assertTrue("test.test_title failed: the date should be Thu, 15 Oct 2015 05:52:15 -0400", !date.equals(""));
			valid += this.assertTrue("test.test_title failed: the image should be http://localhost.com/image1.png", !image.equals(""));

			valid += this.assertTrue("test.test_title failed: the title should be TITLE 1", !title.equals("TITLE 2"));
			valid += this.assertTrue("test.test_title failed: the desc should be Description 1", !desc.equals("Description 2"));
			valid += this.assertTrue("test.test_title failed: the link should be http://localhost.com/link1", !link.equals("http://localhost.com/link2"));
			valid += this.assertTrue("test.test_title failed: the guid should be http://localhost.com/guid1", !guid.equals("http://localhost.com/guid2"));
			valid += this.assertTrue("test.test_title failed: the source should be Source 1", !source.equals("Source 2"));
			valid += this.assertTrue("test.test_title failed: the category should be Category 1", !category.equals("Category 2"));
			valid += this.assertTrue("test.test_title failed: the date should be Thu, 15 Oct 2015 05:52:15 -0400", !date.equals("Thu, 05 Oct 2005 55:52:15 -0400"));
			valid += this.assertTrue("test.test_title failed: the image should be http://localhost.com/image1.png", !image.equals("http://localhost.com/image2.png"));

			// good test
			valid += this.assertTrue("test.test_title failed: the title should be TITLE 1", title.equals("TITLE 1"));
			valid += this.assertTrue("test.test_title failed: the desc should be Description 1", desc.equals("Description 1"));
			valid += this.assertTrue("test.test_title failed: the link should be http://localhost.com/link1", link.equals("http://localhost.com/link1"));
			valid += this.assertTrue("test.test_title failed: the guid should be http://localhost.com/guid1", guid.equals("http://localhost.com/guid1"));
			valid += this.assertTrue("test.test_title failed: the source should be Source 1", source.equals("Source 1"));
			valid += this.assertTrue("test.test_title failed: the category should be Category 1", category.equals("Category 1"));
			valid += this.assertTrue("test.test_title failed: the date should be Thu, 15 Oct 2015 05:52:15 -0400", date.equals("Thu, 15 Oct 2015 05:52:15 -0400"));
			valid += this.assertTrue("test.test_title failed: the image should be http://localhost.com/image1.png", image.equals("http://localhost.com/image1.png"));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return valid;
	}

	private int test_item_2() {

		int valid = 0;

		try {
			com.corelib.XMLParser xmlParser = new com.corelib.XMLParser();
			String data = this.XML_TEST;

			// Parse feed
			Document doc = xmlParser.getDomElement(data);
			String parent = "item"; //parent

			NodeList nl = doc.getElementsByTagName(parent);
			int len = nl.getLength();

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

			// bad test
			valid += this.assertTrue("test.test_title failed: the title should be TITLE 2", !title.equals(""));
			valid += this.assertTrue("test.test_title failed: the desc should be Description 2", !desc.equals(""));
			valid += this.assertTrue("test.test_title failed: the link should be http://localhost.com/link2", !link.equals(""));
			valid += this.assertTrue("test.test_title failed: the guid should be http://localhost.com/guid2", !guid.equals(""));
			valid += this.assertTrue("test.test_title failed: the source should be Source 2", !source.equals(""));
			valid += this.assertTrue("test.test_title failed: the category should be Category 2", !category.equals(""));
			valid += this.assertTrue("test.test_title failed: the date should be Thu, 15 Oct 2015 15:52:15 -0400", !date.equals(""));
			valid += this.assertTrue("test.test_title failed: the image should be http://localhost.com/image2.png", !image.equals("image"));

			valid += this.assertTrue("test.test_title failed: the title should be TITLE 2", !title.equals("TITLE 1"));
			valid += this.assertTrue("test.test_title failed: the desc should be Description 2", !desc.equals("Description 1"));
			valid += this.assertTrue("test.test_title failed: the link should be http://localhost.com/link2", !link.equals("http://localhost.com/link1"));
			valid += this.assertTrue("test.test_title failed: the guid should be http://localhost.com/guid2", !guid.equals("http://localhost.com/guid1"));
			valid += this.assertTrue("test.test_title failed: the source should be Source 2", !source.equals("Source 1"));
			valid += this.assertTrue("test.test_title failed: the category should be Category 2", !category.equals("Category 1"));
			valid += this.assertTrue("test.test_title failed: the date should be Thu, 15 Oct 2015 15:52:15 -0400", !date.equals("Thu, 15 Oct 2015 15:52:15 -0100"));
			valid += this.assertTrue("test.test_title failed: the image should be http://localhost.com/image2.png", !image.equals("http://localhost.com/image2.png"));

			// good test
			valid += this.assertTrue("test.test_title failed: the title should be TITLE 2", title.equals("TITLE 2"));
			valid += this.assertTrue("test.test_title failed: the desc should be Description 2", desc.equals("Description 2"));
			valid += this.assertTrue("test.test_title failed: the link should be http://localhost.com/link2", link.equals("http://localhost.com/link2"));
			valid += this.assertTrue("test.test_title failed: the guid should be http://localhost.com/guid2", guid.equals("http://localhost.com/guid2"));
			valid += this.assertTrue("test.test_title failed: the source should be Source 2", source.equals("Source 2"));
			valid += this.assertTrue("test.test_title failed: the category should be Category 2", category.equals("Category 2"));
			valid += this.assertTrue("test.test_title failed: the date should be Thu, 15 Oct 2015 15:52:15 -0400", date.equals("Thu, 15 Oct 2015 15:52:15 -0400"));
			valid += this.assertTrue("test.test_title failed: the image should be http://localhost.com/image2.png", image.equals(""));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return valid;
	}

	// Run test	

	public void runTests() {
		int valid = 0;
		XMLParserTest test = new XMLParserTest();

		// test_size_items
		valid += test.test_size_items();

		// test_items
		valid += test.test_item_1();
		valid += test.test_item_2();

		// Show the test results
		test.showResult(valid);
	}

	public static void main(String[] args) {

		System.out.println("Start XMLParserTest Test...\n");

		XMLParserTest test = new XMLParserTest();
		test.runTests();		

	}
}
