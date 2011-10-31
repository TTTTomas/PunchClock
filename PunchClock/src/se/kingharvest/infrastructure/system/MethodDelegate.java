package se.kingharvest.infrastructure.system;

import java.lang.reflect.Method;

/**
 * Wraps a named method of a given Object, acting as a delegate.
 * @author tomasb
 *
 * @param <R>
 * @param <T1>
 */
public class MethodDelegate<R, T1> extends Delegate<R, T1> {

	Object _object;
	Method _method;
	
	public MethodDelegate(Object object, String methodName, Class<?> argClass) {
		_object = object;
		_method = Reflect.getMethod(object, methodName, argClass);
	}

	/**
	 * Invokes this delegate.
	 * @param arg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public R call(T1 arg)
	{
		return (R) Reflect.call(_object, _method, arg);
	}
}
