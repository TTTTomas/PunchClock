package se.kingharvest.infrastructure.ui;

import android.app.Activity;

public interface INavigator {

	<A extends Activity> void navigateTo(Class<A> pageType);

	<A extends Activity> void navigateForResult(Class<A> pageType, int requestCode);

	void finishWithResult(int result);
	//<A extends Activity, A1> void navigateTo(A1 arg1, Class<A> pageType);
}
