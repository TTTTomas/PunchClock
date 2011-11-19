package se.kingharvest.infrastructure.data.columns;

import java.util.HashMap;

import se.kingharvest.infrastructure.entity.IEntity;
import se.kingharvest.infrastructure.system.Strings;

public class ColumnCollection<E extends IEntity> {

	Column[] _columns;
	String[] _columnsAsStrings;
	String _columnAsString;
	public final Column IdColumn;
	
	static HashMap<Class<? extends IEntity>, ColumnCollection<? extends IEntity>> _columnCollections = new HashMap<Class<? extends IEntity>, ColumnCollection<? extends IEntity>>();
	
	@SuppressWarnings("unchecked")
	public static <E extends IEntity> ColumnCollection<E> getColumnsFromEntityType(Class<E> entityType)
	{
		if(_columnCollections.containsKey(entityType))
			return (ColumnCollection<E>) _columnCollections.get(entityType);
		
		ColumnCollection<E> columns = EntityReflect.createColumnsFromEntityType(entityType);
		_columnCollections.put(entityType, columns);
		
		return columns;		
	}
	
	public ColumnCollection(Column ... columns)
	{
		_columns = columns;
		_columnsAsStrings = new String[_columns.length];
		IdColumn = readColumns(columns);
	}
	
	private Column readColumns(Column[] columns) {
		Column idColumn = null;
		for (int i = 0; i < columns.length; i++) {
			Column c = columns[i];
			_columnsAsStrings[i] = c.toString();
			if(c.IsIdColumn)
			{
				idColumn = c;
			}
		}
		return idColumn;
	}

	public String getColumnsAsString()
	{
		if (_columnAsString == null)
			_columnAsString = Strings.join(_columnsAsStrings, ", ");
		return _columnAsString;
	}
}
