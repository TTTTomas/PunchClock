package se.kingharvest.infrastructure.entity;

import se.kingharvest.infrastructure.data.types.Id;

public class EntityBase implements IEntity{

	private int _id;
	
	public Id getId() {
		return new Id(_id);
	}

	void setId(int id) {
		_id = id;
	}

	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
