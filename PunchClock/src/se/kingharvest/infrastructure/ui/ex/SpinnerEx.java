package se.kingharvest.infrastructure.ui.ex;

import android.widget.Spinner;

public class SpinnerEx extends ViewExBase<Spinner, SpinnerEx> {
	
    private static final ThreadLocal<SpinnerEx> _instance = new ThreadLocal<SpinnerEx>()
		{
			@Override 
			protected SpinnerEx initialValue() {
                return new SpinnerEx();
			}
        };
	
	public static SpinnerEx getInstance() {
		return _instance.get();
	}

}
