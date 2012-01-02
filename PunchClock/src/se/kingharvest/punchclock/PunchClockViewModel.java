package se.kingharvest.punchclock;

import se.kingharvest.infrastructure.android.ParcelableCreator;
import se.kingharvest.infrastructure.model.ViewModelBase;
import se.kingharvest.infrastructure.property.StringProperty;
import se.kingharvest.infrastructure.time.TimeSpan;
import se.kingharvest.punchclock.data.handlers.ProjectHandler;
import se.kingharvest.punchclock.data.handlers.PunchHandler;
import se.kingharvest.punchclock.entity.Project;
import se.kingharvest.punchclock.entity.WorkPeriod;
import android.os.Parcelable;

public class PunchClockViewModel extends ViewModelBase<PunchClockActivity, PunchClockViewModel>
{
	public enum PunchClockState
	{
		Start,
		Stop,
	}
	
	private static final long serialVersionUID = 241804889221796408L;

	public StringProperty StatusText = new StringProperty();

	private Project _currentProject;
	
	public PunchClockViewModel(PunchClockActivity view) {
    	super(view);
    	updateState();
	}
    
	private void updateState()
	{
		WorkPeriod periodInProgress = PunchHandler.getPeriodInProgress();
		if(periodInProgress == null)
		{
	    	_view.setState(PunchClockState.Stop);
		}
		else
		{
			_currentProject = ProjectHandler.getProject(periodInProgress.ProjectId);
	    	_view.setState(PunchClockState.Start);
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
			_view.askForJob();
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



	public static final Parcelable.Creator<PunchClockViewModel> CREATOR = new ParcelableCreator<PunchClockViewModel>(PunchClockViewModel.class).getSerializingCreator();

}
