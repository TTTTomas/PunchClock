package se.kingharvest.infrastructure.data.columns;

import se.kingharvest.infrastructure.entity.IEntity;
import se.kingharvest.infrastructure.system.Strings;

public class ColumnCollection<E extends IEntity> {

	public final Column IdColumn;
	public final Column[] Columns;
	
	final String[] _columnNames;
	final String[] _columnsAsStrings;
	String _columnAsString;
	String _columnNamesAsString;
	
	public ColumnCollection(Column ... columns)
	{
		Columns = columns;
		_columnsAsStrings = new String[Columns.length];
		_columnNames = new String[Columns.length];
		IdColumn = addColumns(columns);
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
			if(c.IsIdColumn)
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
}
