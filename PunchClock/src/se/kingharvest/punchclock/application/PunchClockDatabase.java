package se.kingharvest.punchclock.application;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class PunchClockDatabase {

	SQLiteOpenHelper _database;
	
	public PunchClockDatabase()
	{
		//DalFactory.registerDal(WorkPeriodDal.class);

	}

	public SQLiteDatabase getSQLiteDatabase() {
		// FIXME: Mjeh.
		return _database.getWritableDatabase();
	}
}
