package se.kingharvest.infrastructure.system;

import android.text.TextUtils;


public class Strings {

	public static String join(String[] strings, String separator)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			sb.append(strings[i]);
			if (i<strings.length-1)
				sb.append(separator);
		}
		return sb.toString();
	}

	/**
	 * Joins together a given number of copies of a single string.
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String join(String str, int count, String separator)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(str);
			if (i<count-1)
				sb.append(separator);
		}
		return sb.toString();
	}

	public static String[] removeOne(String toRemove, String[] strings) {

		String[] removed = new String[strings.length-1];
		int offset=0;
		for (int j = 0; j < removed.length; j++) {
			if(strings[j+offset].equals(toRemove))
			{
				offset = 1;
			}
			
			// Copy the next item unless we are at the end of the array.
//			if(j+offset < removed.length)
//			{
			removed[j] = strings[j+offset];				
//			}
		}
		return removed;
	}

	/**
	 * Parses a string as a boolean. "TRUE", "true" or any integer value greater than zero are parsed as true.
	 */
	public static boolean toBoolean(String value) {
		if (TextUtils.isDigitsOnly(value))
			return Integer.parseInt(value) >= 1;
		else
			return value.toLowerCase().equals(Boolean.TRUE.toString());
		
		// else, the string value is not representable as a boolean, and false is returned above.
	}
	
	public static String nullToEmpty(Object object)
	{
		if(object == null)
			return "";
		return String.valueOf(object);
	}
}
