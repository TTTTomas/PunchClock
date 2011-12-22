package se.kingharvest.punchclock.application;

import se.kingharvest.infrastructure.data.TableFactory;
import android.app.Application;

public class ClientContext extends Application{

	private static ClientContext _instance;
	
	private static PunchClockDatabase _database;
	
	public static ClientContext getInstance(){

		if(_instance == null)
			_instance = new ClientContext();
		
		return _instance;
	}
	
	public void onCreate() {
		_database = new PunchClockDatabase();
		TableFactory.getInstance().setDatabase(_database.getSQLiteDatabase());
	};
	
	public PunchClockDatabase getDatabase(){
		return _database;
	}
}
