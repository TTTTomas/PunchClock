package se.kingharvest.infrastructure.ui.ex;

import se.kingharvest.infrastructure.system.MethodDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
		_view.setText(textId);
		return this;
	}

	public ButtonEx setText(String text)
	{
		_view.setText(text);
		return this;
	}
	
	/**
	 * Sets the click event.
	 * @param listener
	 * @return
	 */
	public ButtonEx setOnClick(OnClickListener listener)
	{
		_view.setOnClickListener(listener);
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

		_view.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				clickMethod.call(v);
			}
		});
		return this;
	}
}