package se.kingharvest.infrastructure.entity;

import java.io.Serializable;

import se.kingharvest.infrastructure.data.types.PrimaryId;


public class EntityBase implements IEntity, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7848219534546131562L;

	public PrimaryId Id = new PrimaryId(0);
	
	public PrimaryId getId() {
		return Id;
	}

	public void setId(long id) {
		Id.set(id);
	}
}
