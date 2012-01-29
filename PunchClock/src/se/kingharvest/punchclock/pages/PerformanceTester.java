package se.kingharvest.punchclock.pages;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import se.kingharvest.infrastructure.diagnostics.Logger;
import se.kingharvest.infrastructure.reflection.MethodReflect;
import se.kingharvest.infrastructure.ui.annotation.OnClickAnnotation;
import se.kingharvest.punchclock.PunchClockActivity;
import se.kingharvest.punchclock.R;
import se.kingharvest.punchclock.dialogs.NewProjectDialog;

public class PerformanceTester {

	
	public static class StopWatch
	{
		private long _startTime;
		private long _stopTime;
		
		public StopWatch()
		{
			_startTime = new Date().getTime();
		}
		
		public long stop()
		{
			_stopTime = new Date().getTime() - _startTime;
			return _stopTime;
		}

		public long stopTime()
		{
			return _stopTime;
		}
	}


	private static final String LOG_TAG = PerformanceTester.class.getSimpleName();
	
	
	
	public static void testAnnotations(Object obj)
	{
		Logger.write(LOG_TAG, " ");
		int nrOfRuns = 10;
		Method[] methods = new Method[nrOfRuns];
		
		StopWatch timer2 = new StopWatch();
		for(int i=0; i<nrOfRuns; i++)
		{
			methods[i] = OnClickAnnotation.getInstance().getAnnotatedMethod(obj, R.id.Punchclock_BreakButton);
		}
		Logger.write(LOG_TAG, "Annotations with cache: " + timer2.stop());

		StopWatch timer = new StopWatch();
		for(int i=0; i<nrOfRuns; i++)
		{
			methods[i] = OnClickAnnotation.getInstance().getAnnotatedMethod(obj, R.id.Punchclock_BreakButton);
		}
		Logger.write(LOG_TAG, "Annotations without cache: " + timer.stop());
		Logger.write(LOG_TAG, "Annotations factor: " + timer.stopTime()/timer2.stopTime());
	}

	
	public static void testMethodLookup(Object obj)
	{
		int nrOfRuns = 10000;
		Method[][] methodArray = new Method[nrOfRuns][];

		StopWatch timer2 = new StopWatch();
		for (int i = 0; i < nrOfRuns; i++) {
			methodArray[i] = getDeclaredMethods(obj);
		}
		Logger.write(LOG_TAG, "Declared methods with cache: " + timer2.stop());		

		StopWatch timer = new StopWatch();
		for (int i = 0; i < nrOfRuns; i++) {
			methodArray[i] = obj.getClass().getDeclaredMethods();
		}
		Logger.write(LOG_TAG, "Declared methods without cache: " + timer.stop());
		Logger.write(LOG_TAG, "Declared methods factor: " + timer.stopTime()/timer2.stopTime());
	}


	private static Map<Class<?>, Method[]> methodCache;
	private static Method[] getDeclaredMethods(Object obj) {
		Class<?> type = obj.getClass();
		if(methodCache == null)
			methodCache = new HashMap<Class<?>, Method[]>();
			
		if(methodCache.containsKey(type))
			return methodCache.get(type);
		
		Method[] methods = type.getDeclaredMethods();
		methodCache.put(type, methods);
		return methods;
	}


	public static void testSingleMethodLookup(Object obj)
	{
		int nrOfRuns = 10000;
		Method[] methodArray = new Method[nrOfRuns];

		StopWatch timer2 = new StopWatch();
		for (int i = 0; i < nrOfRuns; i++) {
			methodArray[i] = getMethodWithCache(obj, "prepareDialog", NewProjectDialog.class);
		}
		Logger.write(LOG_TAG, "Named method with cache: " + timer2.stop());

		StopWatch timer = new StopWatch();
		for (int i = 0; i < nrOfRuns; i++) {
			methodArray[i] = MethodReflect.getMethod(obj, "prepareDialog", NewProjectDialog.class);
		}
		Logger.write(LOG_TAG, "Named method without cache: " + timer.stop());
		Logger.write(LOG_TAG, "Named methods factor: " + timer.stopTime()/timer2.stopTime());
	}

	public static void testSingleMethodCall(PunchClockActivity obj)
	{
		int nrOfRuns = 10000;
		Method m = MethodReflect.getMethod(obj, "countUp");

		StopWatch timer2 = new StopWatch();
		for (int i = 0; i < nrOfRuns; i++) {
			MethodReflect.call(obj, m);
		}
		timer2.stop();

		StopWatch timer = new StopWatch();
		for (int i = 0; i < nrOfRuns; i++) {
			obj.countUp();
		}
		timer.stop();
		
		Logger.write(LOG_TAG, "Reflected method call: " + timer2.stopTime());
		Logger.write(LOG_TAG, "Direct method call: " + timer.stopTime());
		Logger.write(LOG_TAG, "Method call factor: " + timer2.stopTime()/timer.stopTime());
	}

	public static void testSingleMethodCallWithArgs(PunchClockActivity obj)
	{
		int nrOfRuns = 10000;
		Method m = MethodReflect.getMethod(obj, "countUp", int.class, int.class, String.class, String.class);

		StopWatch timer2 = new StopWatch();
		for (int i = 0; i < nrOfRuns; i++) {
			MethodReflect.call(obj, m, 25, 0, "", "");
		}
		timer2.stop();

		StopWatch timer = new StopWatch();
		for (int i = 0; i < nrOfRuns; i++) {
			obj.countUp(25, 0, "dop", "lep");
		}
		timer.stop();
		
		Logger.write(LOG_TAG, "Reflected method call with arguments: " + timer2.stopTime());
		Logger.write(LOG_TAG, "Direct method call with arguments: " + timer.stopTime());
		Logger.write(LOG_TAG, "Method call with arguments factor: " + timer2.stopTime()/timer.stopTime());
	}
	
	private static Method getMethodWithCache(Object object, String methodName, Class<?> argClass) {

		return MethodReflect.getMethod(object, methodName, argClass);
	}

	public static void testSingleMethodCallCached(PunchClockActivity obj)
	{
		int nrOfRuns = 10000;

		StopWatch timer2 = new StopWatch();
		for (int i = 0; i < nrOfRuns; i++) {
			Method m = MethodReflect.getMethod(obj, "countUp", int.class, int.class, String.class, String.class);
			MethodReflect.call(obj, m, 25, 9, "", "");
		}
		timer2.stop();

		StopWatch timer = new StopWatch();
		for (int i = 0; i < nrOfRuns; i++) {
			Method m = MethodReflect.getMethod(obj, "countUp", int.class, int.class, String.class, String.class);
			MethodReflect.call(obj, m, 25, 9, "");
		}
		timer.stop();
		
		Logger.write(LOG_TAG, "Reflected method call: " + timer2.stopTime());
		Logger.write(LOG_TAG, "getMethod2 method call: " + timer.stopTime());
		Logger.write(LOG_TAG, "Method call factor: " + timer2.stopTime()/timer.stopTime());
	}

}
