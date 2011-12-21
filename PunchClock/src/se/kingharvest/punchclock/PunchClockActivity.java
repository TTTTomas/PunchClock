package se.kingharvest.punchclock;

import se.kingharvest.infrastructure.ui.ActivityBase;
import se.kingharvest.infrastructure.ui.annotation.OnClick;
import se.kingharvest.punchclock.dialogs.AskForJobDialog;
import se.kingharvest.punchclock.dialogs.AskForJobDialog.OnNewJobOkListener;
import android.view.View;


public class PunchClockActivity extends ActivityBase<PunchClockActivity, PunchClockViewModel> {
    
	@SuppressWarnings("unused")
	private static final String LOG_TAG = PunchClockActivity.class.getSimpleName();
	
	public int getContentView() {
		return R.layout.punchclock_page;
	}
	
	public PunchClockViewModel createViewModel() {
		return new PunchClockViewModel(this);
	}

	public void bindView()
    {		
        getButton(R.id.punchclock_start_button)
    		.bindOnClick(this);

        getButton(R.id.punchclock_break_button)
    		.bindOnClick(this);
    }

	public void bindViewModel() 
	{
		getButton(R.id.punchclock_start_button)
        	.bindText(_viewModel.StartButtonText);

		getTextView(R.id.punchclock_status_text)
        	.bindText(_viewModel.StatusText);
	}	
	
	@OnClick(R.id.punchclock_start_button)
	public void startClicked(View button)
	{
		_viewModel.startOrStopCurrentProject();
	}

	@OnClick(R.id.punchclock_break_button)
	public void breakClicked(View v)
	{
		_viewModel.breakCurrentJob();
	}

	public void askForJob() {
		
		AskForJobDialog dialog = new AskForJobDialog(this, onNewJobOk());
		dialog.show();
	}

	private OnNewJobOkListener onNewJobOk() {
		
		return null;
	}
	
//	@OnClick(R.id.punchclock_go_to_entries)
//	public void goToEntries(View button)
//	{
//		navigateForResult(EntriesPage.class, EntriesPage.SHOW_ENTRIES);
//	}
//	
//	@OnClick(R.id.punchclock_go_to_entries_with_arguments)
//	public void goToEntriesWithArguments(View button)
//	{
//		navigateTo(EntriesPage.class, EntriesPage.startWithArguments, "Hello", null, 4);
//	}
//
//	@OnActivityResult(EntriesPage.SHOW_ENTRIES)
//	public void returnFromEntries(int resultCode, Intent result)
//	{
//		_viewModel.setCounter(_viewModel.getCounter()+resultCode);
//	}
	
}
