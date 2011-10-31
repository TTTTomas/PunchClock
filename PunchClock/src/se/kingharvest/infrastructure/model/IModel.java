package se.kingharvest.infrastructure.model;

import se.kingharvest.infrastructure.entity.IEntity;

public interface IModel<E extends IEntity> {
	
	void load(E entity);
	
	boolean save();
	
	boolean isEditable();
	
	boolean isDirty();
}
