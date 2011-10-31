package se.kingharvest.infrastructure.ui.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class OnClickAnnotation {

	public static Method getAnnotatedMethod(Object obj, int id)
	{
		// Finds the method annotated with OnClick, having the value id.
		Method[] methods = obj.getClass().getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			Annotation a = m.getAnnotation(OnClick.class);
			
			if(a != null)
			{
				if(((OnClick)a).value() == id)
				{
					m.setAccessible(true);
					return m;
				}
			}
		}
		throw new NullPointerException("Failed to find method annotated with OnClick in " + obj.getClass().getSimpleName() + ".");
	}
}
