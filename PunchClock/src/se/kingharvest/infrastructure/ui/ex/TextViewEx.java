package se.kingharvest.infrastructure.ui.ex;

import se.kingharvest.infrastructure.property.StringProperty;
import se.kingharvest.infrastructure.ui.ex.property.PropertyTextListener;
import android.widget.TextView;

public class TextViewEx extends ViewExBase<TextView, TextViewEx> {
	
    private static final ThreadLocal<TextViewEx> _instance = new ThreadLocal<TextViewEx>()
		{
			@Override 
			protected TextViewEx initialValue() {
                return new TextViewEx();
			}
        };
	
	public static TextViewEx getInstance() {
		return _instance.get();
	}

	public TextViewEx setText(int textId)
	{
		TextView view = _view.get();
		if(view != null)
			view.setText(textId);
		return this;
	}

	public TextViewEx setText(String text)
	{
		TextView view = _view.get();
		if(view != null)
			view.setText(text);
		return this;
	}

	public String getText()
	{
		TextView view = _view.get();
		if(view != null)
			return view.getText().toString();
		return "";
	}

	public TextViewEx bindText(StringProperty textProperty)
	{
		TextView view = _view.get();
		if(view != null)
			new PropertyTextListener<String>(view, textProperty);
		return this;
	}
}
