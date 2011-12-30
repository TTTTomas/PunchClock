package se.kingharvest.infrastructure.entity;

import se.kingharvest.infrastructure.data.types.PrimaryId;


public interface IEntity {

	PrimaryId getId();

	void setId(long id);
}
