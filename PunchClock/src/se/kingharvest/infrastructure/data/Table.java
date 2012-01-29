package se.kingharvest.infrastructure.data;

import java.lang.reflect.Array;

import se.kingharvest.infrastructure.data.columns.ColumnCollection;
import se.kingharvest.infrastructure.data.columns.ColumnHelper;
import se.kingharvest.infrastructure.data.entity.EntityHelper;
import se.kingharvest.infrastructure.data.sqlite.SQLiteHelper;
import se.kingharvest.infrastructure.entity.EntityBase;
import se.kingharvest.infrastructure.reflection.Reflect;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

public class Table<E extends EntityBase> implements ITable<E>{

	public final String TableName;
	public final String IdColumn;
	
	protected final Class<E> _entityType;
	protected final Database _database;
	
	protected final ColumnCollection<E> _columns;
	
	public SQLiteStatement _insertStatement;
	public SQLiteStatement _updateStatement;
	
	@SuppressWarnings("unchecked")
	public Table(String tableName, Database database)
	{
		TableName = tableName;
		_entityType = (Class<E>) Reflect.getGenericType(getClass());
		_database = database;
		_columns = ColumnHelper.getColumnsFromEntityType(_entityType);
		IdColumn = _columns.PrimaryIdColumn.Name;
	}
	
	public boolean tableExists()
	{
		return SQLiteHelper.tableExists(_database.getWritableDatabase(), TableName);
	}

	public void createTable()
	{
		SQLiteHelper.createTable(_database.getWritableDatabase(), TableName, _columns);
	}
	
	public void createTableIfNeeded() {
		if(!tableExists())
			createTable();
	}

	public void dropTable()
	{
		SQLiteHelper.dropTable(_database.getWritableDatabase(), TableName);
	}

	public E select(long id) {

		Cursor cursor = SQLiteHelper.selectById(_database.getReadableDatabase(), _columns, id, TableName);
		
		if(cursor == null)
			return null;
		
		E entity = EntityHelper.createEntityFromCursor(cursor, _columns, _entityType);
		cursor.close();
		return entity;
	}

	@SuppressWarnings("unchecked")
	public E[] selectAll() {
		
		Cursor cursor = SQLiteHelper.selectAll(_database.getReadableDatabase(), _columns, TableName);
		
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

	public int delete(long id) {
		return SQLiteHelper.delete(_database.getWritableDatabase(), TableName, IdColumn, id);
	}

	private Object _updateStatementLock = new Object();
	public void update(E entity) {

		// Use a reusable update statement. Create it if it doesn't exist.
		if (_updateStatement == null){
			synchronized(_updateStatementLock){
				_updateStatement = SQLiteHelper.createUpdateStatement(_database.getWritableDatabase(), _columns, TableName);				
			}
		}

		EntityHelper.bindEntityToUpdateStatement(entity, _updateStatement, _columns);
		_updateStatement.execute();
	}

	private Object _insertStatementLock = new Object();
	public E insert(E entity) {
		
		// Use a reusable insert statement. Create it if it doesn't exist.
		if (_insertStatement == null){
			synchronized(_insertStatementLock){
				_insertStatement = SQLiteHelper.createInsertStatement(_database.getWritableDatabase(), _columns, TableName);				
			}
		}
		
		EntityHelper.bindEntityToStatement(entity, _insertStatement, _columns);
		long id = _insertStatement.executeInsert();
		entity.setId(id);
		return entity;
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
