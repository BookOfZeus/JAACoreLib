package com.corelib;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;

/**
 * Unit test for FileCache.
 */
public class FileCacheTest {

	/**
	 * Sample test files/content
	 */
	private static final String FOLDER_TEST = "/tmp/";
	private static final String FILENAME_TEST = "test_random_file.txt";
	private static final String BAD_FOLDER	= "/bad_folder";
	private static String FILE_CONTENT	= "Java UnitTest Content";

	@Test
	public void testFileCacheFileNotExists()
	{
		FileCache fc = new FileCache(FileCacheTest.FOLDER_TEST + FileCacheTest.FILENAME_TEST);

		// File does not exists
		assertTrue(!fc.fileExists());
		assertFalse(fc.fileExists());
	}

	@Test
	public void testFileCacheFileCreate() throws IOException
	{
		FileCache fc = new FileCache(FileCacheTest.FOLDER_TEST + FileCacheTest.FILENAME_TEST);
		assertTrue(fc.createFile());
		fc.remove();

		//
		try {
			fc = new FileCache(FileCacheTest.BAD_FOLDER + FileCacheTest.FILENAME_TEST);
			fc.createFile();
		}
		catch (IOException ex) {
			// Exception thrown
		}
	}

	@Test
	public void testFileCacheFileRemove() throws IOException
	{
		FileCache fc = new FileCache(FileCacheTest.FOLDER_TEST + FileCacheTest.FILENAME_TEST);
		fc.createFile();
		assertTrue(fc.remove());

		// Delete a file that does not exists
		fc = new FileCache(FileCacheTest.BAD_FOLDER + FileCacheTest.FILENAME_TEST);
		assertFalse(!fc.remove());
	}

	@Test
	public void testFileCacheFileSave() throws Exception
	{
		boolean exceptionThrown = false;
		try {
			FileCache fc = new FileCache(FileCacheTest.FOLDER_TEST + FileCacheTest.FILENAME_TEST);
			fc.save(FileCacheTest.FILE_CONTENT);
			fc.remove();
		}
		catch (IOException ex) {
			exceptionThrown = true;
		}
		assertTrue(!exceptionThrown);
	}

	@Test
	public void testFileCacheFileRead() throws Exception
	{
		int lenStrOrigin = FileCacheTest.FILE_CONTENT.length();

		FileCache fc = new FileCache(FileCacheTest.FOLDER_TEST + FileCacheTest.FILENAME_TEST);

		fc.save(FileCacheTest.FILE_CONTENT);
		String msg = fc.read();
		int len = msg.length();
		assertTrue(len == lenStrOrigin);
		fc.remove();
	}

	@Test
	public void testFileCacheFileIsOld()
	{
		String file = "/etc/hosts";

		// Older than 1 day
		FileCache fc = new FileCache(file);

		assertTrue(fc.isOld(Calendar.HOUR, 12));

		assertTrue(fc.isOld(Calendar.MINUTE, 1));

		assertFalse(fc.isOld(Calendar.YEAR, 1));

	}

	@Test
	public void testFileCacheFileCopy() throws IOException
	{
		String src = "/etc/hosts";
		String dst = "/tmp/hosts";

		boolean exceptionThrown = false;
		try {
			FileCache.copy(src, dst);
		}
		catch (IOException ex) {
			exceptionThrown = true;
		}
		assertTrue(!exceptionThrown);
	}
}