package se.kingharvest.punchclock.data;

import se.kingharvest.infrastructure.data.Database;
import se.kingharvest.infrastructure.data.Table;
import se.kingharvest.infrastructure.data.TableFactory;
import se.kingharvest.punchclock.entity.WorkPeriod;
import android.database.Cursor;

public class WorkPeriodTable extends Table<WorkPeriod>{

	public WorkPeriodTable(Database database) {
		super("WorkPeriod", database);
	}

	public static WorkPeriodTable getTable(){
		
		return TableFactory.getTable(WorkPeriodTable.class);
	}

	public WorkPeriod selectPeriodInProgress() {
		
		Cursor cursor = _database.getWritableDatabase().query(TableName, _columns.getColumnNames(), "InProgress=?", new String[]{"1"}, null, null, null);
		return returnEntityFromCursor(cursor);
	}
}
