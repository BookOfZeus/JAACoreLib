import com.corelib.*;

public class TimerTest extends CoreLibTest {

	// Start test
	private int test_convertSecondsToMinutes() {
		int valid = 0;
		String p;

		p = Timer.convertSecondsToMinutes(1);
		valid += this.assertTrue("test_run failed: should be 00:01: " + p, p.equals("00:01"));

		p = Timer.convertSecondsToMinutes(900);
		valid += this.assertTrue("test_run failed: should be 00:01: " + p, p.equals("15:00"));

		p = Timer.convertSecondsToMinutes(467);
		valid += this.assertTrue("test_run failed: should be 00:01: " + p, p.equals("07:47"));

		return valid;
	}

	//
	// Run test	
	//

	public static void main(String[] args) {
		int valid = 0;

		System.out.println("Start TimerTest Test...\n");

		TimerTest test = new TimerTest();

		// convertSecondsToMinutes
		valid += test.test_convertSecondsToMinutes();


		// Show the test results
		test.showResult(valid);

	}
}
