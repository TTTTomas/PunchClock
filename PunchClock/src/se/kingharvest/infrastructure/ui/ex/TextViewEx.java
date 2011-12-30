package se.kingharvest.infrastructure.ui.ex;

import se.kingharvest.infrastructure.property.IPropertyListener;
import se.kingharvest.infrastructure.property.StringProperty;
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
		_view.setText(textId);
		return this;
	}

	public TextViewEx setText(String text)
	{
		_view.setText(text);
		return this;
	}

	public String getText()
	{
		return _view.getText().toString();
	}

	public TextViewEx bindText(StringProperty textProperty)
	{
		final TextView realView = _view;
		textProperty.Listeners.add(new IPropertyListener<String>() {

			public void propertyChanged(String value) {
				realView.setText(value);
			}
		});
		_view.setText(textProperty.get());
		return this;
	}
}
