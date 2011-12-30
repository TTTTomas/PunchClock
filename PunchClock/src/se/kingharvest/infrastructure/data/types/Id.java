package se.kingharvest.infrastructure.data.types;

public class Id {
	
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
