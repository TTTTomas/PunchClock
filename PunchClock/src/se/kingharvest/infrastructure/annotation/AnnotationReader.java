package se.kingharvest.infrastructure.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import se.kingharvest.infrastructure.collection.HashMapMap;
import se.kingharvest.infrastructure.reflection.Reflect;

/**
 * Base class for simplifying annotation extraction.
 * Extend this class for finding methods annotated with annotation A that
 * matches the value V. The value can match in any given way, by being the annotation value,
 * by being the method name, return type or parameter, or any other thing.
 * 
 * @author tomasb
 *
 * @param <A, V>
 */
public abstract class AnnotationReader<A extends Annotation, V> 
{
	Class<A> _annotationClass;
	
	protected HashMapMap<Class<?>, V, Method> _methodCache;
	
	protected AnnotationReader(Class<A> annotationClass)
	{
		_annotationClass = annotationClass;
	}
	
	/**
	 * Returns a method annotated with the typed annotation A, with the method matching the given value of type V.
	 * @param obj
	 * @param value
	 * @return
	 */
	public Method getAnnotatedMethod(Object obj, V value)
	{
		Class<?> type = obj.getClass();

		if(_methodCache == null)
			_methodCache = new HashMapMap<Class<?>, V, Method>();
		else
			if(_methodCache.containsKeys(type, value))
				return _methodCache.get(type, value);

		Method[] methods = Reflect.getDeclaredMethods(obj);
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			A a = m.getAnnotation(_annotationClass);
			if(a != null)
			{
				if (methodMatches(m, value))
				{
					m.setAccessible(true);
					_methodCache.put(type, value, m);
					return m;
				}
			}
		}
		throw new NullPointerException("Failed to find method annotated with " + _annotationClass + " and matching value " + value + " in " + obj.getClass().getSimpleName() + ".");
	}
	
	/**
	 * Determines whether a given annotated method matches the given value, and will therefore
	 * be returned in getAnnotatedMethod().
	 * @param method
	 * @param value
	 * @return
	 */
	public abstract boolean methodMatches(Method method, V value);
}
