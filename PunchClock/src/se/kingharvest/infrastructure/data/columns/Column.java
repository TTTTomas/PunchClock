package se.kingharvest.infrastructure.data.columns;

import se.kingharvest.infrastructure.data.sqlite.SQLiteHelper;
import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.data.types.PrimaryId;
import android.text.TextUtils;

public class Column {
	
	public enum Order
	{
		First,
		Second,
		Third,
		Fourth
	}

	private final String Name;
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
	
	public String getName() {
		if (!IsPrimaryIdColumn)
			return Name;
		return "_id";
	}

	public String toString()
	{
		String indexStr = IsPrimaryIdColumn ? SQLiteHelper.getIndexString(getName(), true, Ordinal) : "";
		String str = getName() + " " + SQLiteHelper.toSqliteType(Type) + (TextUtils.isEmpty(indexStr) ? "" : " " + indexStr);
		return str;
	}
}
