package se.kingharvest.punchclock.pages;

import se.kingharvest.infrastructure.ui.ActivityBase;
import se.kingharvest.infrastructure.ui.annotation.OnClick;
import se.kingharvest.punchclock.R;
import android.view.View;

public class EntriesPage extends ActivityBase<EntriesPage, EntriesViewModel>{

	public static final int SHOW_ENTRIES = 0;
	
	public int getContentView() {
		return R.layout.entries_page;
	}

	public EntriesViewModel createViewModel() {
		return new EntriesViewModel(this);
	}

	public void bindView() {
		getButton(R.id.entries_go_back)
			.setOnClick(onClick(R.id.entries_go_back));
	}

	public void bindViewModel() {
		// TODO Auto-generated method stub
		
	}
	
	@OnClick(R.id.entries_go_back)
	public void goBack(View v)
	{
		finishWithResult(35);
	}

}
