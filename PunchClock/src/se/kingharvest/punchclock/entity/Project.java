package se.kingharvest.punchclock.entity;

import java.util.Date;

import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.entity.EntityBase;

public class Project extends EntityBase{

	public Id Employer;
	public String Name;
	public String Description;
	public Id Location;
	public Id[] Activities;
	public Date Start;
	public Date Stop;
	public String Note;
	public Id Contact;
	
}
