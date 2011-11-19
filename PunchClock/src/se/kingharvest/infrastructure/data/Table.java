package se.kingharvest.infrastructure.data;

import se.kingharvest.infrastructure.data.columns.ColumnCollection;
import se.kingharvest.infrastructure.entity.EntityBase;
import se.kingharvest.infrastructure.system.Reflect;
import android.database.sqlite.SQLiteDatabase;

public class Table<E extends EntityBase> implements ITable<E>{

	public final String TableName;
	public final String IdColumn;
	
	final Class<E> _entityClass;
	final SQLiteDatabase _database;
	
	final ColumnCollection<E> _columns;
	
	@SuppressWarnings("unchecked")
	public Table(String tableName, SQLiteDatabase database)
	{
		TableName = tableName;
		_entityClass = (Class<E>) Reflect.getGenericType(getClass());
		_database = database;
		_columns = ColumnCollection.getColumnsFromEntityType(_entityClass);
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
		// TODO Auto-generated method stub
		return null;
	}

	public E[] selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int delete(int id) {
		return SQLiteHelper.delete(_database, TableName, IdColumn, id);
	}

	public int update(E entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(E entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}
