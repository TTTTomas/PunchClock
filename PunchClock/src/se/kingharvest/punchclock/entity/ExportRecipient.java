package se.kingharvest.punchclock.entity;

import se.kingharvest.infrastructure.entity.EntityBase;

public class ExportRecipient extends EntityBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2144559477459283156L;

	public enum ExportType
	{
		Calendar,
		Email,
		Dropbox,
		GoogleDocs
	}
	
	public enum ExportFormat
	{
		Default,
		CSV,
		XML,
		HTML,
		Text
	}
	
	public ExportType Type;
	public ExportFormat Format;
	
	public String User;
	public String Address;
	public String Connection;
	public String Calendar;
}
