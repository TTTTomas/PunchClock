package se.kingharvest.infrastructure.data.entity;

import se.kingharvest.infrastructure.data.columns.Column;
import se.kingharvest.infrastructure.data.columns.ColumnCollection;
import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.entity.IEntity;
import se.kingharvest.infrastructure.system.Reflect;
import se.kingharvest.infrastructure.system.Types;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

public class EntityHelper {

	public static <E extends IEntity> E createEntityFromCursor(Cursor cursor, ColumnCollection<E> columns, Class<E> entityType)
	{
		E entity = Reflect.newInstance(entityType);
	
		// FIXME: should iterate cursor instead of columns.
		Column[] cols = columns.Columns;
		for (int i = 0; i < cols.length; i++) {
			EntityHelper.setValue(cursor, cols[i], entity);
		}
		return entity;
	}

	public static <E extends IEntity> void bindValue(SQLiteStatement statement, E entity, Column column)
	{
		Class<?> type = column.Type;
		int index = column.Ordinal+1;
		if(Types.isInteger(type) || type.equals(Id.class))
			statement.bindLong(index, Reflect.getInt(column.Name, entity));
		else if(Types.isLong(type))
			statement.bindLong(index, Reflect.getLong(column.Name, entity));
		else if(Types.isShort(type))
			statement.bindLong(index, Reflect.getShort(column.Name, entity));
		else if(Types.isByte(type))
			statement.bindLong(index, Reflect.getByte(column.Name, entity));
		else if(Types.isBoolean(type)) 
			statement.bindLong(index, Reflect.getBoolean(column.Name, entity) ? 1 : 0);
		else if(Types.isString(type)) 
			statement.bindString(index, Reflect.getString(column.Name, entity));
		else if(Types.isChar(type)) 
			statement.bindString(index, String.valueOf(Reflect.getChar(column.Name, entity)));
		else if(Types.isDate(type)) 
			statement.bindString(index, Reflect.getDateString(column.Name, entity));
		else if(Types.isDouble(type)) 
			statement.bindDouble(index, Reflect.getDouble(column.Name, entity));
		else if(Types.isFloat(type)) 
			statement.bindDouble(index, Reflect.getFloat(column.Name, entity));
		else if(Types.isByteArray(type))
			statement.bindBlob(index, Reflect.getByteArray(column.Name, entity));
	}

	public static <E extends IEntity> void bindEntityToStatement(SQLiteStatement statement, E entity, ColumnCollection<E> columnCollection)
	{
		Column[] columns = columnCollection.Columns;
		for (int i = 0; i < columns.length; i++) {
			Column column = columns[i];
			bindValue(statement, entity, column);
		}
	}

	public static <E extends IEntity> void setValue(Cursor cursor, Column column, E entity)
		{
			Class<?> type = column.Type;
			int index = cursor.getColumnIndex(column.Name);
			if(Types.isBoolean(type))
				Reflect.setBoolean(column.Name, entity, cursor.getInt(index) != 0);
			else if(type.getClass().equals(Id.class))
				Reflect.setInteger(column.Name, entity, cursor.getInt(index));
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
			else if(Types.isByte(type))
				Reflect.setByte(column.Name, entity, cursor.getShort(index));
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
}
