package se.kingharvest.punchclock.entity;

import se.kingharvest.infrastructure.entity.EntityBase;

public class Location extends EntityBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2941924163837918148L;

	public String Address;
	public String ZipCode;
	public String City;
	public String Country;

	public double Latitude;
	public double Longitude;
}
