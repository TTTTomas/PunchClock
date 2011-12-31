package se.kingharvest.infrastructure.data;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper
{
	private ArrayList<ITable<?>> _tables = new ArrayList<ITable<?>>();
	
	public Database(Context context, String name, int version) {
		super(context, name, null, version);

		TableFactory.getInstance().setDatabase(this);
	}

	public void addTable(ITable<?> table)
	{
		_tables.add(table);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	public void createTablesIfNeeded() {
		
		for (int i = 0; i < _tables.size(); i++) {
			_tables.get(i).createTableIfNeeded();
		}
		
	}
}
