package se.kingharvest.infrastructure.time;

public class TimeSpan {
	
	public final double TotalMilliSeconds;
	public final double TotalSeconds;
	public final double TotalMinutes;
	public final double TotalHours;
	public final double TotalDays;

	public final int MilliSeconds;
	public final int Seconds;
	public final int Minutes;
	public final int Hours;
	public final int Days;

	public TimeSpan(int hours, int minutes, int seconds)
	{
		this(DateTime.MILLISECONDS_PER_HOUR * hours
			+ DateTime.MILLISECONDS_PER_MINUTE * minutes
			+ 1000 * seconds);
	}

	
	public TimeSpan(long milliSeconds)
	{
		TotalMilliSeconds = milliSeconds;
		TotalSeconds 	= ((double)TotalMilliSeconds)/1000.0;
		TotalMinutes 	= ((double)TotalMilliSeconds)/((double)DateTime.MILLISECONDS_PER_MINUTE);
		TotalHours 		= ((double)TotalMilliSeconds)/((double)DateTime.MILLISECONDS_PER_HOUR);
		TotalDays 		= ((double)TotalMilliSeconds)/((double)DateTime.MILLISECONDS_PER_DAY);
		
		MilliSeconds 	= (int) (milliSeconds / 1000);
		Seconds 		= (int) (milliSeconds / 1000) % 60;
		Minutes 		= (int) ((milliSeconds / (1000*60)) % 60);
		Hours   		= (int) ((milliSeconds / (1000*60*60)) % 24);
		Days   			= (int) ((milliSeconds / (1000*60*60*24)));
	}
	
	@Override
	public String toString() {
		return Days + " days, " + String.format("%02d", Hours) + ":" + String.format("%02d", Minutes) + "." + String.format("%02d", Seconds);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof TimeSpan))
			return false;
		
		return TotalMilliSeconds == ((TimeSpan)o).TotalMilliSeconds;
	}
}
