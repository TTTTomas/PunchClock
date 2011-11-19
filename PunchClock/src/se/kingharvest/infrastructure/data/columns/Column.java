package se.kingharvest.infrastructure.data.columns;

import se.kingharvest.infrastructure.data.SQLiteHelper;
import se.kingharvest.infrastructure.data.types.Id;

public class Column {

	public String Name;
	public Class<?> Type;
	public int Ordinal;
	
	public boolean IsIdColumn;
	
	public Column(String name, Class<?> type, int ordinal)
	{
		Name = name;
		Type = type;
		Ordinal = ordinal;
		
		if(type.equals(Id.class))
			IsIdColumn = true;
	}
	
	public String toString()
	{
		String indexStr = IsIdColumn ? SQLiteHelper.getIndexString(Name, Ordinal) : "";
		String str = Name + " " + SQLiteHelper.toSqliteType(Type) + " " + indexStr;
		return str;
	}
}
