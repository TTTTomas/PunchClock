package se.kingharvest.punchclock.entity;

import java.util.Date;

import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.entity.EntityBase;
import se.kingharvest.infrastructure.time.DateTime;

public class Project extends EntityBase{

	public Project() {
		
	}

	public Project(String projectName) {
		Name = projectName;
		Start = DateTime.minValue();
		Stop = DateTime.minValue();
	}

	public Id Employer;
	public String Name;
	public String Description;
	public Id Location;
	//public Id[] Activities;
	public Date Start;
	public Date Stop;
	public String Note;
	public Id Contact;
	
	public long BreakTime;
}
