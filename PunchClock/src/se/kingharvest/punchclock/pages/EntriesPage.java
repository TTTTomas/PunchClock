package se.kingharvest.punchclock.pages;

import se.kingharvest.infrastructure.diagnostics.Logger;
import se.kingharvest.infrastructure.ui.ActivityBase;
import se.kingharvest.infrastructure.ui.annotation.NavigateTo;
import se.kingharvest.infrastructure.ui.annotation.OnClick;
import se.kingharvest.punchclock.R;
import android.view.View;

public class EntriesPage extends ActivityBase<EntriesPage, EntriesViewModel>{

	public static final int SHOW_ENTRIES = 0;
	public static final int startWithArguments = 1;
	private static final String LOG_TAG = null;
	
	public int getContentView() {
		return R.layout.entries_page;
	}

	public EntriesViewModel createViewModel() {
		return new EntriesViewModel(this);
	}

	public void bindView() {
		getButton(R.id.entries_go_back)
			.bindOnClick(this);
	}

	public void bindViewModel() {
		// TODO Auto-generated method stub
		
	}
	
	int i=2;
	
	@OnClick(R.id.entries_go_back)
	public void goBack(View v)
	{
		finishWithResult(i);
	}
	
	@NavigateTo(startWithArguments)
	void startWithArguments(String arg1, String arg2, long arg3)
	{
    	Logger.write(LOG_TAG, "startWithArguments: " + arg1 + ", arg2: " + arg2 + ", arg3: " + arg3);
    	i = (int) arg3;
	}

}
