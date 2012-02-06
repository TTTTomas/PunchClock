package se.kingharvest.infrastructure.ui.ex.property;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import se.kingharvest.infrastructure.diagnostics.Logger;
import se.kingharvest.infrastructure.property.IPropertyListener;
import se.kingharvest.infrastructure.property.Property;
import android.database.Cursor;
import android.widget.Adapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

/**
 * A property listener that sets the property value as setAdapter(RecordAdapter) on a list view.
 * @author tomasb
 *
 * @param <T>
 */
public class PropertyRecordAdapterListener extends WeakReference<ListView> implements IPropertyListener<Cursor> {
	
	protected static final String LOG_TAG = "PropertyRecordAdapterListener";
	
	/**
	 * Creates the listener, adds it to the property, updates the view with the current property value.
	 * @param view
	 * @param property
	 */
	@SuppressWarnings("unchecked")
	public PropertyRecordAdapterListener(ListView view, Property<Cursor> property)
	{
		super(view, (ReferenceQueue<ListView>) property.getReferenceQueue());
		property.addListener(this);
		propertyChanged(property.get());
	}

	public void propertyChanged(Cursor cursor) {
		ListView listView = get();
		if(listView != null)
		{
			Adapter adapter = listView.getAdapter();
			if (adapter instanceof CursorAdapter)
				((CursorAdapter) adapter).changeCursor(cursor);
			else
				Logger.warning(LOG_TAG, "No CursorAdapter is set on this ListView, cannot update cursor.");
		}
	}
}
