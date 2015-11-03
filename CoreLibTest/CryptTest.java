import com.corelib.*;
import java.io.*;

public class CryptTest extends CoreLibTest {

	// Start test

	private int test_run1() {
		int valid = 0;

		String str;
		String enc;
		String dec;
		String key = "k94q8h494q8h41ekk94q8h494q8h41ek"; // 32 bites

		try {
			Crypt c = new Crypt(key);

			// 
			str = "test1";
			enc = c.encrypt(str);
			dec = c.decrypt(enc);

			valid += this.assertTrue("test_run1 failed: the string '" + str + "' should be: " + dec, str.equals(dec));

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}		

		return valid;
	}

	private int test_run2() {
		int valid = 0;

		String str;
		String enc;
		String dec;
		String key = ""; // 0 bites, should use default

		try {
			Crypt c = new Crypt(key);

			// 
			str = "test1";
			enc = c.encrypt(str);
			dec = c.decrypt(enc);

			valid += this.assertTrue("test_run2 failed: the string '" + str + "' should be: " + dec, str.equals(dec));

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}		

		return valid;
	}

	// Run test	

	public void runTests() {
		int valid = 0;
		CryptTest test = new CryptTest();

		// test_fileExists
		valid += test.test_run1();

		// test_fileExists
		valid += test.test_run2();

		// Show the test results
		test.showResult(valid);
	}

	public static void main(String[] args) {

		System.out.println("Start CryptTest Test...\n");

		CryptTest test = new CryptTest();
		test.runTests();		

	}
}
