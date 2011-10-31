package se.kingharvest.infrastructure.ui.ex;

import android.view.View;

public class ViewEx extends ViewExBase<View, ViewEx>{
	
    private static final ThreadLocal<ViewEx> _instance = new ThreadLocal<ViewEx>()
	{
		@Override 
		protected ViewEx initialValue() {
            return new ViewEx();
		}
    };

	public static ViewEx getInstance() {
		return _instance.get();
	}

}
