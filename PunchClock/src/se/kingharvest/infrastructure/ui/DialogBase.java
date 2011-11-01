package se.kingharvest.infrastructure.ui;

import se.kingharvest.infrastructure.ui.binder.ILayoutBinder;
import se.kingharvest.infrastructure.ui.binder.LayoutBinder;
import se.kingharvest.infrastructure.ui.ex.ButtonEx;
import se.kingharvest.infrastructure.ui.ex.SpinnerEx;
import se.kingharvest.infrastructure.ui.ex.TextViewEx;
import se.kingharvest.infrastructure.ui.ex.ViewEx;
import android.app.Dialog;
import android.view.View;

public  class DialogBase extends Dialog implements ILayoutBinder{
	
	public DialogBase(ActivityBase<?,?> context) {
		super(context);
		context.manageDialog(this);
	}

	public ViewEx getView(int id) {
		return LayoutBinder.getView(this, id);
	}

	public ButtonEx getButton(int id) {
		return LayoutBinder.getButton(this, id);
	}

	public SpinnerEx getSpinner(int id) {
		return LayoutBinder.getSpinner(this, id);
	}

	/**
	 * Returns an OnClickListener calling the method annotated with 
	 * OnClick(id)
	 * @param id
	 * @return
	 */
	public View.OnClickListener onClick(int id)
	{
		return LayoutBinder.getOnClick(this, id);
	}

	public int getContentView() {
		// TODO Auto-generated method stub
		return 0;
	}

	public TextViewEx getTextView(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
