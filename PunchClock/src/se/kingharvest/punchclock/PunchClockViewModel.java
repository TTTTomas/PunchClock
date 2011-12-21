package se.kingharvest.punchclock;

import se.kingharvest.infrastructure.android.ParcelableCreator;
import se.kingharvest.infrastructure.model.ViewModelBase;
import se.kingharvest.infrastructure.property.StringProperty;
import se.kingharvest.infrastructure.time.TimeSpan;
import se.kingharvest.punchclock.entity.Project;
import se.kingharvest.punchclock.entity.WorkPeriod;
import se.kingharvest.punchclock.model.PunchHandler;
import android.os.Parcelable;

public class PunchClockViewModel extends ViewModelBase<PunchClockActivity, PunchClockViewModel>
{
	private static final long serialVersionUID = 241804889221796408L;

	public StringProperty StartButtonText = new StringProperty();

	public StringProperty StatusText = new StringProperty();

	private Project _currentProject;
	
	public PunchClockViewModel(PunchClockActivity view) {
    	super(view);
	}
    
	public void startOrStopCurrentProject() {
		
		if(_currentProject == null)
		{
			StartButtonText.set("Start");
			_view.askForJob();
			return;
		}
		
		WorkPeriod periodInProgress = PunchHandler.getPeriodInProgress();
		if(periodInProgress != null)
		{
			PunchHandler.stopPeriod(periodInProgress);
			StartButtonText.set("Start");
		}
		else
		{
			PunchHandler.startProject(_currentProject);
			StartButtonText.set("Stop");
		}
	}
		
	public void breakCurrentJob() {
		
		WorkPeriod periodInProgress = PunchHandler.getPeriodInProgress();
		if(periodInProgress != null)
		{
			TimeSpan breakTime = PunchHandler.getBreakTime(_currentProject);
			PunchHandler.insertBreakInPeriod(periodInProgress, breakTime);
		}
	}



	public static final Parcelable.Creator<PunchClockViewModel> CREATOR = new ParcelableCreator<PunchClockViewModel>(PunchClockViewModel.class).getSerializingCreator();

}
