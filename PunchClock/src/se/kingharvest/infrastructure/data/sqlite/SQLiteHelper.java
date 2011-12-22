package se.kingharvest.infrastructure.data.sqlite;

import se.kingharvest.infrastructure.data.columns.Column;
import se.kingharvest.infrastructure.data.columns.ColumnCollection;
import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.entity.IEntity;
import se.kingharvest.infrastructure.system.Strings;
import se.kingharvest.infrastructure.system.Types;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

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
		// BLOB	"BLOB"
		// TEXT	"TEXT"
		// NUMERIC	"NUM"
		// INTEGER	"INT"
		// REAL	"REAL"
		// NONE	"" (empty string)
		
		if(Types.isBoolean(type) || Types.isInteger(type) || Types.isLong(type) || Types.isShort(type) || Types.isByte(type))
			return "INTEGER";
		else if(Types.isString(type) || Types.isChar(type))
			return "TEXT";
		else if(Types.isDouble(type) || Types.isFloat(type))
			return "REAL";
		else if(type.equals(Id.class))
			return "INTEGER ";
		else if(Types.isDate(type))
			return "TEXT ";
		else if(Types.isByteArray(type))
			return "BLOB ";
		
		throw new IllegalArgumentException("Type " + type + " is not a valid Sqlite type.");
	}

	public static String getIndexString(String indexName, int indexOrdinal) {
		String indexString = "CONSTRAINT PK_" + indexName.toUpperCase() + "_" + indexOrdinal + " PRIMARY KEY AUTOINCREMENT";
		return indexString;
	}

	public static <E extends IEntity> Cursor selectAll(SQLiteDatabase database, ColumnCollection<E> columns, String tableName) {
		Cursor c = database.query(tableName, columns.getColumnNames(), null, null, null, null, columns.getSortedColumnsAsString());
		return c;
	}

	public static <E extends IEntity> Cursor selectById(SQLiteDatabase database, ColumnCollection<E> columns, Column idColumn, int id, String tableName) {
		Cursor c = database.query(tableName, columns.getColumnNames(), idColumn + "=", new String[]{ String.valueOf(id) }, null, null, columns.getSortedColumnsAsString());
		return c;
	}
	
	public static <E extends IEntity> SQLiteStatement createUpdateStatement(SQLiteDatabase database, ColumnCollection<E> columns, String tableName)
	{
		String[] columnsWithoutIdColumn = Strings.removeOne(columns.IdColumn.Name, columns.getColumnNames());
		
		String parameters = Strings.join(columnsWithoutIdColumn, "=?, ");

		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ").append(tableName)
			.append(" SET ").append(parameters)
			.append(" WHERE ").append(columns.IdColumn).append("=?");
		
		String sql = sb.toString();

		SQLiteStatement statement = database.compileStatement(sql);
		return statement;
	}

	public static <E extends IEntity> SQLiteStatement createInsertStatement(SQLiteDatabase database, ColumnCollection<E> columns, String tableName)
	{
		String parameters = Strings.join("?", columns.count(), ", ");

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ").append(tableName)
			.append(" (").append(columns.getColumnsAsString()).append(")")
			.append(" VALUES (").append(parameters).append(")");
		
		String sql = sb.toString();

		SQLiteStatement statement = database.compileStatement(sql);
		return statement;
	}

}
