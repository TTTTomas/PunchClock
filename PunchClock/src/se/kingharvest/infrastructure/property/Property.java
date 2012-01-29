package se.kingharvest.infrastructure.property;

import java.lang.ref.ReferenceQueue;
import java.util.HashSet;

public abstract class Property<T> implements IProperty<T> {

	private static final long serialVersionUID = 1760400741734504549L;

	private HashSet<IPropertyListener<T>> _listeners = new HashSet<IPropertyListener<T>>();
	
	private ReferenceQueue<?> _referenceQueue;
	
	T _value;
	
	public void set(T value){
		_value = value;
		expungeReferences();
		for (IPropertyListener<T> listener : _listeners) {
			listener.propertyChanged(value);
		}
	}
	
	public T get(){
		return _value;
	}
	
	public void addListener(IPropertyListener<T> listener){
		expungeReferences();
		_listeners.add(listener);
	}
	
	public ReferenceQueue<?> getReferenceQueue(){
		return _referenceQueue;
	}
	
	@SuppressWarnings("unchecked")
	private void expungeReferences()
	{
		IPropertyListener<T> ref;
		while((ref = (IPropertyListener<T>) _referenceQueue.poll()) != null)
		{
			_listeners.remove(ref);
		}
	}
}
