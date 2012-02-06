package se.kingharvest.infrastructure.property;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public abstract class Event<T> implements IEvent<T>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1003399080904653328L;


	private List<IPropertyListener<T>> _listeners = new ArrayList<IPropertyListener<T>>();
	
	private ReferenceQueue<?> _referenceQueue;
	
	T _value;
	
	public void set(T value){
		_value = value;
		expungeReferences();
		for (int i=0; i<_listeners.size(); i++) {
			_listeners.get(i).propertyChanged(value);
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
