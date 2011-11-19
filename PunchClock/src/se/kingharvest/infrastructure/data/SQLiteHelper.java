package se.kingharvest.infrastructure.data;

import se.kingharvest.infrastructure.data.columns.ColumnCollection;
import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.entity.IEntity;
import se.kingharvest.infrastructure.system.Reflect;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteHelper {
	
	public static <E extends IEntity> void createTable(SQLiteDatabase database, String tableName, ColumnCollection<E> columns)
	{
		String sql = "CREATE TABLE " + tableName + " (" + columns.getColumnsAsString() + ")";
		database.execSQL(sql);
	}
	
	public static void dropTable(SQLiteDatabase database, String tableName)
	{
		String sql = "DROP TABLE " + tableName;
		database.execSQL(sql);
	}

	public static int delete(SQLiteDatabase database, String tableName, String idColumn, int id) {
		return database.delete(tableName, idColumn + "=?", new String[]{ String.valueOf(id) });
	}

	public static String toSqliteType(Class<?> type) {

		// Available Sqlite types:
		// TEXT	"TEXT"
		// NUMERIC	"NUM"
		// INTEGER	"INT"
		// REAL	"REAL"
		// NONE	"" (empty string)
		
		if(Reflect.isBoolean(type) || Reflect.isInteger(type))
			return "INTEGER";
		else if(Reflect.isString(type))
			return "TEXT";
		else if(Reflect.isDouble(type))
			return "REAL";
		else if(type.equals(Id.class))
			return "INTEGER ";
		
		throw new IllegalArgumentException("Type " + type + " is not a valid Sqlite type.");
	}

	public static String getIndexString(String indexName, int indexOrdinal) {
		String indexString = "CONSTRAINT PK_" + indexName.toUpperCase() + "_" + indexOrdinal + " PRIMARY KEY AUTOINCREMENT";
		return indexString;
	}
}
