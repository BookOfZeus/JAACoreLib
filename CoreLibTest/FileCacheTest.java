import com.corelib.*;
import java.util.Calendar;
import java.io.*;

public class FileCacheTest extends CoreLibTest {

	private static String FILENAME_TEST = "/tmp/test_random_file.txt";
	private static String BAD_FOLDER		= "/bad_folder";
	private static String FILE_CONTENT	= "Java UnitTest Content";

	// Start test

	private int test_fileExists() {

		int valid = 0;
		String file = "/tmp/file1.txt";
		FileCache fc = new FileCache(file);

		// File does not exists
		valid += this.assertTrue("test_fileExists failed: the file '" + file + "' does not exists", !fc.fileExists());
		valid += this.assertFalse("test_fileExists failed: the file '" + file + "' does not exists", fc.fileExists());

		return valid;
	}

	private int test_fileCreate() {

		int valid = 0;
		String file = this.FILENAME_TEST;

		// Create the file in a valid writable folder
		FileCache fc = new FileCache(file);
		try {
			valid += this.assertTrue("test_fileCreate failed: the file '" + file + "' could not be created", fc.createFile());
			fc.remove();
		}
		catch(IOException e) {
			valid += this.assertTrue("test_fileCreate failed: EXCEPTION: '" +  e.getMessage() + "'", false);
		}

		// Try to create the file in a folder that does not exists
		fc = new FileCache(this.BAD_FOLDER + file);
		try {
			valid += this.assertTrue("test_fileCreate failed: the file '" + this.BAD_FOLDER + file + "' could not be created", fc.createFile());
		}
		catch(IOException e) {
			valid += this.assertTrue("test_fileCreate failed: EXCEPTION: '" +  e.getMessage() + "'", true);
		}

		return valid;
	}

	private int test_fileRemove() {

		int valid = 0;
		String file = this.FILENAME_TEST;

		// Delete valid file
		FileCache fc = new FileCache(file);
		valid += this.assertTrue("test_fileRemove failed: the file '" + file + "' exists", fc.remove());

		// Delete a file that does not exists
		fc = new FileCache(this.BAD_FOLDER + file);
		valid += this.assertFalse("test_fileRemove failed: the file '" + file + "' does not exists", !fc.remove());

		return valid;
	}

	private int test_fileSave() {

		int valid = 0;
		String file = this.FILENAME_TEST;

		FileCache fc = new FileCache(file);
		try {
			valid += this.assertTrue("test_fileSave failed: the file '" + file + "' was not saved", fc.save(this.FILE_CONTENT));
			fc.remove();
		}
		catch(Exception e) {
			valid += this.assertTrue("test_fileSave failed: EXCEPTION: '" +  e.getMessage() + "'", false);
		}

		return valid;
	}

	private int test_fileRead() {

		int valid = 0;
		String file = this.FILENAME_TEST;
		int lenStrOrigin = this.FILE_CONTENT.length();

		FileCache fc = new FileCache(file);
		try {
			fc.save(this.FILE_CONTENT);
			String msg = fc.read();
			int len = msg.length();
			valid += this.assertTrue("test_fileRead failed: Error while reading file: " + file, len == lenStrOrigin);
			fc.remove();
		}
		catch(IOException e) {
			valid += this.assertTrue("test_fileRead failed: EXCEPTION: '" +  e.getMessage() + "'", false);
		}
		catch(Exception e) {
			valid += this.assertTrue("test_fileSave failed: EXCEPTION: '" +  e.getMessage() + "'", false);
		}

		return valid;
	}

	private int test_fileIsOld() {

		int valid = 0;

		/* Here you will have to specify your own files */
		String file = "/home/eric/Git/JAACoreLib/README.md";

		// Older than 1 day
		FileCache fc = new FileCache(file);

		valid += this.assertFalse("test_fileIsOld failed: '" + file + "' is older than 12 hours", fc.isOld(Calendar.HOUR, 12));
		
		valid += this.assertTrue("test_fileIsOld failed: '" + file + "' is older than 1 minute", fc.isOld(Calendar.MINUTE, 1));
		
		valid += this.assertFalse("test_fileIsOld failed: '" + file + "' is not older than 1 year", fc.isOld(Calendar.YEAR, 1));

		return valid;
	}

	// Run test	

	public void runTests() {
		int valid = 0;
		FileCacheTest test = new FileCacheTest();

		// test_fileExists
		valid += test.test_fileExists();

		// test_fileCreate
		valid += test.test_fileCreate();

		// test_fileRemove
		valid += test.test_fileRemove();

		// test_fileSave
		valid += test.test_fileSave();

		// test_fileRead 
		valid += test.test_fileRead();

		// test_fileIsOld
		valid += test.test_fileIsOld();

		// Show the test results
		test.showResult(valid);
	}

	public static void main(String[] args) {

		System.out.println("Start FileCacheTest Test...\n");

		FileCacheTest test = new FileCacheTest();
		test.runTests();		

	}
}
