import com.corelib.*;
import java.io.*;

public class Base64Test extends CoreLibTest {

	// Start test

	private int test_run1() {
		int valid = 0;

		String text;
		String encoded;
		String decoded;

		try {
			text = "hello";
			encoded = Base64.encode(text.getBytes("UTF-8"));
			decoded = new String(Base64.decode(encoded));
			valid += this.assertTrue("test_run1 failed: the base64 text '" + text + "' should be: " + decoded, text.equals(decoded));

			text = "hello world!";
			encoded = Base64.encode(text.getBytes("UTF-8"));
			decoded = new String(Base64.decode(encoded));
			valid += this.assertTrue("test_run1 failed: the base64 text '" + text + "' should be: " + decoded, text.equals(decoded));

			text = "my super long string!";
			encoded = Base64.encode(text.getBytes("UTF-8"));
			decoded = new String(Base64.decode(encoded));
			valid += this.assertTrue("test_run1 failed: the base64 text '" + text + "' should be: " + decoded, text.equals(decoded));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return valid;
	}

	// Run test	

	public void runTests() {
		int valid = 0;
		Base64Test test = new Base64Test();

		// test_fileExists
		valid += test.test_run1();

		// Show the test results
		test.showResult(valid);
	}

	public static void main(String[] args) {

		System.out.println("Start Base64Test Test...\n");

		Base64Test test = new Base64Test();
		test.runTests();		

	}
}
