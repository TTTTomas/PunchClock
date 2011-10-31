package se.kingharvest.infrastructure.property;

import java.util.HashSet;

public abstract class Property<T> implements IProperty<T> {

	private static final long serialVersionUID = 1760400741734504549L;

	transient public HashSet<IPropertyListener<T>> Listeners = new HashSet<IPropertyListener<T>>();
	
	T _value;
	
	public void set(T value){
		_value = value;
		for (IPropertyListener<T> listener : Listeners) {
			listener.propertyChanged(value);
		}
	}
	
	public T get(){
		return _value;
	}
	
}
