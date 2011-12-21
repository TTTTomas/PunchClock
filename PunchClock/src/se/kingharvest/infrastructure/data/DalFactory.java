package se.kingharvest.infrastructure.data;

import java.util.HashMap;

import se.kingharvest.infrastructure.entity.IEntity;

public class DalFactory implements IDalFactory{

	static DalFactory _instance;
	
	static HashMap<Class<? extends IEntity>, ITable<?>> _dalCache = new HashMap<Class<? extends IEntity>, ITable<?>>();
	
	
	
	@SuppressWarnings("unchecked")
	public <T extends ITable<E>, E extends IEntity> T getDal(Class<E> contract) 
	{
		if(_dalCache.containsKey(contract))
		{
			return (T) _dalCache.get(contract);
		}
		return null;
	}

	public static DalFactory getInstance() {
		if(_instance == null)
		{
			_instance = new DalFactory();
		}
		return _instance;
	}

}
