package se.kingharvest.punchclock;

import android.os.Parcelable;
import se.kingharvest.infrastructure.android.ParcelableCreator;
import se.kingharvest.infrastructure.model.ViewModelBase;
import se.kingharvest.infrastructure.property.StringProperty;

public class PunchClockViewModel extends ViewModelBase<PunchClockActivity, PunchClockViewModel>
{
	private static final long serialVersionUID = 241804889221796408L;

	int _counter;

	public StringProperty CounterText = new StringProperty();

	public PunchClockViewModel(PunchClockActivity view) {
    	super(view);
	}
    
    public void setCounter(int i)
    {
    	_counter = i;
    	CounterText.set("The number is: " + _counter);
    }

    public int getCounter()
    {
    	return _counter;
    }
    
    public static final Parcelable.Creator<PunchClockViewModel> CREATOR = new ParcelableCreator<PunchClockViewModel>(PunchClockViewModel.class).getSerializingCreator();
    
}
