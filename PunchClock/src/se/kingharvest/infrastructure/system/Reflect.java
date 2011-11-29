package se.kingharvest.infrastructure.system;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

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

	public static <T> T newInstance(Class<T> type)
	{
		T instance = null;
		try{
			instance = type.newInstance();
		}
		catch(Exception e){
			throw new ReflectException(e.getMessage(), e);
		}
		return instance;
	}
		
			
	
	public static <T> T newInstance(Constructor<T> constructor, Object arg)
	{
		T instance = null;
		try {
			instance = constructor.newInstance(arg);
		} catch (Exception e) {
			throw new ReflectException(e.getMessage(), e);
		}
		return instance;
	}

	/**
	 * Returns the first generic type argument of the given class.
	 * @param class1
	 * @return
	 */
	public static Class<?> getGenericType(Class<?> clazz) {
		Class<?> genericType = ((Class<?>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0]);
		return genericType;
	}

	public static void setBoolean(String fieldName, Object object, boolean value)
	{
		try {
			object.getClass().getField(fieldName).setBoolean(object, value);
		} catch (Exception e) {
			throw new ReflectException(e.getMessage(), e);
		}
	}

	public static void setInteger(String fieldName, Object object, int value)
	{
		try {
			object.getClass().getField(fieldName).setInt(object, value);
		} catch (Exception e) {
			throw new ReflectException(e.getMessage(), e);
		}
	}
	
	public static void setLong(String fieldName, Object object, long value)
	{
		try {
			object.getClass().getField(fieldName).setLong(object, value);
		} catch (Exception e) {
			throw new ReflectException(e.getMessage(), e);
		}
	}

	public static void setShort(String fieldName, Object object, short value)
	{
		try {
			object.getClass().getField(fieldName).setShort(object, value);
		} catch (Exception e) {
			throw new ReflectException(e.getMessage(), e);
		}
	}

	public static void setFloat(String fieldName, Object object, Float value)
	{
		try {
			object.getClass().getField(fieldName).setFloat(object, value);
		} catch (Exception e) {
			throw new ReflectException(e.getMessage(), e);
		}
	}

	public static void setDouble(String fieldName, Object object, Double value)
	{
		try {
			object.getClass().getField(fieldName).setDouble(object, value);
		} catch (Exception e) {
			throw new ReflectException(e.getMessage(), e);
		}
	}

	public static void setByte(String fieldName, Object object, byte value)
	{
		try {
			object.getClass().getField(fieldName).setByte(object, value);
		} catch (Exception e) {
			throw new ReflectException(e.getMessage(), e);
		}
	}

	public static void setChar(String fieldName, Object object, char value)
	{
		try {
			object.getClass().getField(fieldName).setChar(object, value);
		} catch (Exception e) {
			throw new ReflectException(e.getMessage(), e);
		}
	}

	public static void setDate(String fieldName, Object object, String value)
	{
		try {
			object.getClass().getField(fieldName).set(object, ISO8601Date.parse(value));
		} catch (Exception e) {
			throw new ReflectException(e.getMessage(), e);
		}
	}

	public static void set(String fieldName, Object object, Object value)
	{
		try {
			object.getClass().getField(fieldName).set(object, value);
		} catch (Exception e) {
			throw new ReflectException(e.getMessage(), e);
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
