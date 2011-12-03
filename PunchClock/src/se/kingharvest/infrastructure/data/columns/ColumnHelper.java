package se.kingharvest.infrastructure.data.columns;

import java.lang.reflect.Field;
import java.util.HashMap;


import se.kingharvest.infrastructure.entity.IEntity;

public class ColumnHelper {

	public static <E extends IEntity> ColumnCollection<E> createColumnsFromEntityType(Class<E> entityType)
	{
		// Iterate all public fields of an entity and create a column collection from
		// all public fields of the entity.
		Field[] fields = entityType.getFields();
		Column[] columns = new Column[fields.length];
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			columns[i] = new Column(f.getName(), f.getType(), i);
		}
		
		ColumnCollection<E> columnCollection = new ColumnCollection<E>(columns);
		return columnCollection;
	}

	/**
	 * Column collection cache. Maps entity types to column collections.
	 */
	static HashMap<Class<? extends IEntity>, ColumnCollection<? extends IEntity>> _columnCollections = new HashMap<Class<? extends IEntity>, ColumnCollection<? extends IEntity>>();

	@SuppressWarnings("unchecked")
	public static <E extends IEntity> ColumnCollection<E> getColumnsFromEntityType(Class<E> entityType)
	{
		if(_columnCollections.containsKey(entityType))
			return (ColumnCollection<E>) _columnCollections.get(entityType);
		
		ColumnCollection<E> columns = createColumnsFromEntityType(entityType);
		_columnCollections.put(entityType, columns);
		
		return columns;		
	}

}
