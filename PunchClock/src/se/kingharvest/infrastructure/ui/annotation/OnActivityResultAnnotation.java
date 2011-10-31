package se.kingharvest.infrastructure.ui.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

public class OnActivityResultAnnotation {

	public static HashMap<Integer, Method> getAnnotatedMethods(Object obj)
	{
		HashMap<Integer, Method> methodMap = null;
		// Finds the method annotated with OnActivityResult, having the value requestCode.
		Method[] methods = obj.getClass().getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			Annotation a = m.getAnnotation(OnActivityResult.class);
			
			if(a != null)
			{
				if(methodMap == null)
					methodMap = new HashMap<Integer, Method>();
				
				m.setAccessible(true);
				methodMap.put(((OnActivityResult)a).value(), m);
			}
		}
		return methodMap;
	}
}
