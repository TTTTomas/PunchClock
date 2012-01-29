package se.kingharvest.infrastructure.data.entity;

import se.kingharvest.infrastructure.data.DatabaseException;
import se.kingharvest.infrastructure.data.columns.Column;
import se.kingharvest.infrastructure.data.columns.ColumnCollection;
import se.kingharvest.infrastructure.entity.IEntity;
import se.kingharvest.infrastructure.reflection.FieldReflect;
import se.kingharvest.infrastructure.reflection.Reflect;
import se.kingharvest.infrastructure.system.Types;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

public class EntityHelper {

	/**
	 * Puts all field values of an entity into a statement as specified by a column collection.
	 * The primary key is skipped, therefore not bound.
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
	 * Binds an entity to an update statement. Similar to binding to an insert statement, except that the primary id is also bound, last.
	 * @param entity
	 * @param statement
	 * @param columnCollection
	 */
	public static <E extends IEntity> void bindEntityToUpdateStatement(E entity, SQLiteStatement statement, ColumnCollection<E> columnCollection)
	{
		// Bind entity.
		bindEntityToStatement(entity, statement, columnCollection);
		
		// Bind primary key.
		Column primaryKey = columnCollection.PrimaryIdColumn;
		bindValueToStatement(entity, statement, primaryKey);
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
				statement.bindLong(index, FieldReflect.getInt(column.Name, entity));
			if(Types.isId(type) || Types.isPrimaryId(type))
				statement.bindLong(index, FieldReflect.getId(column.Name, entity));
			else if(Types.isLong(type))
				statement.bindLong(index, FieldReflect.getLong(column.Name, entity));
			else if(Types.isShort(type))
				statement.bindLong(index, FieldReflect.getShort(column.Name, entity));
			else if(Types.isByte(type))
				statement.bindLong(index, FieldReflect.getByte(column.Name, entity));
			else if(Types.isBoolean(type)) 
				statement.bindLong(index, FieldReflect.getBoolean(column.Name, entity) ? 1 : 0);
			else if(Types.isString(type)){
				String value = FieldReflect.getString(column.Name, entity);
				if (value == null)
					statement.bindNull(index);
				else
					statement.bindString(index, value);		
			}
			else if(Types.isChar(type)) 
				statement.bindString(index, String.valueOf(FieldReflect.getChar(column.Name, entity)));
			else if(Types.isDate(type)){
				String value = FieldReflect.getDateString(column.Name, entity);
				if (value == null)
					statement.bindNull(index);
				else
					statement.bindString(index, value);		
			}
			else if(Types.isDouble(type)) 
				statement.bindDouble(index, FieldReflect.getDouble(column.Name, entity));
			else if(Types.isFloat(type)) 
				statement.bindDouble(index, FieldReflect.getFloat(column.Name, entity));
			else if(Types.isByteArray(type)){
				byte[] value = FieldReflect.getByteArray(column.Name, entity);
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
			FieldReflect.setInt(column.Name, entity, cursor.getInt(cursorIndex));
		else if(Types.isId(type) || Types.isPrimaryId(type))
			FieldReflect.setId(column.Name, entity, cursor.getLong(cursorIndex));
		else if(Types.isLong(type))
			FieldReflect.setLong(column.Name, entity, cursor.getLong(cursorIndex));
		else if(Types.isShort(type))
			FieldReflect.setShort(column.Name, entity, cursor.getShort(cursorIndex));
		else if(Types.isString(type))
			FieldReflect.set(column.Name, entity, cursor.getString(cursorIndex));
		else if(Types.isDouble(type))
			FieldReflect.setDouble(column.Name, entity, cursor.getDouble(cursorIndex));
		else if(Types.isBoolean(type))
			FieldReflect.setBoolean(column.Name, entity, cursor.getInt(cursorIndex) != 0);
		else if(Types.isFloat(type))
			FieldReflect.setFloat(column.Name, entity, cursor.getFloat(cursorIndex));
		else if(Types.isDate(type))
			FieldReflect.setDateString(column.Name, entity, cursor.getString(cursorIndex));
		else if(Types.isByte(type))
			FieldReflect.setByte(column.Name, entity, (byte) cursor.getShort(cursorIndex));
		else if(Types.isByteArray(type))
			FieldReflect.setByteArrayClone(column.Name, entity, cursor.getBlob(cursorIndex));
		else if(Types.isChar(type)) 
			FieldReflect.setChar(column.Name, entity, cursor.getString(cursorIndex).charAt(0));
		else
			throw new IllegalArgumentException("Value type " + type + " is not a valid type for setting field values.");			
	}
}
