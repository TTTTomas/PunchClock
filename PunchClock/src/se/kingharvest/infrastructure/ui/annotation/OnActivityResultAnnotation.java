package se.kingharvest.infrastructure.ui.annotation;

import se.kingharvest.infrastructure.annotation.AnnotationReaderByValue;

public class OnActivityResultAnnotation extends AnnotationReaderByValue<OnActivityResult>{

	static OnActivityResultAnnotation _instance;
	
	public static OnActivityResultAnnotation getInstance(){
		if(_instance == null)
			_instance = new OnActivityResultAnnotation();
		return _instance;
	}

	protected OnActivityResultAnnotation() {
		super(OnActivityResult.class);
	}

	@Override
	public boolean annotationValueEquals(OnActivityResult annotation, int value) {
		return (annotation.value() == value);
	}
}
