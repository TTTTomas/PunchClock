package se.kingharvest.infrastructure.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import se.kingharvest.infrastructure.collection.HashMapMap;
import se.kingharvest.infrastructure.reflection.Reflect;

/**
 * Base class for simplifying annotation extraction.
 * Extend this class for finding methods annotated with annotation A that
 * has a given integer value. A typical use will be to match the value() of
 * the annotation.
 * 
 * @author tomasb
 *
 * @param <A>
 */
public abstract class AnnotationReaderByValue<A extends Annotation> 
{
	Class<A> _annotationClass;
	
	protected HashMapMap<Class<?>, Integer, Method> _methodCache;
	
	protected AnnotationReaderByValue(Class<A> annotationClass)
	{
		_annotationClass = annotationClass;
	}
	
	/**
	 * Returns a method annotated with the typed annotation A, with the annotation matching the given integer value.
	 * @param obj
	 * @param value
	 * @return
	 */
	public Method getAnnotatedMethod(Object obj, int value)
	{
		Class<?> type = obj.getClass();

		if(_methodCache == null)
			_methodCache = new HashMapMap<Class<?>, Integer, Method>();
		else
			if(_methodCache.containsKeys(type, value))
				return _methodCache.get(type, value);

		Method[] methods = Reflect.getDeclaredMethods(obj);
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			A a = m.getAnnotation(_annotationClass);
			if(a != null)
			{
				if (annotationValueEquals(a, value))
				{
					m.setAccessible(true);
					_methodCache.put(type, value, m);
					return m;
				}
			}
		}
		throw new NullPointerException("Failed to find method annotated with " + _annotationClass + " and value " + value + " in " + obj.getClass().getSimpleName() + ".");
	}
	
	/**
	 * Determines whether a given annotation matches the given value, and will therefore
	 * be returned in getAnnotatedMethod().
	 * @param method
	 * @param value
	 * @return
	 */
	public abstract boolean annotationValueEquals(A annotation, int value);
}
