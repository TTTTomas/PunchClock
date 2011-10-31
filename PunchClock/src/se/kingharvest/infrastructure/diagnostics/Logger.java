package se.kingharvest.infrastructure.diagnostics;

import android.util.Log;

public class Logger {

	public static void write(String tag, String message) {
		Log.i(tag, message);
	}

}
