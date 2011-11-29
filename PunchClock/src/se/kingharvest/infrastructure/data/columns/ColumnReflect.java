package se.kingharvest.infrastructure.data.columns;

import java.lang.reflect.Field;
import java.util.HashMap;

import android.database.Cursor;

import se.kingharvest.infrastructure.entity.IEntity;
import se.kingharvest.infrastructure.system.Reflect;
import se.kingharvest.infrastructure.system.Types;

public class ColumnReflect {

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

	public static <E extends IEntity> void setValue(Cursor cursor, Column column, E entity)
	{
		Class<?> type = column.Type;
		int index = cursor.getColumnIndex(column.Name);
		if(Types.isBoolean(type))
			Reflect.setBoolean(column.Name, entity, cursor.getInt(index) != 0);
		else if(Types.isInteger(type))
			Reflect.setInteger(column.Name, entity, cursor.getInt(index));
		else if(Types.isLong(type))
			Reflect.setLong(column.Name, entity, cursor.getLong(index));
		else if(Types.isShort(type))
			Reflect.setShort(column.Name, entity, cursor.getShort(index));
		else if(Types.isString(type))
			Reflect.set(column.Name, entity, cursor.getString(index));
		else if(Types.isDouble(type))
			Reflect.setDouble(column.Name, entity, cursor.getDouble(index));
		else if(Types.isFloat(type))
			Reflect.setFloat(column.Name, entity, cursor.getFloat(index));
		else
			throw new IllegalArgumentException("Value type " + type + " is not a valid type for setting field values.");
		
//		else if(Types.isChar(type))
//			Reflect.setFloat(column.Name, entity, cursor.getFloat(index));
//		else if(Types.isByte(type))
//			Reflect.setFloat(column.Name, entity, cursor.getFloat(index));
//		else if(Types.isDate(type))
//			Reflect.setFloat(column.Name, entity, cursor.getFloat(index));
//		else if(Types.isByte(type))
//			Reflect.setFloat(column.Name, entity, cursor.getFloat(index));
		
		
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
