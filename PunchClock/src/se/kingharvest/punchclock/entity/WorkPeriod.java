package se.kingharvest.punchclock.entity;

import java.util.Date;

import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.entity.EntityBase;

public class WorkPeriod extends EntityBase {

	public Date Start;
	public Date Stop;
	public Id ProjectId;
	public Id[] Activities;
	public Id Location;
	public String Note;
	public boolean InProgress;	
}
