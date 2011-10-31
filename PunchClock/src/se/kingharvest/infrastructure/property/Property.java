package se.kingharvest.infrastructure.property;

import java.util.HashSet;

public abstract class Property<T> implements IProperty<T> {

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
