package se.kingharvest.infrastructure.system;

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
}
