package se.kingharvest.infrastructure.data;

import se.kingharvest.infrastructure.entity.IEntity;

public interface IDalFactory {

	 <T extends ITable<E>, E extends IEntity> T getDal(Class<E> contract);
	
}
