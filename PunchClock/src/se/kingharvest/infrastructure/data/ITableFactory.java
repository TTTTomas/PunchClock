package se.kingharvest.infrastructure.data;

import se.kingharvest.infrastructure.entity.IEntity;

public interface ITableFactory {

	<T extends ITable<E>, E extends IEntity> T getTableByType(Class<T> contract);
	
}
