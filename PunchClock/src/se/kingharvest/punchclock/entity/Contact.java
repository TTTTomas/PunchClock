package se.kingharvest.punchclock.entity;

import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.entity.EntityBase;

public class Contact extends EntityBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7205972552289390318L;

	public String FirstName;
	public String LastName;
	public Id Location;
	public String Note;
	public String Email;
	public String MobilePhone;
	public String Phone;
	public String IntegrationId;
}
