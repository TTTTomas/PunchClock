package se.kingharvest.infrastructure.ui.annotation;

import se.kingharvest.infrastructure.annotation.AnnotationReaderByValue;

public class NavigateToAnnotation extends AnnotationReaderByValue<NavigateTo>
{
	static NavigateToAnnotation _instance;
	
	public static NavigateToAnnotation getInstance(){
		if(_instance == null)
			_instance = new NavigateToAnnotation();
		return _instance;
	}
	
	protected NavigateToAnnotation() {
		super(NavigateTo.class);
	}

	@Override
	public boolean annotationValueEquals(NavigateTo annotation, int value){
		return (annotation.value() == value);
	}
}
