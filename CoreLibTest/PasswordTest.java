import com.corelib.*;
import java.io.*;

public class PasswordTest extends CoreLibTest {

	// Start test

	private int test_gen() {
		int valid = 0;
		double score = 0;
		String pass = "";

		// Lower
		Password pLower = new Password();
		pLower.setUseUpper(false);
		pLower.setUseDigit(false);
		pLower.setUseSpecial(false);

		pass = pLower.generate(4);
		//System.out.println(pass);
		score = Password.getScore(pass);
		valid += this.assertTrue("test_run failed: Lower score should be : "+score, score == 9);

		// Upper
		Password pUpper = new Password();
		pUpper.setUseLower(false);
		pUpper.setUseDigit(false);
		pUpper.setUseSpecial(false);

		pass = pUpper.generate(4);
		//System.out.println(pass);
		score = Password.getScore(pass);
		valid += this.assertTrue("test_run failed: Upper score should be : "+score, score == 9);

		// Digit
		Password pDigit = new Password();
		pDigit.setUseLower(false);
		pDigit.setUseUpper(false);
		pDigit.setUseSpecial(false);

		pass = pDigit.generate(4);
		//System.out.println(pass);
		score = Password.getScore(pass);
		valid += this.assertTrue("test_run failed: Digit score should be : "+score, score == 9);

		// Special
		Password pSpec = new Password();
		pSpec.setUseLower(false);
		pSpec.setUseUpper(false);
		pSpec.setUseSpecial(false);

		pass = pSpec.generate(4);
		//System.out.println(pass);
		score = Password.getScore(pass);
		valid += this.assertTrue("test_run failed: Special score should be : "+score, score == 9);

		// digit + Special
		Password pDS = new Password();
		pDS.setUseLower(false);
		pDS.setUseUpper(false);

		pass = pDS.generate(4);
		//System.out.println(pass);
		score = Password.getScore(pass);
		valid += this.assertTrue("test_run failed: digit + Special 4 score should be : "+score, score == 50);

		// digit + Special
		Password pDS2 = new Password();
		pDS2.setUseLower(false);
		pDS2.setUseUpper(false);

		pass = pDS2.generate(8);
		//System.out.println(pass);
		score = Password.getScore(pass);
		valid += this.assertTrue("test_run failed: digit + Special 8 score should be : "+score, score == 54);

		return valid;
	}
	
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

		// generate
		valid += test.test_gen();

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
