package se.kingharvest.punchclock.application;

import se.kingharvest.infrastructure.data.TableFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class PunchClockDatabase {

	SQLiteOpenHelper _database;
	
	public PunchClockDatabase()
	{
		TableFactory.getInstance().setDatabase(getSQLiteDatabase());
		//DalFactory.registerDal(WorkPeriodDal.class);
		ProjectTable.getTable().dropTable();
		ProjectTable.getTable().createTable();

	}

	public SQLiteDatabase getSQLiteDatabase() {
		// FIXME: Mjeh.
		return _database.getWritableDatabase();
	}
}
