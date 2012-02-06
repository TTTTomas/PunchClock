package se.kingharvest.infrastructure.ui.dialog;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import se.kingharvest.infrastructure.property.BooleanProperty;
import se.kingharvest.infrastructure.reflection.MethodReflect;
import se.kingharvest.infrastructure.ui.DialogBase;
import se.kingharvest.infrastructure.ui.annotation.OnCreateDialogAnnotation;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;

public class DialogManager<VM> {

	protected Activity _activity;
	
	public DialogManager(Activity activity)
	{
		_activity = activity;
	}

    private static final String SE_KINGHARVEST_DIALOGMANAGER_NR_OF_DIALOGS = "SE_KINGHARVEST_DIALOGMANAGER_NR_OF_DIALOGS";
    private static final String SE_KINGHARVEST_DIALOGMANAGER_DIALOG = "SE_KINGHARVEST_DIALOGMANAGER_DIALOG_";
    private static final String SE_KINGHARVEST_DIALOGMANAGER_SAVED_DIALOGS_TAG = "SE_KINGHARVEST_DIALOGMANAGER_DIALOGS_TAG";

    private Set<DialogBase<?>> _managedDialogs;
    
    /**
     * Add a dialog to the managed collection, prepare it, and show it.
     * @param dialog
     */
    public void showDialog(DialogBase<?> dialog)
    {
        if (_managedDialogs == null) {
        	_managedDialogs = new HashSet<DialogBase<?>>();
        }

        _managedDialogs.add(dialog);
        
        MethodReflect.call(_activity, "prepareDialog", dialog);
		dialog.show();
    }

    
    public void closeDialog()
    {
    	
    }

    /**
     * When an activity is destroyed, dismiss all managed dialogs
     * and decouple the activity.
     */
    public void onDestroy() 
	{		
    	// dismiss any dialogs we are managing.
        if (_managedDialogs != null) {
        	for (Dialog dialog : _managedDialogs) {
        		if (dialog != null && dialog.isShowing()) {
        			dialog.dismiss();
        		}
        	}
        }
        _activity = null;
	}

    /**
     * Save the state of all managed dialogs.
     * @param outState
     */
    public void saveManagedDialogs(Bundle outState) 
    {
        if (_managedDialogs == null) {
            return;
        }

        final int numDialogs = _managedDialogs.size();
        if (numDialogs == 0) {
            return;
        }

        Bundle dialogState = new Bundle();

        // save each dialog to the bundle.
        int i = 0;
        for (Dialog dialog : _managedDialogs) {
            dialogState.putBundle(SE_KINGHARVEST_DIALOGMANAGER_DIALOG + i, dialog.onSaveInstanceState());
            i++;
		}

        dialogState.putInt(SE_KINGHARVEST_DIALOGMANAGER_NR_OF_DIALOGS, _managedDialogs.size());
        outState.putBundle(SE_KINGHARVEST_DIALOGMANAGER_SAVED_DIALOGS_TAG, dialogState);
    }
    
    /**
     * Restores saved dialogs.
     * @param savedInstanceState
     */
    public void restoreManagedDialogs(Bundle savedInstanceState) 
    {
        final Bundle b = savedInstanceState.getBundle(SE_KINGHARVEST_DIALOGMANAGER_SAVED_DIALOGS_TAG);
        if (b == null) {
            return;
        }

        final int numDialogs = b.getInt(SE_KINGHARVEST_DIALOGMANAGER_NR_OF_DIALOGS, 0);
        _managedDialogs = new HashSet<DialogBase<?>>(numDialogs);
        for (int i = 0; i < numDialogs; i++) {
            Bundle dialogState = b.getBundle(SE_KINGHARVEST_DIALOGMANAGER_DIALOG + i);
            if (dialogState != null) {
                // Calling onRestoreInstanceState() below will invoke dispatchOnCreate
                // so tell createDialog() not to do it, otherwise we get an exception
                final DialogBase<?> dialog = createDialog(dialogState);
                _managedDialogs.add(dialog);
                MethodReflect.call(_activity, "prepareDialog", dialog);
                dialog.onRestoreInstanceState(dialogState);
            }
        }
    }

    private DialogBase<?> createDialog(Bundle state) {
    	Class<?> t;
    	//state.putSerializable("class", t);
    	// Create using annotations and reflection..
    	
    	int dialogId = 0;
    	Class<?> returnType = null;
        final DialogBase<?> dialog;// = onCreateDialog(dialogId);
        
        OnCreateDialogAnnotation.getInstance().getAnnotatedMethod(_activity, returnType);
        
        //dialog.dispatchOnCreate(state);
        return null;//dialog;
    }

    /**
	 * Synchronized show dialog.
	 * @param activity
	 * @param dialog
	 * @return
	 */
	public static boolean show(final Activity activity, final Dialog dialog)
	{
		final CountDownLatch latch = new CountDownLatch(1);
		final BooleanProperty isDialogDismissed = new BooleanProperty(false);
		
		activity.runOnUiThread(new Runnable() {
			public void run() {
				dialog.setOnDismissListener(onDialogDismiss(latch, isDialogDismissed));
				dialog.show();
			}
		});
		
		try {
			latch.await();
		} catch (InterruptedException e) {
		} 
		return isDialogDismissed.get();
	}
	
	private static OnDismissListener onDialogDismiss(final CountDownLatch latch, final BooleanProperty isDialogDismissed)
	{
		return new OnDismissListener() {
			public void onDismiss(DialogInterface dialog) {
				isDialogDismissed.set(true);
				latch.countDown();
			}
		};
		
	}
}
