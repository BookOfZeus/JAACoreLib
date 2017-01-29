package com.corelib;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Calendar;
import java.util.Date;

/**
 * LocalFile.java
 *
 * File manipulation.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
class FileCache
{
	/**
	 * The class tag name
	 */
	final static private String TAG = "FileCache";
	/**
	 * The class tag name
	 */
	final static private String ERROR_CREATE_FILE = "Cannot create file";

	/**
	 * The image cache path
	 */
	final static private String IMAGE_CACHE_PATH = "image_cache";

	private String filename;
	private File file;
	private File cacheDir;

	/**
	 * Constructor
	 *
	 * @param file The filename
	 */
	public FileCache(String file)
	{
		this.filename = file;
		this.file = new File(filename);
	}

	/**
	 * Constructor
	 *
	 * @param path The full path
	 * @param file The filename
	 */
	public FileCache(String path, String file)
	{
		this(path + File.separator + file);
		this.filename = path + File.separator + file;
	}

	/**
	 * Constructor
	 *
  	 * <pre>
	 * {@code
	 *  if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
	 *  	cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), FileCache.IMAGE_CACHE_PATH);
	 *  }
	 * }
	 * </pre>
	 *
	 * @param path The external storage mounted path
	 * @throws IOException Write Error
	 */
	public FileCache(File path) throws IOException
	{
		this.cacheDir = path;

		if(!this.cacheDir.exists()) {
			if (!this.cacheDir.mkdirs()) {
				throw new IOException("Unable to create directory");
			}
		}
	}

	/**
	 * Get the cache path
	 *
	 * @return String
	 */
	public String getCacheDir()
	{
		return this.cacheDir.getAbsolutePath();
	}

	/**
	 * Save a file
	 *
	 * @param content The content of the file
	 * @throws java.io.IOException File not found
	 */
	public void save(String content) throws IOException
	{
		if (!this.fileExists()) {
			if(!this.createFile()) {
				throw new IOException(FileCache.TAG + ":" + FileCache.ERROR_CREATE_FILE);
			}
		}
		BufferedWriter bw = new BufferedWriter(
			new FileWriter(this.filename)
		);
		bw.write(content);
		bw.close();
	}

	/**
	 * Read a file
	 *
	 * @return The content of the file (can be empty if the file does not exists)
	 * @throws java.io.IOException File Error
	 */
	public String read() throws IOException
	{
		if(!this.fileExists()) {
			throw new FileNotFoundException("File Not Found");
		}
		String read;
		StringBuilder builder = new StringBuilder("");

		BufferedReader br = new BufferedReader(
			new FileReader(this.filename)
		);
		while((read = br.readLine()) != null) {
			builder.append(read);
		}
		br.close();
		return builder.toString();
	}

	/**
	 * Check if a file exists
	 *
	 * @return boolean
	 */
	public boolean fileExists()
	{
		return this.file.exists() && this.file.canRead();
	}

	/**
	 * Create new file
	 *
	 * @return boolean
	 * @throws java.io.IOException File error
	 */
	public boolean createFile() throws IOException {
		return !this.file.exists() && this.file.createNewFile();
	}

	/**
	 * Check if the file is old
	 *
	 * @param type The type
	 * @param length The length
	 * @return boolean
	 */
	public boolean isOld(int type, int length)
	{
		if(!this.fileExists()) {
			return true;
		}
		length = Math.abs(length) * -1;
		Calendar time = Calendar.getInstance();
		time.add(type, length);
		Date lastModified = new Date(this.file.lastModified());
		return lastModified.before(time.getTime());
	}

	/**
	 * Remove a file
	 *
	 * @return boolean
	 */
	public boolean remove() {
		return !this.fileExists() || this.file.canWrite() && this.file.delete();
	}

	/**
	 * Clear the cache folder
	 *
	 * @throws IOException Write Error
	 */
	public void clear() throws IOException
	{
		File[] files = cacheDir.listFiles();
		if(files == null) {
			return;
		}
		for(File f:files) {
			if (!f.delete()) {
				throw new IOException("Unable to delete file");
			}
		}
	}

	/**
	 * Copy an assets folder to the local files folders
	 *
	 * @param folder The asset folder
	 * @param c The context
	 * @return boolean
	 * @throws IOException File Error
	 */
	public static boolean copyAssets(String folder, Context c) throws IOException
	{
		boolean ret = false;

		AssetManager assetManager = c.getAssets();
		String[] files;

		files = assetManager.list(folder);
		for(String f: files) {

			InputStream in;
			OutputStream out;
			in = assetManager.open(folder + File.separator + f);
			File outFile = new File(c.getFilesDir().toString() + File.separator, f);
			out = new FileOutputStream(outFile);

			// Copy
			byte[] buffer = new byte[1024];
			int read;
			while((read = in.read(buffer)) != -1){
				out.write(buffer, 0, read);
			}
			in.close();
			out.close();
			ret = true;
		}
		return ret;
	}

	/**
	 * Copy a source file to a destination file
	 *
	 * @param src The source file
	 * @param dst The destination file
	 * @throws IOException File Error
	 */
	public static void copy(String src, String dst) throws IOException
	{
		InputStream in = new FileInputStream(new File(src));
		OutputStream out = new FileOutputStream(new File(dst));

		// Transfer bytes from in to out
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}
}