package se.kingharvest.punchclock.data;

import se.kingharvest.infrastructure.data.Database;
import se.kingharvest.infrastructure.data.Table;
import se.kingharvest.infrastructure.data.TableFactory;
import se.kingharvest.punchclock.entity.Project;

public class ProjectTable extends Table<Project>{

	public ProjectTable(Database database) {
		super("Project", database);
	}

	public static ProjectTable getTable(){
		
		return TableFactory.getTable(ProjectTable.class);
	}

}
