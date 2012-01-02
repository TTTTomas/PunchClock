package se.kingharvest.punchclock.data;

import se.kingharvest.infrastructure.data.Database;
import se.kingharvest.punchclock.data.tables.ProjectTable;
import se.kingharvest.punchclock.data.tables.WorkPeriodTable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class PunchClockDatabase {

	Database _database;
	
	public static final String PUNCHCLOCK_DATABASE_NAME = "PunchClockDatabase.sqlite";
	public static final int PUNCHCLOCK_DATABASE_VERSION_CURRENT = 1;
	
	public PunchClockDatabase(Context context)
	{
		_database = new Database(context, PUNCHCLOCK_DATABASE_NAME, PUNCHCLOCK_DATABASE_VERSION_CURRENT);

		_database.addTable(ProjectTable.getTable());
		_database.addTable(WorkPeriodTable.getTable());
		
		_database.createTablesIfNeeded();
	}

	public SQLiteDatabase getSQLiteDatabase() {
		// FIXME: Mjeh.
		return _database.getWritableDatabase();
	}
}
