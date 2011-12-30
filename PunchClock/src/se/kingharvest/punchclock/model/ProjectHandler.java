package se.kingharvest.punchclock.model;

import se.kingharvest.punchclock.data.ProjectTable;
import se.kingharvest.punchclock.entity.Project;

public class ProjectHandler {

	public static Project createProject(Project newProject) {
		
		Project project = ProjectTable.getTable().insert(newProject);
		return project;
	}

}
