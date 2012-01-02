package se.kingharvest.punchclock.data.handlers;

import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.punchclock.data.tables.ProjectTable;
import se.kingharvest.punchclock.entity.Project;

public class ProjectHandler {

	public static Project createProject(Project newProject) {
		
		Project project = ProjectTable.getTable().insert(newProject);
		return project;
	}

	public static Project getProject(Id projectId) {
		Project project = ProjectTable.getTable().select(projectId.get());
		return project;
	}

}
