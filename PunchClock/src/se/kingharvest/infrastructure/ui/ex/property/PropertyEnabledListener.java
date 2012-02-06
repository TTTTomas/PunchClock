package se.kingharvest.infrastructure.ui.ex.property;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import se.kingharvest.infrastructure.property.IPropertyListener;
import se.kingharvest.infrastructure.property.Property;
import android.view.View;

/**
 * A property listener that sets the property boolean value as setEnabled() on a view.
 * @author tomasb
 *
 * @param <T>
 */
public class PropertyEnabledListener extends WeakReference<View> implements IPropertyListener<Boolean> {
	
	/**
	 * Creates the listener, adds it to the property, updates the view with the current property value.
	 * @param view
	 * @param property
	 */
	@SuppressWarnings("unchecked")
	public PropertyEnabledListener(View view, Property<Boolean> property)
	{
		super(view, (ReferenceQueue<View>) property.getReferenceQueue());
		property.addListener(this);
		propertyChanged(property.get());
	}

	public void propertyChanged(Boolean value) {
		View v = get();
		if(v != null)
			v.setEnabled(Boolean.TRUE.equals(value));
	}
}
