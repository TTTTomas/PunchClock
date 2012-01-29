package se.kingharvest.infrastructure.ui.annotation;

import se.kingharvest.infrastructure.annotation.AnnotationReaderByValue;

public class OnClickAnnotation extends AnnotationReaderByValue<OnClick>
{
	static OnClickAnnotation _instance;
	
	public static OnClickAnnotation getInstance(){
		if(_instance == null)
			_instance = new OnClickAnnotation();
		return _instance;
	}
	
	protected OnClickAnnotation() {
		super(OnClick.class);
	}

	@Override
	public boolean annotationValueEquals(OnClick annotation, int value) {
		return (annotation.value() == value);
	}
}
