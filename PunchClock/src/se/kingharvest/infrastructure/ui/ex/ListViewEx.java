package se.kingharvest.infrastructure.ui.ex;

import se.kingharvest.infrastructure.property.CursorProperty;
import se.kingharvest.infrastructure.ui.adapter.RecordAdapter;
import se.kingharvest.infrastructure.ui.ex.property.PropertyRecordAdapterListener;
import android.widget.ListView;


public class ListViewEx extends ViewExBase<ListView, ListViewEx> {

    private static final ThreadLocal<ListViewEx> _instance = new ThreadLocal<ListViewEx>()
		{
			@Override 
			protected ListViewEx initialValue() {
                return new ListViewEx();
			}
        };
	
	public static ListViewEx getInstance() {
		return _instance.get();
	}

	public ListViewEx bindList(CursorProperty cursorProperty, int itemId) {
		
		ListView view = _view.get();
		if(view != null)
		{
			view.setAdapter(new RecordAdapter(_context.get(), cursorProperty.get(), itemId));
			new PropertyRecordAdapterListener(view, cursorProperty);
		}
		return this;
	}
	
//	public ListViewEx bindList(ArrayProperty<?> arrayProperty) {
//
//		ListView view = _view.get();
//		if(view != null)
//			new PropertyTextListener<String>(view, textProperty);
//		return this;
//	}

}
