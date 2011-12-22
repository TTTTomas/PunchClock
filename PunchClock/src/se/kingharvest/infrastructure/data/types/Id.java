package se.kingharvest.infrastructure.data.types;

public class Id {
	
	private int _id;

	public Id(int id) {
		_id = id;
	}
	
	public void set(int id)
	{
		_id = id;
	}

	public int get()
	{
		return _id;
	}
	
	public String toString()
	{
		return "Id(" + _id + ")";
	}
}
