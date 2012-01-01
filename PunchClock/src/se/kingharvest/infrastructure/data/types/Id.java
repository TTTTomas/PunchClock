package se.kingharvest.infrastructure.data.types;

import java.io.Serializable;

public class Id implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9216451097485760854L;

	private long _id;

	public Id(long id) {
		_id = id;
	}
	
	public void set(long id)
	{
		_id = id;
	}

	public long get()
	{
		return _id;
	}
	
	public String toString()
	{
		return "Id(" + _id + ")";
	}
}
