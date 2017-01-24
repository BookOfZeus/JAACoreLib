package com.corelib;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit test for URLHandler.
 */
public class URLHandlerTest
{
	/**
	 * Setup default test strings
	 */
	private static String DOMAIN = "http://bookofzeus.com/";
	private static String url1 = URLHandlerTest.DOMAIN + "articles/create-an-android-virtual-device";
	private static String url2 = URLHandlerTest.DOMAIN + "test.php";
	private static String url3 = URLHandlerTest.DOMAIN + "test.php?awesome=1";
	private static String url4 = URLHandlerTest.DOMAIN + "test/";
	private static String url5 = URLHandlerTest.DOMAIN + "test/another_test";
	private static String url6 = URLHandlerTest.DOMAIN + "articles/create-an-android-virtual-device/";

	@Test
	public void testURLHandlerGetPath() throws Exception
	{
		URLHandler u1 = new URLHandler(URLHandlerTest.url1);
		String test1 = "/articles/create-an-android-virtual-device";
		String path1 = u1.getPath();
		assertTrue(path1.equals(test1));

		u1 = new URLHandler(url6);
		test1 = "/articles/create-an-android-virtual-device/";
		path1 = u1.getPath();
		assertTrue(path1.equals(test1));
	}

	@Test
	public void testURLHandlerGetNameFromUrl() throws Exception
	{
		String name1 = "Test";
		String name2 = "Another_test";

		// "test.php"
		String urlName1 = URLHandler.getNameFromUrl(url2);
		assertTrue(urlName1.equals(name1));

		// "test.php?awesome=1"
		String urlName2 = URLHandler.getNameFromUrl(url3);
		assertTrue(urlName2.equals(name1));

		// "test/"
		String urlName3 = URLHandler.getNameFromUrl(url4);
		assertTrue(urlName3.equals(name1));

		// "test/anotherTest"
		String urlName4 = URLHandler.getNameFromUrl(url5);
		assertTrue(urlName4.equals(name2));

	}
}