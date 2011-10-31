package se.kingharvest.infrastructure.ui;

import java.lang.reflect.Method;
import java.util.HashMap;

import se.kingharvest.infrastructure.diagnostics.Logger;
import se.kingharvest.infrastructure.model.IView;
import se.kingharvest.infrastructure.model.IViewModel;
import se.kingharvest.infrastructure.system.Reflect;
import se.kingharvest.infrastructure.ui.annotation.OnActivityResultAnnotation;
import se.kingharvest.infrastructure.ui.binder.ILayoutBinder;
import se.kingharvest.infrastructure.ui.binder.LayoutBinder;
import se.kingharvest.infrastructure.ui.ex.ButtonEx;
import se.kingharvest.infrastructure.ui.ex.SpinnerEx;
import se.kingharvest.infrastructure.ui.ex.TextViewEx;
import se.kingharvest.infrastructure.ui.ex.ViewEx;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

@SuppressWarnings("rawtypes")
public abstract class ActivityBase<V extends IView<?>, VM extends IViewModel> 
	extends Activity 
	implements ILayoutBinder, IView<VM>, INavigator
{	
	private final String LOG_TAG = getClass().getSimpleName();
	
	protected VM _viewModel;

	/** Contains all methods annotated with OnActivityResult if the subclasser has not implemented onActivityResult. */
	protected HashMap<Integer, Method> _resultMethods;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentView());

		// If creating from scratch, create a fresh view model.
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
		
		// Writes the view model to the Bundle.
		VM viewModel = getViewModel();
    	Logger.write(LOG_TAG, "Saving instance state for " + viewModel.getClass().getSimpleName() + ".");
		outState.putParcelable(this.getClass().getSimpleName() + "_VIEWMODEL", viewModel);
	}
	
	@SuppressWarnings({ "unchecked" })
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		
		// Reads the view model from the saved state.
		VM viewModel = (VM) savedInstanceState.getParcelable(this.getClass().getSimpleName() + "_VIEWMODEL");
		viewModel.setView(this);
    	Logger.write(LOG_TAG, "Restoring instance state for " + viewModel.getClass().getSimpleName() + ".");
		setViewModel(viewModel);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// Finds all methods annotated with OnActivityResult.
        if(_resultMethods == null)
        {
            Logger.write(LOG_TAG, "Reading all OnActivityResult annotations.");
        	_resultMethods = OnActivityResultAnnotation.getAnnotatedMethods(this);
        }
        
        // Dispatches the result to a method annotated with the correct requestCode.
        if(_resultMethods != null && _resultMethods.containsKey(requestCode))
        {
        	Method m = _resultMethods.get(requestCode);
            Logger.write(LOG_TAG, "Getting activity results. Calling " + m.getName() + ". requestCode: " + requestCode + ", resultCode: " + resultCode);
        	Reflect.call(this, m, resultCode, data);
        }
        else
        {
        	Logger.write(LOG_TAG, "Getting activity results. No result method found. requestCode: " + requestCode + ", resultCode: " + resultCode);
        }
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

	public View.OnClickListener onClick(int id){
		return LayoutBinder.getOnClick(this, id);
	}
	
	/* INavigator */
	
	public <A extends Activity> void navigateTo(Class<A> pageType) {
        Intent intent = new Intent(this, pageType);
        startActivity(intent);
	}

	public <A extends Activity> void navigateForResult(Class<A> pageType, int requestCode) {
        Intent intent = new Intent(this, pageType);
        startActivityForResult(intent, requestCode);
	}
	
	public void finishWithResult(int resultCode)
	{
		setResult(resultCode);
		finish();
	}
}
