import com.corelib.*;
import java.io.*;

public class HashTest extends CoreLibTest {

	// Start test
	private int test_md5() {
		int valid = 0;
		double score = 0;
		String p;

		//test = 098F6BCD4621D373CADE4E832627B4F6
		try {
			p = Hash.md5("test");
			valid += this.assertTrue("test_run failed: test should be 098F6BCD4621D373CADE4E832627B4F6", p.equalsIgnoreCase("098F6BCD4621D373CADE4E832627B4F6"));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// hello = 5D41402ABC4B2A76B9719D911017C592
		try {
			p = Hash.md5("hello");
			valid += this.assertTrue("test_run failed: test should be 5D41402ABC4B2A76B9719D911017C592", p.equalsIgnoreCase("5D41402ABC4B2A76B9719D911017C592"));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// ithinkitsworking = D2403E6D2D912F61F9F6CAC28B987CE9
		try {
			p = Hash.md5("ithinkitsworking");
			valid += this.assertTrue("test_run failed: test should be D2403E6D2D912F61F9F6CAC28B987CE9", p.equalsIgnoreCase("D2403E6D2D912F61F9F6CAC28B987CE9"));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return valid;
	}


	// Run test	

	public void runTests() {
		int valid = 0;
		HashTest test = new HashTest();

		// len
		valid += test.test_md5();

		// Show the test results
		test.showResult(valid);
	}

	public static void main(String[] args) {

		System.out.println("Start HashTest Test...\n");

		HashTest test = new HashTest();
		test.runTests();		

	}
}
