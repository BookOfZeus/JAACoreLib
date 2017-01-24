package com.corelib;

/**
 * Timer.java
 *
 * Timer class.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
class Timer
{
	public final static int MINUTES_IN_HOUR = 60;
	public final static int SECONDS_IN_MINUTE = 60;

	/**
	 * Default separator
	 */
	private final static String SEPARATOR = ":";

	/**
	 * Convert seconds to hours:minutes:seconds
	 *
	 * @param sec The number of seconds
	 * @return Formatted time (hh:mm:ss)
	 */
	public static String convertSecondsToMinutes(long sec)
	{
		long seconds = sec % Timer.SECONDS_IN_MINUTE;
		long minutes = (sec / Timer.SECONDS_IN_MINUTE) % Timer.MINUTES_IN_HOUR;

		return String.format("%02d", minutes) +
			Timer.SEPARATOR + 
			String.format("%02d", seconds);
	}

}
