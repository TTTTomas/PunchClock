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
}
