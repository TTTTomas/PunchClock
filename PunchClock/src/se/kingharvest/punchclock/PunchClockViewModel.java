package se.kingharvest.punchclock;

import se.kingharvest.infrastructure.android.ParcelableCreator;
import se.kingharvest.infrastructure.model.ViewModelBase;
import se.kingharvest.infrastructure.property.BooleanProperty;
import se.kingharvest.infrastructure.property.StringProperty;
import se.kingharvest.infrastructure.time.TimeSpan;
import se.kingharvest.punchclock.data.handlers.ProjectHandler;
import se.kingharvest.punchclock.data.handlers.PunchHandler;
import se.kingharvest.punchclock.entity.Project;
import se.kingharvest.punchclock.entity.WorkPeriod;
import android.os.Parcelable;

public class PunchClockViewModel extends ViewModelBase</*PunchClockActivity,*/ PunchClockViewModel>
{
	public interface IPunchClockDialogService
	{
		void showAskForJob();
	}
	
	public enum PunchClockState
	{
		Start,
		Stop,
	}
	
	private static final long serialVersionUID = 241804889221796408L;

	public StringProperty		StartButtonText = new StringProperty();
	public BooleanProperty		StartButtonEnabled = new BooleanProperty();
	
	public StringProperty		BreakButtonText = new StringProperty();
	public BooleanProperty		BreakButtonEnabled = new BooleanProperty();

	public StringProperty		StatusText = new StringProperty();

	transient private IPunchClockDialogService _dialogService;
	
	private Project _currentProject;
	
	public PunchClockViewModel(IPunchClockDialogService dialogService) {
		_dialogService = dialogService;
    	updateState();
	}
    
	private void updateState()
	{
		WorkPeriod periodInProgress = PunchHandler.getPeriodInProgress();
		if(periodInProgress == null)
		{
			setState(PunchClockState.Stop);
		}
		else
		{
			_currentProject = ProjectHandler.getProject(periodInProgress.ProjectId);
			setState(PunchClockState.Start);
		}			
	}
	
	public void setNewProject(Project newProject) {
		
		Project project = ProjectHandler.createProject(newProject);
		_currentProject = project;
		StatusText.set("Created new project " + project.Name);
	}

	public void startOrStopCurrentProject() {
		
		if(_currentProject == null)
		{
			StatusText.set("");
			_dialogService.showAskForJob();
			return;
		}
		
		WorkPeriod periodInProgress = PunchHandler.getPeriodInProgress();
		if(periodInProgress != null)
		{
			PunchHandler.stopPeriod(periodInProgress);
			StatusText.set("Stopped period " + periodInProgress.Id);
		}
		else
		{
			PunchHandler.startProject(_currentProject);
			StatusText.set("Working at job " + _currentProject.Description);
		}
		updateState();
	}
		
	public void breakCurrentJob() {
		
		WorkPeriod periodInProgress = PunchHandler.getPeriodInProgress();
		Project projectInProgress = PunchHandler.getProject(periodInProgress.ProjectId);
		if(periodInProgress != null)
		{
			PunchHandler.insertBreakInPeriod(periodInProgress, projectInProgress.BreakTime);
		}
		StatusText.set("Take a break: " + new TimeSpan(projectInProgress.BreakTime));
	}

	private void setState(PunchClockState state) {
		
		switch (state) {
		case Start:
			StartButtonText.set("Stop");
			BreakButtonEnabled.set(Boolean.TRUE);
			break;

		case Stop:
			StartButtonText.set("Start");
			BreakButtonEnabled.set(Boolean.FALSE);
			break;
		}
	}	


	public static final Parcelable.Creator<PunchClockViewModel> CREATOR = new ParcelableCreator<PunchClockViewModel>(PunchClockViewModel.class).getSerializingCreator();

}
