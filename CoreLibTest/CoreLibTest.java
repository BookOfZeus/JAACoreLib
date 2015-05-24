public class CoreLibTest {

	private int testsRan = 0;

	public static void main(String[] args) {
		System.out.println("Start CoreLibTest Test...");
	}

	public int assertTrue(String msg, boolean result) {
		this.testsRan++;
		if(!result) {
			System.out.println("   " + msg);
			return 0;
		}
		return 1;
	}

	public int assertFalse(String msg, boolean result) {
		return this.assertTrue(msg, !result);
	}

	public void showResult(int valid) {
		int invalid = this.testsRan - valid;

		System.out.println("\n Tests Ran: " + this.testsRan);

		if(invalid > 0) {
			System.out.println(" Valid Tests: " + valid);
			System.out.println(" Invalid Tests: " + invalid);
		}
		else {
			System.out.println("\n*** ALL TESTS PASSED ***\n");
		}
	}
}
