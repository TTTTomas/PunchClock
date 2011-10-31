package se.kingharvest.punchclock.entity;

import se.kingharvest.infrastructure.entity.EntityBase;

public class Activity extends EntityBase {
	
	public enum ActivityScope
	{
		Global,
		Employer,
		Project
	}
	
	public String Tag;
	public ActivityScope Scope;
	public int Id;
}
