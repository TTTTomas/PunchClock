package se.kingharvest.infrastructure.time;

import java.util.Date;

public class DateTime {

	public final static int MILLISECONDS_PER_MINUTE = 1000 * 60;
	public final static long MILLISECONDS_PER_HOUR = MILLISECONDS_PER_MINUTE * 60;
	public final static long MILLISECONDS_PER_DAY = MILLISECONDS_PER_HOUR * 24;

	public static Date add(Date date, long timeSpan){
		
		long milliSeconds = date.getTime() + timeSpan;
		return new Date(milliSeconds);
	}

	/**
	 * Adds a time span to a date.
	 * @param date
	 * @param timeSpan
	 * @return
	 */
	public static Date add(Date date, TimeSpan timeSpan){
		
		long milliSeconds = date.getTime() + timeSpan.TotalMilliSeconds;
		return new Date(milliSeconds);
	}
	
	private final static Date MIN_VALUE = new Date(0, 1, 0);

	public static Date minValue() {
		return MIN_VALUE;
	}
}
