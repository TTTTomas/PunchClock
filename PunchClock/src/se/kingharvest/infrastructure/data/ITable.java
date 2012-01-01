package se.kingharvest.infrastructure.data;

import se.kingharvest.infrastructure.entity.IEntity;

public interface ITable<E extends IEntity> {
	
	boolean tableExists();

	void dropTable();

	void createTable();

	void createTableIfNeeded();

	E select(long id);

	E[] selectAll();
	
	int delete(long id);
	
	void update(E entity);
	
	E insert(E entity);

}
