package se.kingharvest.infrastructure.data;

import se.kingharvest.infrastructure.entity.IEntity;

public interface IDalFactory {

	<E extends IEntity> IDal<E> getDal(Class<E> contract);
	
}
