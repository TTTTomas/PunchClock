package se.kingharvest.infrastructure.ui.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class NavigateToAnnotation {

	/**
	 * Finds the method annotated with NavigateTo, having the value id, and the method signature matching paramTypes.
	 * @param obj
	 * @param id
	 * @param paramTypes
	 * @return
	 */
	public static Method getAnnotatedMethod(Object obj, int id)
	{
		Method[] methods = obj.getClass().getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			Annotation a = m.getAnnotation(NavigateTo.class);
			
			if(a != null)
			{
				if(((NavigateTo)a).value() == id)
				{
					m.setAccessible(true);
					return m;
				}
			}
		}
		throw new NullPointerException("Failed to find method annotated with NavigateTo in " + obj.getClass().getSimpleName() + ".");
	}
}
