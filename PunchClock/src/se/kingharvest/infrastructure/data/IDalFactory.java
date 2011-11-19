package se.kingharvest.infrastructure.data;

import se.kingharvest.infrastructure.entity.IEntity;

public interface IDalFactory {

	<E extends IEntity> ITable<E> getDal(Class<E> contract);
	
}
