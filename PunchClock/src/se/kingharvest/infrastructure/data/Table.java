package se.kingharvest.infrastructure.data;

import java.lang.reflect.Array;
import java.sql.Statement;

import se.kingharvest.infrastructure.data.columns.ColumnCollection;
import se.kingharvest.infrastructure.data.columns.ColumnReflect;
import se.kingharvest.infrastructure.data.entity.EntityHelper;
import se.kingharvest.infrastructure.entity.EntityBase;
import se.kingharvest.infrastructure.system.Reflect;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class Table<E extends EntityBase> implements ITable<E>{

	public final String TableName;
	public final String IdColumn;
	
	final Class<E> _entityType;
	final SQLiteDatabase _database;
	
	final ColumnCollection<E> _columns;
	
	public SQLiteStatement _insertStatement;
	
	@SuppressWarnings("unchecked")
	public Table(String tableName, SQLiteDatabase database)
	{
		TableName = tableName;
		_entityType = (Class<E>) Reflect.getGenericType(getClass());
		_database = database;
		_columns = ColumnReflect.getColumnsFromEntityType(_entityType);
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

	public int update(E entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long insert(E entity) {
		
		// Use a reusable insert statement. Create it if it doesn't exist.
		if (_insertStatement == null){
			synchronized(_insertStatement){
				_insertStatement = SQLiteHelper.createInsertStatement(_database, _columns, TableName);				
			}
		}
		
		SQLiteHelper.bindStatement(_insertStatement, entity, _columns);
		long id = _insertStatement.executeInsert();
		return id;
	}

}
