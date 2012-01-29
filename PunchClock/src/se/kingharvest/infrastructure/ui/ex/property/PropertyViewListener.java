package se.kingharvest.infrastructure.ui.ex.property;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import se.kingharvest.infrastructure.property.IPropertyListener;
import se.kingharvest.infrastructure.property.Property;

/**
 * A property listener for views. The view is stored as a weak reference,
 * so that it may be garbage collected. The listener automatically adds itself 
 * as a listener to the property in the constructor.
 * @author tomasb
 *
 * @param <T>
 * @param <V>
 */
public abstract class PropertyViewListener<T, V> extends WeakReference<V> implements IPropertyListener<T>{
	
	@SuppressWarnings("unchecked")
	public PropertyViewListener(V view, Property<T> property)
	{
		super(view, (ReferenceQueue<V>) property.getReferenceQueue());
		property.addListener(this);
	}

	public final void propertyChanged(T value) {
		V view = get();
		if(view != null)
			propertyChanged(view, value);
	}

	protected abstract void propertyChanged(V view, T value);
}
