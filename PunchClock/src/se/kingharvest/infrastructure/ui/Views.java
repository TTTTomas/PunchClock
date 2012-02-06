package se.kingharvest.infrastructure.ui;

import java.util.ArrayList;

import se.kingharvest.infrastructure.system.Strings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

public class Views {

	/**
	 * Returns all defined ids in a view hierarchy.
	 * @param view
	 * @return
	 */
	public static ArrayList<Integer> getAllChildIds(View view)
	{
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		int rootId = view.getId();		
		if (rootId != View.NO_ID)
			ids.add(rootId);
		
		if(view instanceof ViewGroup)
			getAllChildIds((ViewGroup) view, ids);
		
		return ids;
	}
		
	private static void getAllChildIds(ViewGroup viewGroup, ArrayList<Integer> ids)
	{
		int childCount = viewGroup.getChildCount();
		for (int i = 0; i < childCount; i++) {

			View v = viewGroup.getChildAt(i);
			int id = v.getId();
			if (id != View.NO_ID)
				ids.add(id);
			
			if(v instanceof ViewGroup)
				getAllChildIds((ViewGroup) v, ids);	
		}
	}

	public static void setValue(View view, String value) {
		
		if(view instanceof TextView)
			((TextView)view).setText(value);
		else if (view instanceof CheckBox)
		{
			boolean boolValue = Strings.toBoolean(value);
			((CheckBox)view).setChecked(boolValue);
		}
	}
}