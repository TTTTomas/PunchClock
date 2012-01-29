package se.kingharvest.infrastructure.reflection;

import java.lang.reflect.Method;
import java.util.Arrays;

import se.kingharvest.infrastructure.collection.HashMapMap;


/**
 * Various reflection based utility methods.
 * 
 * @author tomasb
 *
 */
public class MethodReflect {

	protected static HashMapMap<Class<?>, Class<?>[], Method> _methodCache;

	/**
	 * Returns a specified method of an object, wrapping any exceptions.
	 * @param object
	 * @param methodName
	 * @param argClass
	 * @return
	 */
	public static Method getMethod(Object object, String methodName, Class<?> ... argClass)
	{
		Class<?> type = object.getClass();

		if(_methodCache == null)
			_methodCache = new HashMapMap<Class<?>, Class<?>[], Method>();
		else
			if(_methodCache.containsKeys(type, argClass))
				return _methodCache.get(type, argClass);

		try {
			Method method = object.getClass().getMethod(methodName, argClass);
			if(method == null)
				throw new ReflectException("Method " + Reflect.getClassSimpleName(object) + "." + methodName + " with argument types " + Arrays.toString(argClass) + " does not exist.");
			method.setAccessible(true);
			_methodCache.put(type, argClass, method);
			return method;
		} catch (Exception e) {
			throw new ReflectException("Failed to get method " + Reflect.getClassSimpleName(object) + "." + methodName + " with argument types " + Arrays.toString(argClass) + ".", e);
		}
	}

	/**
	 * Calls a named method.
	 * @param object
	 * @param method
	 * @param args
	 * @return
	 */
	public static Object call(Object object, String methodName)
	{
		Method method = getMethod(object, methodName);
		try {
			return method.invoke(object);
		} catch (Exception e) {
			throw new ReflectException("Failed to call method " + method + " with no arguments.", e);
		}
	}

	/**
	 * Calls a named method with an argument, wrapping any exceptions.
	 * @param object
	 * @param method
	 * @param args
	 * @return
	 */
	public static Object call(Object object, String methodName, Object ... args)
	{
		Method method = getMethod(object, methodName, toClassArray(args));
		try {
			return method.invoke(object, args);
		} catch (Exception e) {
			throw new ReflectException("Failed to call method " + method + " with arguments of types " + Arrays.toString(toClassArray(args)), e);
		}
	}

	private static Class<?>[] toClassArray(Object ... args) {

		if(args == null || args.length == 0)
			return null;

		else if(args != null && args.length == 1)
			args[0].getClass();

		Class<?>[] classArray = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			classArray[i] = args[i].getClass();
		}
		return classArray;
	}

	/**
	 * Calls a specified method with arguments, wrapping any exceptions.
	 * @param object
	 * @param method
	 * @param args
	 * @return
	 */
	public static Object call(Object object, Method method, Object ... args)
	{
		try {
			return method.invoke(object, args);
		} catch (Exception e) {
			throw new ReflectException("Failed to call method " + method + " with arguments of types " + Arrays.toString(toClassArray(args)), e);
		}
	}

	//	public static boolean matches(Method m, String ... params) {
//		return matches(m, null, params);
//	}
//	
//	public static boolean matches(Method m, String returnType, String ... params) {
//		
//		if((m.getReturnType() == null && returnType != null) 
//		|| (m.getReturnType() != null && returnType == null)
//		|| (m.getReturnType() != null && returnType != null && !m.getReturnType().getSimpleName().equals(returnType)))
//			return false;
//
//		// Here we know that the return type matches. 
//
//		return Arrays.equals(m.getParameterTypes(), params);
		
//		Class<?>[] methodParams = m.getParameterTypes();
//		if((params == null && methodParams != null)
//		|| (params != null && methodParams == null)
//		|| (params != null && methodParams != null && params.length != methodParams.length))
//			return false;
//
//		// Here we know that the parameters are same size non null arrays.
//		// Everything passed.
//		return true;
//	}
}
