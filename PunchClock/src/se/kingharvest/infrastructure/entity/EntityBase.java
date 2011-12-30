package se.kingharvest.infrastructure.entity;

import se.kingharvest.infrastructure.data.types.PrimaryId;


public class EntityBase implements IEntity{

	public PrimaryId Id = new PrimaryId(0);
	
	public PrimaryId getId() {
		return Id;
	}

	public void setId(long id) {
		Id.set(id);
	}
}
