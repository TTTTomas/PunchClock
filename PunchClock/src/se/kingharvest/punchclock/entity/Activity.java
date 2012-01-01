package se.kingharvest.punchclock.entity;

import se.kingharvest.infrastructure.entity.EntityBase;

public class Activity extends EntityBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8096321038451743208L;

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
