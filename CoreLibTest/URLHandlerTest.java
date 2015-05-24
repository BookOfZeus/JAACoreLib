import com.corelib.*;
import java.util.Calendar;
import java.io.*;

public class URLHandlerTest extends CoreLibTest {

	private static String DOMAIN = "http://bookofzeus.com/";

	private String url1 = this.DOMAIN + "articles/create-an-android-virtual-device";
	private String url2 = this.DOMAIN + "test.php";
	private String url3 = this.DOMAIN + "test.php?awesome=1";
	private String url4 = this.DOMAIN + "test/";
	private String url5 = this.DOMAIN + "test/anotherTest";
	private String url6 = this.DOMAIN + "articles/create-an-android-virtual-device/";

	// Start Test

	private int test_getPath() {

		int valid = 0;

		// getPath()
		try {
			URLHandler u1 = new URLHandler(url1);
			String test1 = "/articles/create-an-android-virtual-device";
			String path1 = u1.getPath();
			valid += this.assertTrue("test_getPath failed: path should be: " + test1, path1.equals(test1));
		}
		catch(Exception e) {
			valid += this.assertTrue("test_getPath failed: EXCEPTION: '" +  e.getMessage() + "'", false);
		}

		// getPath()
		try {
			URLHandler u1 = new URLHandler(url6);
			String test1 = "/articles/create-an-android-virtual-device/";
			String path1 = u1.getPath();
			valid += this.assertTrue("test_getPath failed: path should be: " + test1, path1.equals(test1));
		}
		catch(Exception e) {
			valid += this.assertTrue("test_getPath failed: EXCEPTION: '" +  e.getMessage() + "'", false);
		}

		return valid;
	}

	private int test_getNameFromUrl() {

		int valid = 0;

		try {
			String name1 = "Test";
			String name2 = "Anothertest";

			// "test.php"
			String urlName1 = URLHandler.getNameFromUrl(url2);
			valid += this.assertTrue("test_getPath failed: path should be: " + name1, urlName1.equals(name1));

			// "test.php?awesome=1"
			String urlName2 = URLHandler.getNameFromUrl(url3);
			valid += this.assertTrue("test_getPath failed: path should be: " + name1, urlName2.equals(name1));

			// "test/"
			String urlName3 = URLHandler.getNameFromUrl(url4);
			valid += this.assertTrue("test_getPath failed: path should be: " + name1, urlName3.equals(name1));

			// "test/anotherTest"
			String urlName4 = URLHandler.getNameFromUrl(url5);
			valid += this.assertTrue("test_getPath failed: path should be: " + name1, urlName4.equals(name2));
		}
		catch(Exception e) {
			valid += this.assertTrue("test_getPath failed: EXCEPTION: '" +  e.getMessage() + "'", false);
		}

		return valid;
	}

	// Run test	

	public void runTests() {
		int valid = 0;
		URLHandlerTest test = new URLHandlerTest();

		// test_getPath
		valid += test.test_getPath();

		// test_NameFromUrl
		valid += test.test_getNameFromUrl();

		// Show the test results
		test.showResult(valid);
	}

	public static void main(String[] args) {

		System.out.println("Start URLHandlerTest Test...\n");

		URLHandlerTest test = new URLHandlerTest();
		test.runTests();		
/*

		try {

			URLHandler u1 = new URLHandler(url1);
			CoreLibTest.show("test_utilWithExt", url1, u1.getPath());
			CoreLibTest.show("test_utilWithExt", url2, URLHandler.getNameFromUrl(url2));
			CoreLibTest.show("test_utilWithExt", url3, URLHandler.getNameFromUrl(url3));
			CoreLibTest.show("test_utilWithExt", url4, URLHandler.getNameFromUrl(url4));
			URLHandler u5 = new URLHandler(url5);
			CoreLibTest.show("test_utilWithExt", url5, u5.getPath());
		}
		catch(Exception e) {
			CoreLibTest.show("EXCEPTION: " + e.getMessage());
		}
*/
	}
}
