package se.kingharvest.infrastructure.entity;

import se.kingharvest.infrastructure.data.types.Id;

public interface IEntity {

	Id getId();
	
	boolean save();
}
