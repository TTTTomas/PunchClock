package se.kingharvest.infrastructure.ui;

import se.kingharvest.infrastructure.model.IViewModel;
import se.kingharvest.infrastructure.model.ViewModelBase;
import se.kingharvest.infrastructure.ui.binding.ILayoutBinder;
import se.kingharvest.infrastructure.ui.binding.LayoutBinder;
import se.kingharvest.infrastructure.ui.ex.ButtonEx;
import se.kingharvest.infrastructure.ui.ex.ListViewEx;
import se.kingharvest.infrastructure.ui.ex.SpinnerEx;
import se.kingharvest.infrastructure.ui.ex.TextViewEx;
import se.kingharvest.infrastructure.ui.ex.ViewEx;
import android.app.Dialog;
import android.view.View;

public class DialogBase<VM extends ViewModelBase</*IView<?>, */IViewModel/*<?>*/>> extends Dialog implements ILayoutBinder{
	
	public DialogBase(ActivityBase<?> context) {
		super(context);
		setContentView(getContentView());
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

	public TextViewEx getTextView(int id) {
		return LayoutBinder.getTextView(this, id);
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

	public ListViewEx getListView(int id) {
		return LayoutBinder.getListView(this, id);
	}
}
