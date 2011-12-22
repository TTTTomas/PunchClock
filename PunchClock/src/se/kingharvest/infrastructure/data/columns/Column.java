package se.kingharvest.infrastructure.data.columns;

import se.kingharvest.infrastructure.data.sqlite.SQLiteHelper;
import se.kingharvest.infrastructure.data.types.Id;

public class Column {
	
	public enum Order
	{
		First,
		Second,
		Third,
		Fourth
	}

	public String Name;
	public Class<?> Type;
	public int Ordinal;
	public Order Order;
	
	public boolean IsIdColumn;
	
	public Column(String name, Class<?> type, int ordinal)
	{
		this(name, type, ordinal, null);
	}

	public Column(String name, Class<?> type, int ordinal, Order order)
	{
		Name = name;
		Type = type;
		Ordinal = ordinal;
		Order = order;
		
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
