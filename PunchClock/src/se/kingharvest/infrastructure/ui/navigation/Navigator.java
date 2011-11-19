package se.kingharvest.infrastructure.ui.navigation;

import java.lang.reflect.Method;

import se.kingharvest.infrastructure.system.Reflect;
import se.kingharvest.infrastructure.ui.annotation.NavigateToAnnotation;
import se.kingharvest.infrastructure.ui.util.IntentUtils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class Navigator {

	private static final String SE_KINGHARVEST_NAVIGATOR_NR_OF_ARGUMENTS 	= "SE_KINGHARVEST_NAVIGATOR_NR_OF_ARGUMENTS";
	private static final String SE_KINGHARVEST_NAVIGATOR_TARGET 			= "SE_KINGHARVEST_NAVIGATOR_TARGET";

	public static void callNavigationTarget(Activity activity)
	{
		Intent intent = activity.getIntent();
		
		int targetMethod = intent.getIntExtra(SE_KINGHARVEST_NAVIGATOR_TARGET, -1);
		if(targetMethod != -1)
		{
			int nrOfArguments = intent.getIntExtra(SE_KINGHARVEST_NAVIGATOR_NR_OF_ARGUMENTS, -1);
			
			
			Method m = NavigateToAnnotation.getAnnotatedMethod(activity, targetMethod);
			Object[] args = new Object[nrOfArguments];
			for (int i = 0; i < args.length; i++) {
				args[i] = IntentUtils.getArgumentFromIntent(intent, i);
			}
			Reflect.call(activity, m, args);
		}
	}
	
	public static <A extends Activity> void navigateTo(Context context, Class<A> targetClass, int targetMethod, Object ... args)
	{
        Intent intent = new Intent(context, targetClass);
        intent.putExtra(SE_KINGHARVEST_NAVIGATOR_TARGET, targetMethod);
        intent.putExtra(SE_KINGHARVEST_NAVIGATOR_NR_OF_ARGUMENTS, args.length);
        for (int i = 0; i < args.length; i++) {
            IntentUtils.putArgumentInIntent(intent, i, args[i]);
		}
        context.startActivity(intent);
	}
}
