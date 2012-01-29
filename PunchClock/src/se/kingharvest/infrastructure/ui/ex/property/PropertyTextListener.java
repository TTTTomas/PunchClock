package se.kingharvest.infrastructure.ui.ex.property;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import se.kingharvest.infrastructure.property.IPropertyListener;
import se.kingharvest.infrastructure.property.Property;
import android.widget.TextView;

/**
 * A property listener that sets the property value as setText() on a text view.
 * @author tomasb
 *
 * @param <T>
 */
public class PropertyTextListener<T> extends WeakReference<TextView> implements IPropertyListener<T> {
	
	/**
	 * Creates the listener, adds it to the property, updates the view with the current property value.
	 * @param view
	 * @param property
	 */
	@SuppressWarnings("unchecked")
	public PropertyTextListener(TextView view, Property<T> property)
	{
		super(view, (ReferenceQueue<TextView>) property.getReferenceQueue());
		property.addListener(this);
		propertyChanged(property.get());
	}

	public void propertyChanged(T value) {
		TextView v = get();
		if(v != null)
			v.setText(String.valueOf(value));
	}
}
