package se.kingharvest.infrastructure.data.entity;

import se.kingharvest.infrastructure.data.DatabaseException;
import se.kingharvest.infrastructure.data.columns.Column;
import se.kingharvest.infrastructure.data.columns.ColumnCollection;
import se.kingharvest.infrastructure.entity.IEntity;
import se.kingharvest.infrastructure.system.Reflect;
import se.kingharvest.infrastructure.system.Types;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

public class EntityHelper {

	/**
	 * Puts all field values of an entity into a statement as specified by a column collection.
	 * @param entity
	 * @param statement
	 * @param columnCollection
	 */
	public static <E extends IEntity> void bindEntityToStatement(E entity, SQLiteStatement statement, ColumnCollection<E> columnCollection)
	{
		Column[] columns = columnCollection.Columns;
		for (int i = 0; i < columns.length; i++) {
			Column column = columns[i];
			if(!column.IsPrimaryIdColumn)
				bindValueToStatement(entity, statement, column);
		}
	}

	/**
	 * Puts a single field value of an entity into a statement as specified by a column.
	 * @param entity
	 * @param statement
	 * @param column
	 */
	public static <E extends IEntity> void bindValueToStatement(E entity, SQLiteStatement statement, Column column)
	{
		try
		{
			Class<?> type = column.Type;
			int index = column.Ordinal+1;
			if(Types.isInteger(type))
				statement.bindLong(index, Reflect.getInt(column.Name, entity));
			if(Types.isId(type) || Types.isPrimaryId(type))
				statement.bindLong(index, Reflect.getId(column.Name, entity));
			else if(Types.isLong(type))
				statement.bindLong(index, Reflect.getLong(column.Name, entity));
			else if(Types.isShort(type))
				statement.bindLong(index, Reflect.getShort(column.Name, entity));
			else if(Types.isByte(type))
				statement.bindLong(index, Reflect.getByte(column.Name, entity));
			else if(Types.isBoolean(type)) 
				statement.bindLong(index, Reflect.getBoolean(column.Name, entity) ? 1 : 0);
			else if(Types.isString(type)){
				String value = Reflect.getString(column.Name, entity);
				if (value == null)
					statement.bindNull(index);
				else
					statement.bindString(index, value);		
			}
			else if(Types.isChar(type)) 
				statement.bindString(index, String.valueOf(Reflect.getChar(column.Name, entity)));
			else if(Types.isDate(type)){
				String value = Reflect.getDateString(column.Name, entity);
				if (value == null)
					statement.bindNull(index);
				else
					statement.bindString(index, value);		
			}
			else if(Types.isDouble(type)) 
				statement.bindDouble(index, Reflect.getDouble(column.Name, entity));
			else if(Types.isFloat(type)) 
				statement.bindDouble(index, Reflect.getFloat(column.Name, entity));
			else if(Types.isByteArray(type)){
				byte[] value = Reflect.getByteArray(column.Name, entity);
				if (value == null)
					statement.bindNull(index);
				else
					statement.bindBlob(index, value);		
			}
		}
		catch (Exception e)
		{
			throw new DatabaseException("Failed to bind entity type " + entity.getClass().getSimpleName() + " column " + column.toString() + " to Sqlite statement.", e);
		}
	}

	/**
	 * Creates a new entity from the contents of a cursor, as specified by a column collection.
	 * It is not necessary for the cursor to fully match a full entity. If the cursor contains less columns,
	 * the entity will be filled with whatever the cursor contains.
	 * @param cursor
	 * @param columns
	 * @param entityType
	 * @return
	 */
	public static <E extends IEntity> E createEntityFromCursor(Cursor cursor, ColumnCollection<E> columns, Class<E> entityType)
	{
		if(cursor != null && cursor.moveToFirst())
		{
			E entity = Reflect.newInstance(entityType);
		
			// Iterates the cursor instead of columns. This makes it possible to create entities 
			// from "incomplete" queries.
			int count = cursor.getColumnCount();
			for (int i = 0; i < count; i++) {
				String columnName = cursor.getColumnName(i);
				Column column = columns.ColumnByName.get(columnName);
				EntityHelper.setValueFromCursor(cursor, entity, i, column);
			}
			return entity;
		}
		return null;
	}

	/**
	 * Sets a single field value in an entity from a cursor as specified by a column.
	 * @param cursor
	 * @param entity
	 * @param cursorIndex
	 * @param column
	 */
	public static <E extends IEntity> void setValueFromCursor(Cursor cursor, E entity, int cursorIndex, Column column)
	{
		Class<?> type = column.Type;
		if(Types.isInteger(type))
			Reflect.setInteger(column.Name, entity, cursor.getInt(cursorIndex));
		else if(Types.isId(type) || Types.isPrimaryId(type))
			Reflect.setId(column.Name, entity, cursor.getLong(cursorIndex));
		else if(Types.isLong(type))
			Reflect.setLong(column.Name, entity, cursor.getLong(cursorIndex));
		else if(Types.isShort(type))
			Reflect.setShort(column.Name, entity, cursor.getShort(cursorIndex));
		else if(Types.isString(type))
			Reflect.set(column.Name, entity, cursor.getString(cursorIndex));
		else if(Types.isDouble(type))
			Reflect.setDouble(column.Name, entity, cursor.getDouble(cursorIndex));
		else if(Types.isBoolean(type))
			Reflect.setBoolean(column.Name, entity, cursor.getInt(cursorIndex) != 0);
		else if(Types.isFloat(type))
			Reflect.setFloat(column.Name, entity, cursor.getFloat(cursorIndex));
		else if(Types.isDate(type))
			Reflect.setDateString(column.Name, entity, cursor.getString(cursorIndex));
		else if(Types.isByte(type))
			Reflect.setByte(column.Name, entity, (byte) cursor.getShort(cursorIndex));
		else if(Types.isByteArray(type))
			Reflect.setByteArrayClone(column.Name, entity, cursor.getBlob(cursorIndex));
		else if(Types.isChar(type)) 
			Reflect.setChar(column.Name, entity, cursor.getString(cursorIndex).charAt(0));
		else
			throw new IllegalArgumentException("Value type " + type + " is not a valid type for setting field values.");			
	}
}
