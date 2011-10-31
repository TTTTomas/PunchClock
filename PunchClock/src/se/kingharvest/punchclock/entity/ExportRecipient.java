package se.kingharvest.punchclock.entity;

public class ExportRecipient {

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
