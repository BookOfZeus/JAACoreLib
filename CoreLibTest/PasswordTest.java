import com.corelib.*;
import java.io.*;

public class PasswordTest extends CoreLibTest {

	// Start test

	private int test_run1() {
		int valid = 0;
		double score = 0;

		score = Password.getScore("good");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 9);

		score = Password.getScore("Good");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 34.0);

		score = Password.getScore("G0od");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 69.66666666666667);

		score = Password.getScore("G0@d");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 100);

		return valid;
	}

	private int test_run2() {
		int valid = 0;
		double score = 0;

		score = Password.getScore("hello");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 13);

		score = Password.getScore("Hello");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 32);

		score = Password.getScore("H3llo");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 67);

		score = Password.getScore("H3ll0");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 71);

		score = Password.getScore("H3l!0");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 97);

		return valid;
	}

	private int test_run3() {
		int valid = 0;
		double score = 0;

		score = Password.getScore("password");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 29);

		score = Password.getScore("Password");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 62);

		score = Password.getScore("P@ssword");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 81.66666666666667);

		score = Password.getScore("P@sswoRd");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 80.33333333333333);

		score = Password.getScore("P@ssw0Rd");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 100);

		score = Password.getScore("P@5sw0Rd");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 100);

		score = Password.getScore("P@5sw0R!");
		valid += this.assertTrue("test_run failed: score should be : "+score, score == 100);

		return valid;
	}

	// Run test	

	public void runTests() {
		int valid = 0;
		PasswordTest test = new PasswordTest();

		// test_1
		valid += test.test_run1();

		// test_2
		valid += test.test_run2();

		// test_3
		valid += test.test_run3();

		// Show the test results
		test.showResult(valid);
	}

	public static void main(String[] args) {

		System.out.println("Start PasswordTest Test...\n");

		PasswordTest test = new PasswordTest();
		test.runTests();		

	}
}
