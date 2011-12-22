package se.kingharvest.punchclock.model;

import java.util.Date;

import se.kingharvest.infrastructure.time.DateTime;
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
	
		// If stop occurs after start, then that means that the period was in
		// a break and that it is stopped before it manages to even start.
		periodInProgress.Stop = new Date();
		if (periodInProgress.Stop.before(periodInProgress.Start))
		{
			WorkPeriodTable.getTable().delete(periodInProgress.getId().get());
		}
		else
		{
			periodInProgress.InProgress = false;
			WorkPeriodTable.getTable().update(periodInProgress);
		}
	}

	public static WorkPeriod getPeriodInProgress() {

		WorkPeriod period = WorkPeriodTable.getTable().selectPeriodInProgress();
		return period;
	}

	public static TimeSpan getBreakTime(Project currentProject) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void insertBreakInPeriod(WorkPeriod periodInProgress, TimeSpan breakTime) {
		
		stopPeriod(periodInProgress);
		WorkPeriod nextPeriod = periodInProgress.copy();
		nextPeriod.Stop = null;
		nextPeriod.Start = DateTime.add(new Date(), breakTime);
		nextPeriod.InProgress = true;
		
		WorkPeriodTable.getTable().insert(nextPeriod);
	}

}
