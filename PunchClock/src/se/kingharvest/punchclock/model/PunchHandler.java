package se.kingharvest.punchclock.model;

import java.util.Date;

import se.kingharvest.infrastructure.time.TimeSpan;
import se.kingharvest.punchclock.data.WorkPeriodTable;
import se.kingharvest.punchclock.entity.Project;
import se.kingharvest.punchclock.entity.WorkPeriod;

public class PunchHandler {

	public static void startProject(Project project) 
	{
		WorkPeriod periodInProgress = getPeriodInProgress();
		if(periodInProgress != null)
		{
			stopPeriod(periodInProgress);
		}
		
		WorkPeriod period = new WorkPeriod();
		period.Start = new Date();
		period.InProgress = true;
		period.ProjectId = project.getId();
		period.Location = project.Location;
		
		WorkPeriodTable.getTable().insert(period);
	}

	public static void stopPeriod(WorkPeriod periodInProgress) {
		// TODO Auto-generated method stub		
	}

	public static WorkPeriod getPeriodInProgress() {

		WorkPeriod period = WorkPeriodTable.getTable().selectPeriodInProgress();
		return period;
	}

	public static TimeSpan getBreakTime(Project _currentProject) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void insertBreakInPeriod(WorkPeriod periodInProgress,
			TimeSpan breakTime) {
		// TODO Auto-generated method stub
		
	}

}
