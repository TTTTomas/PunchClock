package se.kingharvest.infrastructure.data;

import java.util.HashMap;

import se.kingharvest.infrastructure.entity.IEntity;
import se.kingharvest.infrastructure.reflection.Reflect;

public class TableFactory implements ITableFactory{

	static TableFactory _instance;
	
	static Database _database;
	
	private HashMap<Class<ITable<IEntity>>, ITable<IEntity>> _tableCache;

	public static <T extends ITable<E>, E extends IEntity> T getTable(Class<T> contract)	
	{
		return TableFactory.getInstance().getTableByType(contract);
	}
	
	
	@SuppressWarnings("unchecked")
	public <T extends ITable<E>, E extends IEntity> T getTableByType(Class<T> contract)
	{
		if(_tableCache == null)
			_tableCache = new HashMap<Class<ITable<IEntity>>, ITable<IEntity>>();
		
		if(_tableCache.containsKey(contract))
			return (T) _tableCache.get(contract);

		T dal = Reflect.newInstance(contract, Database.class, _database);
		_tableCache.put((Class<ITable<IEntity>>)contract, (ITable<IEntity>)dal);
		return dal;
	}

	public static TableFactory getInstance() {
		if(_instance == null)
		{
			_instance = new TableFactory();
		}
		return _instance;
	}

	public void setDatabase(Database database) {
		// Changing the database invalidates the cache.
		_tableCache = null;
		_database = database;
	}

}
