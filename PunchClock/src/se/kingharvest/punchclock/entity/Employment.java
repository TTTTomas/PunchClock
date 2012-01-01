package se.kingharvest.punchclock.entity;

import java.util.Date;

import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.entity.EntityBase;

public class Employment extends EntityBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6159096457901198384L;

	public enum SalaryFrequency{
		PerHour,
		PerDay,
		PerWeek,
		PerMonth
	}

	public Id Employer;
	public Id Project;
	public Date Start;
	public Date Stop;
	
	public SalaryFrequency SalaryFrequency;
	public double Salary;
	
	public Id Export;
}
