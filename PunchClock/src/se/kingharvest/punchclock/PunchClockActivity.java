package se.kingharvest.punchclock;

import java.util.HashMap;
import java.util.Map;

import se.kingharvest.infrastructure.ui.ActivityBase;
import se.kingharvest.infrastructure.ui.annotation.OnClick;
import se.kingharvest.infrastructure.ui.annotation.OnCreateDialog;
import se.kingharvest.punchclock.PunchClockViewModel.PunchClockState;
import se.kingharvest.punchclock.dialogs.NewProjectDialog;
import se.kingharvest.punchclock.dialogs.NewProjectDialog.OnNewProjectOkListener;
import se.kingharvest.punchclock.entity.Project;
import android.app.Dialog;
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
		//PerformanceTester.testAnnotations(this);
		//PerformanceTester.testMethodLookup(this);
		//PerformanceTester.testSingleMethodLookup(this);
		//PerformanceTester.testSingleMethodCall(this);
		//PerformanceTester.testSingleMethodCallWithArgs(this);
		//PerformanceTester.testSingleMethodCallCached(this);
		
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
	
	public static int _counter = 0;
	public void countUp()
	{
		Map<Integer, String> map = new HashMap<Integer, String>();
		_counter++;
		map.put(_counter, "Hello");
		//Logger.write(LOG_TAG, "Counter: " + _counter);
	}

	public void countUp(int up, int down, String bla, String blo)
	{
		Map<Integer, String> map = new HashMap<Integer, String>();
		_counter += up;
		map.put(_counter, "Hello");
		//Logger.write(LOG_TAG, "Counter: " + _counter);
	}

	public void setState(PunchClockState state) {
		
		switch (state) {
		case Start:
			getButton(R.id.Punchclock_StartButton).setText("Stop");
			getButton(R.id.Punchclock_BreakButton).setEnabled(true);
			break;

		case Stop:
			getButton(R.id.Punchclock_StartButton).setText("Start");
			getButton(R.id.Punchclock_BreakButton).setEnabled(false);
			break;

		}
		// TODO Auto-generated method stub	
	}
	
	public void askForJob() {
		
		NewProjectDialog dialog = createNewProjectDialog();
		showDialog(dialog);
	}

	private OnNewProjectOkListener onNewProjectOk() {
		
		return new OnNewProjectOkListener() {
			public void newProjectOk(Project newProject) {
				_viewModel.setNewProject(newProject);
				_viewModel.startOrStopCurrentProject();
			}
		};
	}
	
	@OnCreateDialog
	public NewProjectDialog createNewProjectDialog()
	{
		NewProjectDialog dialog = new NewProjectDialog(this);
		return dialog;
	}
	
	public void prepareDialog(NewProjectDialog dialog)
	{
		dialog.setOnNewJobOkListener(onNewProjectOk());
	}

	public void manageDialog(Dialog dialog) {
		// TODO Auto-generated method stub
		
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
