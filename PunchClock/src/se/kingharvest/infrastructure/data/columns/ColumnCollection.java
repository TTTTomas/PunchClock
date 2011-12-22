package se.kingharvest.infrastructure.data.columns;

import java.util.HashMap;

import se.kingharvest.infrastructure.entity.IEntity;
import se.kingharvest.infrastructure.system.Strings;

public class ColumnCollection<E extends IEntity> {

	public final Column PrimaryIdColumn;
	public final Column[] Columns;
	public final HashMap<String, Column> ColumnByName = new HashMap<String, Column>();
	
	final String[] _columnNames;
	final String[] _columnsAsStrings;
	String _columnAsString;
	String _columnNamesAsString;
	
	public ColumnCollection(Column ... columns)
	{
		Columns = columns;
		_columnsAsStrings = new String[Columns.length];
		_columnNames = new String[Columns.length];
		PrimaryIdColumn = addColumns(columns);
	}
	
	/**
	 * Adds a set of columns to the collection, returning the id column.
	 * @param columns
	 * @return
	 */
	private Column addColumns(Column[] columns) {
		Column idColumn = null;
		for (int i = 0; i < columns.length; i++) {
			Column c = columns[i];
			_columnsAsStrings[i] = c.toString();
			_columnNames[i] = c.Name;
			ColumnByName.put(c.Name, c);
			if(c.IsPrimaryIdColumn)
			{
				idColumn = c;
			}
		}
		return idColumn;
	}
	
	public int count()
	{
		return Columns.length;
	}

	/**
	 * Returns a comma-separated string of column names.
	 * Used when building SQL query strings such as create table, or insert.
	 * @return
	 */
	public String getColumnsAsString()
	{
		if (_columnAsString == null)
			_columnAsString = Strings.join(_columnsAsStrings, ", ");
		return _columnAsString;
	}

	/**
	 * Returns all column names as an array. 
	 * Used in the native sqlite query API.
	 * @return
	 */
	public String[] getColumnNames()
	{
		return _columnNames;
	}

	public String getSortedColumnsAsString() {
		return null;
	}

	public Column getColumnByName(String columnName) {
		// TODO Auto-generated method stub
		return null;
	}
}
