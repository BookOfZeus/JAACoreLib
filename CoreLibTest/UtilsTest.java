import com.corelib.*;
import java.util.Calendar;
import java.io.*;

public class UtilsTest extends CoreLibTest {

	private static String FILENAME_TEST = "/tmp/test_random_file.txt";
	private static String BAD_FOLDER		= "/bad_folder";
	private static String FILE_CONTENT	= "Java UnitTest Content";

	// Start test

	private int test_capitalize() {

		int valid = 0;
		String result = "Awesome";

		String str1 = "awesome";
		valid += this.assertTrue("test_capitalize failed: the string shoul be '" + result + "'", result.equals(Utils.capitalize(str1)));

		String str2 = "Awesome";
		valid += this.assertTrue("test_capitalize failed: the string shoul be '" + result + "'", result.equals(Utils.capitalize(str2)));

		return valid;
	}

	private int test_padRight() {

		int valid = 0;
		String result7 = "Awesome";
		String result10 = "Awesome   ";
		String result15 = "Awesome        ";
		String str = "Awesome";

		valid += this.assertTrue("test_capitalize failed: the string shoul be '" + result7 + "'", result7.equals(Utils.padRight(str, 7)));
		valid += this.assertTrue("test_capitalize failed: the string shoul be '" + result7 + "'", result10.equals(Utils.padRight(str, 10)));
		valid += this.assertTrue("test_capitalize failed: the string shoul be '" + result7 + "'", result15.equals(Utils.padRight(str, 15)));

		valid += this.assertFalse("test_capitalize failed: the string shoul be '" + result7 + "'", result15.equals(Utils.padRight(str, 7)));

		return valid;
	}

	private int test_padLeft() {

		int valid = 0;
		String result7 = "Awesome";
		String result10 = "   Awesome";
		String result15 = "        Awesome";
		String str = "Awesome";

		valid += this.assertTrue("test_capitalize failed: the string shoul be '" + result7 + "'", result7.equals(Utils.padLeft(str, 7)));
		valid += this.assertTrue("test_capitalize failed: the string shoul be '" + result7 + "'", result10.equals(Utils.padLeft(str, 10)));
		valid += this.assertTrue("test_capitalize failed: the string shoul be '" + result7 + "'", result15.equals(Utils.padLeft(str, 15)));

		valid += this.assertFalse("test_capitalize failed: the string shoul be '" + result7 + "'", result15.equals(Utils.padLeft(str, 7)));

		return valid;
	}

	// Run test	

	public void runTests() {
		int valid = 0;
		UtilsTest test = new UtilsTest();

		// test_capitalize
		valid += test.test_capitalize();

		// test_padRight
		valid += test.test_padRight();

		// test_padLeft
		valid += test.test_padLeft();

		// Show the test results
		test.showResult(valid);

	}

	public static void main(String[] args) {

		System.out.println("Start UtilsTest Test...\n");

		UtilsTest test = new UtilsTest();
		test.runTests();		

	}
}
