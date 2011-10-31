package se.kingharvest.infrastructure.ui;

import se.kingharvest.infrastructure.diagnostics.Logger;
import se.kingharvest.infrastructure.model.IView;
import se.kingharvest.infrastructure.model.IViewModel;
import se.kingharvest.infrastructure.ui.binder.ILayoutBinder;
import se.kingharvest.infrastructure.ui.binder.LayoutBinder;
import se.kingharvest.infrastructure.ui.ex.ButtonEx;
import se.kingharvest.infrastructure.ui.ex.SpinnerEx;
import se.kingharvest.infrastructure.ui.ex.TextViewEx;
import se.kingharvest.infrastructure.ui.ex.ViewEx;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

@SuppressWarnings("rawtypes")
public abstract class ActivityBase<V extends IView<?>, VM extends IViewModel> extends Activity implements ILayoutBinder, IView<VM>
{	
	private final String LOG_TAG = getClass().getSimpleName();
	
	protected VM _viewModel;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentView());

		if (savedInstanceState == null) {
	    	Logger.write(LOG_TAG, "onCreate, creating new. ");
			setViewModel(createViewModel());
        } else {
        	Logger.write(LOG_TAG, "onCreate, restoring. ");
        }
		
	    bindView();
	}
	
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		VM viewModel = getViewModel();
    	Logger.write(LOG_TAG, "Saving instance state for " + viewModel.getClass().getSimpleName() + ".");
		outState.putParcelable(this.getClass().getSimpleName() + "_VIEWMODEL", viewModel);
	}
	
	@SuppressWarnings({ "unchecked" })
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		VM viewModel = (VM) savedInstanceState.getParcelable(this.getClass().getSimpleName() + "_VIEWMODEL");
		viewModel.setView(this);
    	Logger.write(LOG_TAG, "Restoring instance state for " + viewModel.getClass().getSimpleName() + ".");
		setViewModel(viewModel);
	}

	/* IView */

	public void setViewModel(VM viewModel) {
		_viewModel = viewModel;
		bindViewModel();
	}
	
	public VM getViewModel() {
		return _viewModel;
	}

	/* ILayoutBinder */
	
	public ViewEx getView(int id) {
		return LayoutBinder.getView(this, id);
	}

	public TextViewEx getTextView(int id) {
		return LayoutBinder.getTextView(this, id);
	}

	public ButtonEx getButton(int id) {
		return LayoutBinder.getButton(this, id);
	}

	public SpinnerEx getSpinner(int id) {
		return LayoutBinder.getSpinner(this, id);
	}

	public View.OnClickListener onClick(int id)
	{
		return LayoutBinder.getOnClick(this, id);
	}
}
