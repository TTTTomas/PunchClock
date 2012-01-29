package se.kingharvest.infrastructure.ui.annotation;

import java.lang.reflect.Method;

import se.kingharvest.infrastructure.annotation.AnnotationReader;

public class OnCreateDialogAnnotation extends AnnotationReader<OnCreateDialog, Class<?>>{

	static OnCreateDialogAnnotation _instance;
	
	public static OnCreateDialogAnnotation getInstance(){
		if(_instance == null)
			_instance = new OnCreateDialogAnnotation();
		return _instance;
	}

	protected OnCreateDialogAnnotation() {
		super(OnCreateDialog.class);
	}

	@Override
	public boolean methodMatches(Method method, Class<?> returnType) {
		return method.getReturnType().equals(returnType);
	}
}
