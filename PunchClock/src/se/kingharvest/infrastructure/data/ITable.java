package se.kingharvest.infrastructure.data;

import se.kingharvest.infrastructure.entity.IEntity;

public interface ITable<E extends IEntity> {
	
	
	
	E select(int id);

	E[] selectAll();
	
	int delete(int id);
	
	int update(E entity);
	
	int insert(E entity);

}
