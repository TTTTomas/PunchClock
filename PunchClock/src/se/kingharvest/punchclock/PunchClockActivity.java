package se.kingharvest.punchclock;

import se.kingharvest.infrastructure.ui.ActivityBase;
import se.kingharvest.infrastructure.ui.annotation.OnClick;
import se.kingharvest.infrastructure.ui.annotation.OnCreateDialog;
import se.kingharvest.punchclock.PunchClockViewModel.IPunchClockDialogService;
import se.kingharvest.punchclock.dialogs.NewProjectDialog;
import se.kingharvest.punchclock.dialogs.NewProjectDialog.OnNewProjectOkListener;
import se.kingharvest.punchclock.entity.Project;
import se.kingharvest.punchclock.pages.EntriesPage;
import android.view.View;


public class PunchClockActivity extends ActivityBase</*PunchClockActivity,*/ PunchClockViewModel> implements IPunchClockDialogService{
    	
	public int getContentView() {
		return R.layout.punchclock_page;
	}
	
	public PunchClockViewModel createViewModel() {
		// TODO: Should get view model from a factory or registry.
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
        
        getButton(R.id.Punchclock_EntriesButton)
    		.bindOnClick(this);        
    }

	public void bindViewModel() 
	{
		getTextView(R.id.punchclock_status_text)
			.bindText(_viewModel.StatusText);
		
		getButton(R.id.Punchclock_StartButton)
			.bindText(_viewModel.StartButtonText);
		
		getButton(R.id.Punchclock_BreakButton)
			.bindEnabled(_viewModel.BreakButtonEnabled);
	}
	
	@OnClick(R.id.Punchclock_StartButton)
	public void onStartClicked(View button)
	{
		_viewModel.startOrStopCurrentProject();
	}

	@OnClick(R.id.Punchclock_BreakButton)
	public void onBreakClicked(View v)
	{
		_viewModel.breakCurrentJob();
	}
	
	@OnClick(R.id.Punchclock_EntriesButton)
	public void onEntriesClicked(View v)
	{
		navigateTo(EntriesPage.class);
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
	
	public void showAskForJob() {
		
		NewProjectDialog dialog = createNewProjectDialog();
		showDialog(dialog);
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
