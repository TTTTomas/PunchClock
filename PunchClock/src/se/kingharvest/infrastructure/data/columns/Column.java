package se.kingharvest.infrastructure.data.columns;

import se.kingharvest.infrastructure.data.sqlite.SQLiteHelper;
import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.data.types.PrimaryId;

public class Column {
	
	public enum Order
	{
		First,
		Second,
		Third,
		Fourth
	}

	public final String Name;
	public final Class<?> Type;
	public final int Ordinal;
	public final Order Order;
	
	public final boolean IsIdColumn;
	public final boolean IsPrimaryIdColumn;
	
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
		
		IsIdColumn = type.equals(Id.class) || type.equals(PrimaryId.class);
		IsPrimaryIdColumn = type.equals(PrimaryId.class);
	}
	
	public String toString()
	{
		String indexStr = IsIdColumn ? SQLiteHelper.getIndexString(Name, Ordinal) : "";
		String str = Name + " " + SQLiteHelper.toSqliteType(Type) + " " + indexStr;
		return str;
	}
}
