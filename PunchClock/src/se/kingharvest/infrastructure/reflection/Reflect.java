package se.kingharvest.infrastructure.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;

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

	public static <T, A> T newInstance(Class<T> type, Class<A> argClass, A argument)
	{
		Constructor<T> constructor = Reflect.getConstructor(type, argClass);
		T newObject = Reflect.newInstance(constructor, argument);
		return newObject;
	}

	public static <T> Constructor<T> getConstructor(Class<T> type, Class<?> ... argClass)
	{
		Constructor<T> constructor = null;
		try {
			constructor = type.getConstructor(argClass);
		} catch (Exception e) {
			throw new ReflectException("Failed to get constructor of type " + type, e);
		}
		return constructor;
	}

	public static <T> T newInstance(Class<T> type)
	{
		T instance = null;
		try{
			instance = type.newInstance();
		}
		catch(Exception e){
			throw new ReflectException("Failed to create new instance of type " + type, e);
		}
		return instance;
	}

	public static <T> T newInstance(Constructor<T> constructor, Object arg)
	{
		T instance = null;
		try {
			instance = constructor.newInstance(arg);
		} catch (Exception e) {
			throw new ReflectException("Failed to create new instance from constructor of type " + constructor.getDeclaringClass() + " using argument of type " + Reflect.getClassSimpleName(arg), e);
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

	public static String getClassSimpleName(Object object) {
		if(object == null)
			return "Null";
		return object.getClass().getSimpleName();
	}

	
	protected static HashMap<Class<?>, Method[]> _methodsCache;
	
	public static Method[] getDeclaredMethods(Object object) {
		
		Class<?> type = object.getClass();
		if(_methodsCache == null)
			_methodsCache = new HashMap<Class<?>, Method[]>();
		else
			if(_methodsCache.containsKey(type))
				return _methodsCache.get(type);

		Method[] methods = type.getDeclaredMethods();
		_methodsCache.put(type, methods);
		return methods;
	}
}
