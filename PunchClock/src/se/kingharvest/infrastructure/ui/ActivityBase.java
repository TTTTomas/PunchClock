package se.kingharvest.infrastructure.ui;

import java.lang.reflect.Method;
import java.util.HashMap;

import se.kingharvest.infrastructure.diagnostics.Logger;
import se.kingharvest.infrastructure.model.IView;
import se.kingharvest.infrastructure.model.IViewModel;
import se.kingharvest.infrastructure.reflection.MethodReflect;
import se.kingharvest.infrastructure.ui.annotation.OnActivityResultAnnotation;
import se.kingharvest.infrastructure.ui.binding.ILayoutBinder;
import se.kingharvest.infrastructure.ui.binding.LayoutBinder;
import se.kingharvest.infrastructure.ui.dialog.DialogManager;
import se.kingharvest.infrastructure.ui.dialog.IDialogManager;
import se.kingharvest.infrastructure.ui.ex.ButtonEx;
import se.kingharvest.infrastructure.ui.ex.ListViewEx;
import se.kingharvest.infrastructure.ui.ex.SpinnerEx;
import se.kingharvest.infrastructure.ui.ex.TextViewEx;
import se.kingharvest.infrastructure.ui.ex.ViewEx;
import se.kingharvest.infrastructure.ui.navigation.INavigator;
import se.kingharvest.infrastructure.ui.navigation.Navigator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/**
 * This is a base class for an Activity that is paired with a view model. It also overrides a 
 * handful of common Activity methods and implements another handful of alternative helper methods.
 * You need to provide your subclass as a type argument to ActivityBase.
 * @author tomasb
 *
 * @param <V> The view - your Activity. 
 * @param <VM> The view model.
 */
@SuppressWarnings("rawtypes")
public abstract class ActivityBase</*V extends IView<VM>,*/ VM extends IViewModel> 
	extends Activity 
	implements ILayoutBinder, IView<VM>, INavigator, IDialogManager
{
	protected final String LOG_TAG = getClass().getSimpleName();
	
	protected VM _viewModel;
	
    /** Contains all methods annotated with OnActivityResult if the subclasser has not implemented onActivityResult. */
	protected HashMap<Integer, Method> _resultMethods;
	
	/**
	 * onCreate is called when the activity is created.
	 * It sets the content view (@see getContentView()) and creates an instance
	 * of the view model (@see createViewModel()) if necessary. Then it calls 
	 * bindView() (@see bindView()). 
	 * Subclassers will probably not need to override this method. 
	 */
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
		
		if(getIntent().getExtras() != null)
		{
        	Logger.write(LOG_TAG, "onCreate, getting parameters. ");
        	Navigator.callNavigationTarget(this);
		}
		
	    bindView();
	}
	
	/**
	 * onSaveInstanceState saves the view model to the stored state.
	 * Store any data you want to be saved on state change (such as device
	 * rotation or activity freeze) in the view model, and it will automatically
	 * be saved and restored.
	 * Subclassers will probably not need to override this method. 
	 */
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		// TODO: Should store view model in application state.
		// Writes the view model to the Bundle.
		VM viewModel = getViewModel();
    	Logger.write(LOG_TAG, "Saving instance state for " + viewModel.getClass().getSimpleName() + ".");
		outState.putParcelable(this.getClass().getSimpleName() + "_VIEWMODEL", viewModel);
	}
	
	/**
	 * onRestoreInstanceState restores the view model from the stored state.
	 * Store any data you want to be saved on state change (such as device
	 * rotation or activity freeze) in the view model, and it will automatically
	 * be saved and restored.
	 * Subclassers will probably not need to override this method. 
	 */
	@SuppressWarnings({ "unchecked" })
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		
		// Reads the view model from the saved state.
		VM viewModel = (VM) savedInstanceState.getParcelable(this.getClass().getSimpleName() + "_VIEWMODEL");
		//viewModel.setView(this);
    	Logger.write(LOG_TAG, "Restoring instance state for " + viewModel.getClass().getSimpleName() + ".");
		setViewModel(viewModel);
	}

	/**
	 * onActivityResult receives the result from any activity called from this activity.
	 * It then dispatches the result to any method annotated with OnActivityResult(requestCode).
	 * Therefore, to receive results, create a method with the signature method(int result, Intent data)
	 * and annotate it with OnActivityResult(requestCode). It will get results dispatched to it from
	 * here.
	 * Subclassers will probably not need to override this method. 
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

    	Method m = OnActivityResultAnnotation.getInstance().getAnnotatedMethod(this, requestCode);

        if(m != null)
        {
            Logger.write(LOG_TAG, "Getting activity results. Calling " + m.getName() + ". requestCode: " + requestCode + ", resultCode: " + resultCode);
        	MethodReflect.call(this, m, resultCode, data);
        }
        else
        {
        	Logger.write(LOG_TAG, "Getting activity results. No result method found. requestCode: " + requestCode + ", resultCode: " + resultCode);
        }
	}
	
	/**
	 * onActivityResult is called when the activity is destroyed.
	 * Here all dialogs managed by this activity are dismissed to
	 * prevent any leaked dialogs.
	 * @see Activity#onDestroy
	 */
	@Override
	protected void onDestroy() {
		
		_dialogManager.onDestroy();
		super.onDestroy();
	}
	
	/**
	 * Returns the root layout of this Activity.
	 * @return
	 */
	public ViewGroup getLayout()
	{
		ViewGroup layout = ((ViewGroup)findViewById(getContentView()));
		return layout;
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

	public ListViewEx getListView(int id) {
		return LayoutBinder.getListView(this, id);
	}

	public View.OnClickListener onClick(int id){
		return LayoutBinder.getOnClick(this, id);
	}
	
	/* IDialogManager */

	DialogManager<VM> _dialogManager = new DialogManager<VM>(this);

	public <D extends DialogBase> void showDialog(D dialog) {
		_dialogManager.showDialog(dialog);
	}
	
	/* INavigator */
	
	public <A extends Activity> void navigateTo(Class<A> targetPage) {
        Intent intent = new Intent(this, targetPage);
        startActivity(intent);
	}

	public <A extends Activity> void navigateForResult(Class<A> targetPage, int requestCode) {
        Intent intent = new Intent(this, targetPage);
        startActivityForResult(intent, requestCode);
	}
	
	public void finishWithResult(int resultCode)
	{
		setResult(resultCode);
		finish();
	}

	public <A extends Activity> void navigateTo(Class<A> targetPage, int targetMethod, Object ... args)
	{
		Navigator.navigateTo(this, targetPage, targetMethod, args);
	}

//	public <A extends Activity, A1> void navigateTo(Class<A> targetClass, int targetMethod, A1 arg1)
//	{
//		Navigator.navigateTo(this, targetClass, targetMethod, arg1);
//	}
//
//	public <A extends Activity, A1, A2> void navigateTo(Class<A> targetClass, int targetMethod, A1 arg1, A2 arg2)
//	{
//		Navigator.navigateTo(this, targetClass, targetMethod, arg1, arg2);
//	}
//
//	public <A extends Activity, A1, A2, A3> void navigateTo(Class<A> targetClass, int targetMethod, A1 arg1, A2 arg2, A3 arg3)
//	{
//		Navigator.navigateTo(this, targetClass, targetMethod, arg1, arg2, arg3);
//	}
//
//	public <A extends Activity, A1, A2, A3, A4> void navigateTo(Class<A> targetClass, int targetMethod, A1 arg1, A2 arg2, A3 arg3, A4 arg4)
//	{
//		Navigator.navigateTo(this, targetClass, targetMethod, arg1, arg2, arg3, arg4);
//	}
//
//	public <A extends Activity, A1, A2, A3, A4, A5> void navigateTo(Class<A> targetClass, int targetMethod, A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5)
//	{
//		Navigator.navigateTo(this, targetClass, targetMethod, arg1, arg2, arg3, arg4, arg5);
//	}
//
//	public <A extends Activity, A1, A2, A3, A4, A5, A6> void navigateTo(Class<A> targetClass, int targetMethod, A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5, A6 arg6)
//	{
//		Navigator.navigateTo(this, targetClass, targetMethod, arg1, arg2, arg3, arg4, arg5, arg6);
//	}
}
