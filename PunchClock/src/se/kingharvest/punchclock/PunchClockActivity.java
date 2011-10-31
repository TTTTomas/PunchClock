package se.kingharvest.punchclock;

import se.kingharvest.infrastructure.ui.ActivityBase;
import se.kingharvest.infrastructure.ui.annotation.OnActivityResult;
import se.kingharvest.infrastructure.ui.annotation.OnClick;
import se.kingharvest.punchclock.pages.EntriesPage;
import android.content.Intent;
import android.view.View;


public class PunchClockActivity extends ActivityBase<PunchClockActivity, PunchClockViewModel> {
    
	@SuppressWarnings("unused")
	private static final String LOG_TAG = PunchClockActivity.class.getSimpleName();
	
	public int getContentView() {
		return R.layout.main;
	}

	public PunchClockViewModel createViewModel() {
		return new PunchClockViewModel(this);
	}

	public void bindView()
    {
        getButton(R.id.punchclock_button)
        	.setOnClick(onClick(R.id.punchclock_button));

        getButton(R.id.punchclock_go_to_entries)
        	.setOnClick(onClick(R.id.punchclock_go_to_entries));
    }

	public void bindViewModel() 
	{
        getTextView(R.id.punchclock_text_property)
        	.bindText(_viewModel.CounterText);

        getTextView(R.id.punchclock_just_text)
        	.setText("Just text.");
	}	
	
	@OnClick(R.id.punchclock_button)
	public void punch(View button)
	{
		_viewModel.setCounter(_viewModel.getCounter()+1);
	}

	@OnClick(R.id.punchclock_go_to_entries)
	public void goToEntries(View button)
	{
		navigateForResult(EntriesPage.class, EntriesPage.SHOW_ENTRIES);
	}
	
	@OnActivityResult(EntriesPage.SHOW_ENTRIES)
	public void returnFromEntries(int resultCode, Intent result)
	{
		_viewModel.setCounter(_viewModel.getCounter()+resultCode);
	}
	
}
