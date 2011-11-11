package se.kingharvest.infrastructure.ui;

import java.util.concurrent.CountDownLatch;

import se.kingharvest.infrastructure.property.BooleanProperty;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

public class DialogManager {

	
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
	
	static OnDismissListener onDialogDismiss(final CountDownLatch latch, final BooleanProperty isDialogDismissed)
	{
		return new OnDismissListener() {
			public void onDismiss(DialogInterface dialog) {
				isDialogDismissed.set(true);
				latch.countDown();
			}
		};
		
	}
}
