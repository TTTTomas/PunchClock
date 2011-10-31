package se.kingharvest.infrastructure.ui.ex;

import android.content.Context;
import android.view.View;

/**
 * Wraps a standard view with the more versatile ViewExBase, 
 * to be subclassed as ButtonEx, SpinnerEx. The purpose of each 
 * subclass is to provide a builder-like interface to the view.
 * 
 * @author tomasb
 *
 * @param <V>
 * @param <VEX>
 */
public class ViewExBase<V extends View, VEX extends ViewExBase<V, VEX>> {
	
	Context _context;
	V _view;

	/**
	 * Sets a context and a view to wrap.
	 * 
	 * @param view
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public VEX set(Context context, V view) {
		_context = context;
		_view = view;
		return (VEX) this;
	}

	public V get()
	{
		return _view;
	}
}
