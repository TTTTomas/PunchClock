package se.kingharvest.infrastructure.ui.ex;

import se.kingharvest.infrastructure.property.StringProperty;
import se.kingharvest.infrastructure.system.MethodDelegate;
import se.kingharvest.infrastructure.ui.binding.LayoutBinder;
import se.kingharvest.infrastructure.ui.ex.property.PropertyTextListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Provides a builder interface to the Button.
 * @author tomasb
 *
 */
public class ButtonEx extends ViewExBase<Button, ButtonEx> {
	
    private static final ThreadLocal<ButtonEx> _instance = new ThreadLocal<ButtonEx>()
		{
			@Override 
			protected ButtonEx initialValue() {
                return new ButtonEx();
			}
        };
	
	public static ButtonEx getInstance() {
		return _instance.get();
	}

	public ButtonEx setText(int textId)
	{
		TextView view = _view.get();
		if(view != null)
			view.setText(textId);
		return this;
	}

	public ButtonEx setText(String text)
	{
		TextView view = _view.get();
		if(view != null)
			view.setText(text);
		return this;
	}	
	
	/**
	 * Sets the click event.
	 * @param listener
	 * @return
	 */
	public ButtonEx setOnClick(OnClickListener listener)
	{
		Button view = _view.get();
		if(view != null)
			view.setOnClickListener(listener);
		return this;
	}

	/**
	 * Sets the click event to a named method in the context.
	 * @param methodName
	 * @return
	 */
	public ButtonEx setOnClick(String methodName)
	{
		final MethodDelegate<Void, View> clickMethod = new MethodDelegate<Void, View>(_context, methodName, View.class);

		Button view = _view.get();
		if(view != null)
			view.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					clickMethod.call(v);
				}
			});
		return this;
	}

//	public ButtonEx bindOnClick() {
//		setOnClick(LayoutBinder.getOnClick(_context, _view.getId()));
//		return this;
//	}

	public ButtonEx bindOnClick(Object obj) {
		Button view = _view.get();
		if(view != null)
			setOnClick(LayoutBinder.getOnClick(obj, view.getId()));
		return this;
	}

	public ButtonEx bindText(StringProperty textProperty)
	{
		Button view = _view.get();
		if(view != null)
			new PropertyTextListener<String>(view, textProperty);
		return this;
	}
}
