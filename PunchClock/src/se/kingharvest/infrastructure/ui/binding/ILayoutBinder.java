package se.kingharvest.infrastructure.ui.binding;

import android.view.View;
import se.kingharvest.infrastructure.ui.ex.ButtonEx;
import se.kingharvest.infrastructure.ui.ex.SpinnerEx;
import se.kingharvest.infrastructure.ui.ex.TextViewEx;
import se.kingharvest.infrastructure.ui.ex.ViewEx;

public interface ILayoutBinder {

	int getContentView();
	
	ViewEx getView(int id);
	TextViewEx getTextView(int id);
	ButtonEx getButton(int id);
	SpinnerEx getSpinner(int id);

	/**
	 * Returns an OnClickListener calling the method annotated with 
	 * OnClick(id)
	 * @param id
	 * @return
	 */
	public View.OnClickListener onClick(int id);
}
