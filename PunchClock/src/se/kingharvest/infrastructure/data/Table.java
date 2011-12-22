package se.kingharvest.infrastructure.data;

import java.lang.reflect.Array;

import se.kingharvest.infrastructure.data.columns.ColumnCollection;
import se.kingharvest.infrastructure.data.columns.ColumnHelper;
import se.kingharvest.infrastructure.data.entity.EntityHelper;
import se.kingharvest.infrastructure.data.sqlite.SQLiteHelper;
import se.kingharvest.infrastructure.entity.EntityBase;
import se.kingharvest.infrastructure.system.Reflect;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class Table<E extends EntityBase> implements ITable<E>{

	public final String TableName;
	public final String IdColumn;
	
	protected final Class<E> _entityType;
	protected final SQLiteDatabase _database;
	
	protected final ColumnCollection<E> _columns;
	
	public SQLiteStatement _insertStatement;
	public SQLiteStatement _updateStatement;
	
	@SuppressWarnings("unchecked")
	public Table(String tableName, SQLiteDatabase database)
	{
		TableName = tableName;
		_entityType = (Class<E>) Reflect.getGenericType(getClass());
		_database = database;
		_columns = ColumnHelper.getColumnsFromEntityType(_entityType);
		IdColumn = _columns.IdColumn.Name;
	}
	
	public void createTable()
	{
		SQLiteHelper.createTable(_database, TableName, _columns);
	}
	
	public void dropTable()
	{
		SQLiteHelper.dropTable(_database, TableName);
	}

	public E select(int id) {

		Cursor cursor = SQLiteHelper.selectById(_database, _columns, _columns.IdColumn, id, TableName);
		
		if(cursor == null)
			return null;
		
		E entity = EntityHelper.createEntityFromCursor(cursor, _columns, _entityType);
		cursor.close();
		return entity;
	}

	@SuppressWarnings("unchecked")
	public E[] selectAll() {
		
		Cursor cursor = SQLiteHelper.selectAll(_database, _columns, TableName);
		
		if(cursor == null)
			return (E[]) Array.newInstance(_entityType, 0);
		
		int i = 0;
		E[] entities = (E[]) Array.newInstance(_entityType, cursor.getCount());
		while (cursor.moveToNext()) {
			entities[i] = EntityHelper.createEntityFromCursor(cursor, _columns, _entityType);
		}
		cursor.close();
		return entities;
	}

	public int delete(int id) {
		return SQLiteHelper.delete(_database, TableName, IdColumn, id);
	}

	public long update(E entity) {

		// Use a reusable update statement. Create it if it doesn't exist.
		if (_updateStatement == null){
			synchronized(_updateStatement){
				_updateStatement = SQLiteHelper.createUpdateStatement(_database, _columns, TableName);				
			}
		}

		EntityHelper.bindEntityToStatement(entity, _updateStatement, _columns);
		long id = _updateStatement.executeInsert();
		return id;
	}

	public long insert(E entity) {
		
		// Use a reusable insert statement. Create it if it doesn't exist.
		if (_insertStatement == null){
			synchronized(_insertStatement){
				_insertStatement = SQLiteHelper.createInsertStatement(_database, _columns, TableName);				
			}
		}
		
		EntityHelper.bindEntityToStatement(entity, _insertStatement, _columns);
		long id = _insertStatement.executeInsert();
		return id;
	}

	/**
	 * Creates a single entity from a cursor.
	 * Closes the cursor when done.
	 * @param cursor
	 * @return
	 */
	protected E returnEntityFromCursor(Cursor cursor) {
		E entity = EntityHelper.createEntityFromCursor(cursor, _columns, _entityType);
		cursor.close();
		return entity;
	}

}
