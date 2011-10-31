package se.kingharvest.infrastructure.entity;

public class EntityBase implements IEntity{

	private int _id;
	
	public int getId() {
		return _id;
	}

	void setId(int id) {
		_id = id;
	}

	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
