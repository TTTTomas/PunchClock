package se.kingharvest.infrastructure.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import se.kingharvest.infrastructure.android.Resources;
import se.kingharvest.infrastructure.ui.Views;
import se.kingharvest.punchclock.application.ClientContext;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * This adapter will bind cursor columns to matching ids in the given item view.
 * @author tomasb
 *
 */
public class RecordAdapter extends CursorAdapter{
	
	int _itemViewId;
	
	HashMap<String, Integer> _columnsToBind;

	public RecordAdapter(Context context, Cursor c, int itemViewId) {
		super(context, c);
		
		_itemViewId = itemViewId;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(_itemViewId, parent, false);
		bindView(v, context, cursor);
		return v;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {

		if(_columnsToBind == null)
		{
			// Find views which maps to column names by id name, and cache the name-id mapping.
			_columnsToBind = new HashMap<String, Integer>();
			ArrayList<Integer> ids = Views.getAllChildIds(view);
			int idCount = ids.size();
			for (int i = 0; i < idCount; i++) {
				int id = ids.get(i);
				
				// If the id name matches a column id...
				String name = Resources.getResourceNameById(ClientContext.IdResourceClass, id);
				if(cursor.getColumnIndex(name) != -1)
				{
					// ...and there is a view with that id, then set the view value, and store the name-id mapping
					View v = view.findViewById(id);
					if(v != null)
					{
						_columnsToBind.put(name, id);
						Views.setValue(v, cursor.getString(cursor.getColumnIndex(name)));
					}
				}
			}			
		}
		else
		{			
			// Loop over all mapped columns and set the value of mapped view resource id to the named cursor column value.
			for (String name : _columnsToBind.keySet()) {
				
				View v = view.findViewById(_columnsToBind.get(name));
				Views.setValue(v, cursor.getString(cursor.getColumnIndex(name)));
			}
		}
	}

}
