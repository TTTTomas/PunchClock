package se.kingharvest.infrastructure.diagnostics;

import android.util.Log;

public class Logger {

	public static void write(String tag, String message) {
		Log.i(tag, message);
	}

	public static void warning(String tag, String message) {
		Log.w(tag, message);
	}

	public static void debug(String tag, String message) {
		Log.d(tag, message);
	}
}
