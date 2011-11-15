package se.kingharvest.infrastructure.system;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Various reflection based utility methods.
 * 
 * @author tomasb
 *
 */
public class Reflect {

	/**
	 * Makes a safe cast, returning null if a cast isn't possible.
	 * @param <T>
	 * @param obj
	 * @param contract
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T as(Object obj, Class<T> contract)
	{
		if (obj == null)
			return null;

		if(contract.isAssignableFrom(obj.getClass()))
			return (T)obj;
			
		return null;
	}

	/**
	 * Returns a specified method of an object, wrapping any exceptions.
	 * @param object
	 * @param methodName
	 * @param argClass
	 * @return
	 */
	public static Method getMethod(Object object, String methodName, Class<?> argClass)
	{
		Method method = null;
		try {
			method = object.getClass().getMethod(methodName, argClass);
		} catch (SecurityException e) {
			throw new ReflectException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new ReflectException(e.getMessage(), e);
		}
		return method;
	}

	public static <T> Constructor<T> getConstructor(Class<T> type, Class<?> ... argClass)
	{
		Constructor<T> constructor = null;
		try {
			constructor = type.getConstructor(argClass);
		} catch (SecurityException e) {
			throw new ReflectException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new ReflectException(e.getMessage(), e);
		}
		return constructor;
	}
	
	/**
	 * Calls a Method, wrapping any exceptions.
	 * @param object
	 * @param method
	 * @param args
	 * @return
	 */
	public static Object call(Object object, Method method, Object ... args)
	{
		try {
			return method.invoke(object, args);
		} catch (IllegalAccessException e) {
			throw new ReflectException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new ReflectException(e.getMessage(), e);
		}
	}

	public static <T> T newInstance(Constructor<T> constructor, Object arg)
	{
		T instance = null;
		try {
			instance = constructor.newInstance(arg);
		} catch (IllegalArgumentException e) {
			throw new ReflectException(e.getMessage(), e);
		} catch (InstantiationException e) {
			throw new ReflectException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new ReflectException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new ReflectException(e.getMessage(), e);
		}
		return instance;
	}

	public static boolean matches(Method m, Class<?> ... params) {
		return matches(m, null, params);
	}
	
	public static boolean matches(Method m, Class<?> returnType, Class<?> ... params) {
		
		if((m.getReturnType() == null && returnType != null) 
		|| (m.getReturnType() != null && returnType == null)
		|| (m.getReturnType() != null && returnType != null && !m.getReturnType().equals(returnType)))
			return false;

		// Here we know that the return type matches. 

		return Arrays.equals(m.getParameterTypes(), params);
		
//		Class<?>[] methodParams = m.getParameterTypes();
//		if((params == null && methodParams != null)
//		|| (params != null && methodParams == null)
//		|| (params != null && methodParams != null && params.length != methodParams.length))
//			return false;
//
//		// Here we know that the parameters are same size non null arrays.
//		// Everything passed.
//		return true;
	}
}
