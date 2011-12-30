package se.kingharvest.punchclock;

import se.kingharvest.infrastructure.android.ParcelableCreator;
import se.kingharvest.infrastructure.model.ViewModelBase;
import se.kingharvest.infrastructure.property.StringProperty;
import se.kingharvest.infrastructure.time.TimeSpan;
import se.kingharvest.punchclock.entity.Project;
import se.kingharvest.punchclock.entity.WorkPeriod;
import se.kingharvest.punchclock.model.ProjectHandler;
import se.kingharvest.punchclock.model.PunchHandler;
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
    	_view.setState(PunchClockState.Stop);
	}
    
	public void setNewProject(Project newProject) {
		
		Project project = ProjectHandler.createProject(newProject);
		_currentProject = project;
		StatusText.set("Created new project " + project.Description);
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
			_view.setState(PunchClockState.Stop);
		}
		else
		{
			_view.setState(PunchClockState.Start);
			PunchHandler.startProject(_currentProject);
			StatusText.set("Working at job " + _currentProject.Description);
		}
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
