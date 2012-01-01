package se.kingharvest.punchclock.entity;

import java.util.Date;

import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.entity.EntityBase;

public class WorkPeriod extends EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4656657479432857086L;

	public Date Start;
	public Date Stop;
	public Id ProjectId;
	//public Id[] Activities;
	public Id Location;
	public String Note;
	public boolean InProgress;
	
	/**
	 * Returns a copy of this WorkPeriod. The new period does not contain
	 * a set Start/Stop date or an id.
	 * @return
	 */
	public WorkPeriod copy() {

		WorkPeriod period = new WorkPeriod();
		period.ProjectId = ProjectId;
		//period.Activities = Activities.clone();
		period.Location = Location;
		period.Note = period.Note;
		
		return period;
	}
}
