package se.kingharvest.infrastructure.ui.navigation;

import android.app.Activity;

public interface INavigator {

	<A extends Activity> void navigateTo(Class<A> pageType);

	<A extends Activity> void navigateForResult(Class<A> pageType, int requestCode);

	void finishWithResult(int result);

	<A extends Activity> void navigateTo(Class<A> targetClass, int targetMethod, Object ... args);
	
//	<A extends Activity, A1> void navigateTo(Class<A> targetClass, int targetMethod, A1 arg1);
}
