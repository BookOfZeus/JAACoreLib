package com.corelib;

/**
 * Timer.java
 *
 * Timer class.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
@SuppressWarnings("ALL")
public final class Timer
{
	/**
	 * Number of minutes in 1 hour
	 */
	public final static int MINUTES_IN_HOUR = 60;
	/**
	 * Number of seconds in 1 minute
	 */
	public final static int SECONDS_IN_MINUTE = 60;

	/**
	 * Default separator
	 */
	private final static String SEPARATOR = ":";

	/**
	 * Constructor
	 */
	private Timer() throws InstantiationException
	{
		throw new InstantiationException("Create an instance of Timer is forbidden");
	}

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
