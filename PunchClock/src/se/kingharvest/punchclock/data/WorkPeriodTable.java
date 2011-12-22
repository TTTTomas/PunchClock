package se.kingharvest.punchclock.data;

import se.kingharvest.infrastructure.data.Table;
import se.kingharvest.infrastructure.data.TableFactory;
import se.kingharvest.punchclock.entity.WorkPeriod;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class WorkPeriodTable extends Table<WorkPeriod>{

	public WorkPeriodTable(SQLiteDatabase database) {
		super("WorkPeriod", database);
	}

	public static WorkPeriodTable getTable(){
		
		return TableFactory.getTable(WorkPeriodTable.class);
	}

	public WorkPeriod selectPeriodInProgress() {
		
		Cursor cursor = _database.query(TableName, _columns.getColumnNames(), "InProgress=?", new String[]{"1"}, null, null, null);
		return returnEntityFromCursor(cursor);
	}
}
