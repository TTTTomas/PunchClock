package se.kingharvest.punchclock.entity;

import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.entity.EntityBase;

public class Employer extends EntityBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7623380177890510007L;

	public String Name;
	public String Description;
	public Id Location;
	public Id Contact;
	
	public String IntegrationId;
}
