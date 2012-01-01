package se.kingharvest.infrastructure.data.sqlite;

import se.kingharvest.infrastructure.data.columns.ColumnCollection;
import se.kingharvest.infrastructure.entity.IEntity;
import se.kingharvest.infrastructure.system.Strings;
import se.kingharvest.infrastructure.system.Types;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;

public class SQLiteHelper {
	
	public static <E extends IEntity> void createTable(SQLiteDatabase database, String tableName, ColumnCollection<E> columns)
	{
		//String idColumnSql = getIndexString("IdColumn", 0);
		String sql = "CREATE TABLE " + tableName + " (" + columns.getColumnsAsString() + ")";
		database.execSQL(sql);
	}
	
	public static void dropTable(SQLiteDatabase database, String tableName)
	{
		String sql = "DROP TABLE " + tableName;
		database.execSQL(sql);
	}

	public static int delete(SQLiteDatabase database, String tableName, String idColumn, long id) {
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
		else if(Types.isId(type) || Types.isPrimaryId(type))
			return "INTEGER";
		else if(Types.isString(type) || Types.isChar(type))
			return "TEXT";
		else if(Types.isDouble(type) || Types.isFloat(type))
			return "REAL";
		else if(Types.isDate(type))
			return "TEXT";
		else if(Types.isByteArray(type))
			return "BLOB";
		
		throw new IllegalArgumentException("Type " + type + " is not a valid Sqlite type.");
	}

	public static String getIndexString(String indexName, boolean isPrimaryKey, int indexOrdinal) {
		String primaryKey = isPrimaryKey ? " PRIMARY KEY" : "";
		String indexString = "CONSTRAINT PK_" + indexName.toUpperCase() + "_" + indexOrdinal + primaryKey + " AUTOINCREMENT";
		return indexString;
	}

	public static <E extends IEntity> Cursor selectAll(SQLiteDatabase database, ColumnCollection<E> columns, String tableName) {
		Cursor c = database.query(tableName, columns.getColumnNames(), null, null, null, null, columns.getSortedColumnsAsString());
		return c;
	}

	public static <E extends IEntity> Cursor selectById(SQLiteDatabase database, ColumnCollection<E> columns, long id, String tableName) {
		Cursor c = database.query(tableName, columns.getColumnNames(), columns.PrimaryIdColumn.Name + "=?", new String[]{ String.valueOf(id) }, null, null, columns.getSortedColumnsAsString());
		return c;
	}
	
	public static <E extends IEntity> SQLiteStatement createUpdateStatement(SQLiteDatabase database, ColumnCollection<E> columns, String tableName)
	{
		String[] columnsWithoutIdColumn = Strings.removeOne(columns.PrimaryIdColumn.Name, columns.getColumnNames());
		
		String parameters = Strings.join(columnsWithoutIdColumn, "=?, ") + "=?";

		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ").append(tableName)
			.append(" SET ").append(parameters)
			.append(" WHERE ").append(columns.PrimaryIdColumn.Name).append("=?");
		
		String sql = sb.toString();

		SQLiteStatement statement = database.compileStatement(sql);
		return statement;
	}

	public static <E extends IEntity> SQLiteStatement createInsertStatement(SQLiteDatabase database, ColumnCollection<E> columns, String tableName)
	{
		String parameters = Strings.join("?", columns.count()-1, ", ");

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ").append(tableName)
			.append(" (").append(columns.getColumnNamesWithoutIdColumnAsString()).append(")")
			.append(" VALUES (").append(parameters).append(")");
		
		String sql = sb.toString();

		SQLiteStatement statement = database.compileStatement(sql);
		return statement;
	}

	public static boolean tableExists(SQLiteDatabase database, String tableName) {

		String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name=?";
		Cursor cursor = database.rawQuery(sql, new String[]{ tableName });
		
		boolean result = !TextUtils.isEmpty(getString(cursor));
		cursor.close();
		return result;
	}

	/**
	 * Returns a single value from a cursor as a boolean.
	 * @param cursor
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean getBoolean(Cursor cursor) {
		
		if(cursor != null){
			if (cursor.moveToFirst()){
				return cursor.getInt(0) != 0;
			}
		}
		return false;
	}

	/**
	 * Returns a single value from a cursor as a String.
	 * @param cursor
	 * @return
	 */
	private static String getString(Cursor cursor) {
		
		if(cursor != null){
			if (cursor.moveToFirst()){
				return cursor.getString(0);
			}
		}
		return "";
	}
}
