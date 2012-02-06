package se.kingharvest.punchclock.application;

import se.kingharvest.infrastructure.model.IViewModel;
import se.kingharvest.punchclock.data.PunchClockDatabase;
import android.app.Application;

public class ClientContext extends Application{

	public static String 	PackageName;
	public static Class<?> 	IdResourceClass;

	private static ClientContext _instance;
	
	private static PunchClockDatabase _database;
	
	public static ClientContext getInstance(){
		if(_instance == null)
			_instance = new ClientContext();
		
		return _instance;
	}
	
	public void onCreate() {
		PackageName = getApplicationContext().getPackageName();
		try {
			IdResourceClass = Class.forName(PackageName + ".R$id");
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Failed to look up application resource class " + PackageName + ".R", e);
		}

			_database = new PunchClockDatabase(this);
	};
	
	public PunchClockDatabase getDatabase(){
		return _database;
	}
	
	public <VM extends IViewModel> VM getViewModel()
	{
		return null;
	}
}
