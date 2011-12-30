package se.kingharvest.punchclock;

import se.kingharvest.infrastructure.ui.ActivityBase;
import se.kingharvest.infrastructure.ui.annotation.OnClick;
import se.kingharvest.punchclock.PunchClockViewModel.PunchClockState;
import se.kingharvest.punchclock.dialogs.NewProjectDialog;
import se.kingharvest.punchclock.dialogs.NewProjectDialog.OnNewProjectOkListener;
import se.kingharvest.punchclock.entity.Project;
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
        getButton(R.id.Punchclock_StartButton)
    		.bindOnClick(this);

        getButton(R.id.Punchclock_BreakButton)
    		.bindOnClick(this);
    }

	public void bindViewModel() 
	{
		getTextView(R.id.punchclock_status_text)
        	.bindText(_viewModel.StatusText);
	}	
	
	@OnClick(R.id.Punchclock_StartButton)
	public void startClicked(View button)
	{
		_viewModel.startOrStopCurrentProject();
	}

	@OnClick(R.id.Punchclock_BreakButton)
	public void breakClicked(View v)
	{
		_viewModel.breakCurrentJob();
	}

	public void setState(PunchClockState state) {
		
		switch (state) {
		case Start:
			getButton(R.id.Punchclock_StartButton).setText("Stop");
			getButton(R.id.Punchclock_BreakButton).setEnabled(false);
			break;

		case Stop:
			getButton(R.id.Punchclock_StartButton).setText("Start");
			getButton(R.id.Punchclock_BreakButton).setEnabled(true);
			break;

		}
		// TODO Auto-generated method stub	
	}
	
	public void askForJob() {
		
		NewProjectDialog dialog = new NewProjectDialog(this);
		showDialog(dialog);
	}

	private OnNewProjectOkListener onNewProjectOk() {
		
		return new OnNewProjectOkListener() {
			public void newProjectOk(Project newProject) {
				_viewModel.setNewProject(newProject);
			}
		};
	}
	
	public void prepareDialog(NewProjectDialog dialog)
	{
		dialog.setOnNewJobOkListener(onNewProjectOk());
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
