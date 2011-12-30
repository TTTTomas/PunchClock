package se.kingharvest.punchclock.data;

import se.kingharvest.infrastructure.data.Table;
import se.kingharvest.infrastructure.data.TableFactory;
import se.kingharvest.punchclock.entity.Project;
import android.database.sqlite.SQLiteDatabase;

public class ProjectTable extends Table<Project>{

	public ProjectTable(SQLiteDatabase database) {
		super("Project", database);
	}

	public static ProjectTable getTable(){
		
		return TableFactory.getTable(ProjectTable.class);
	}

}
