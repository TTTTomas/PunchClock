package se.kingharvest.infrastructure.ui.ex;

import java.lang.ref.WeakReference;

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
	
	WeakReference<Context> _context;
	WeakReference<V> _view;

	/**
	 * Sets a context and a view to wrap.
	 * 
	 * @param view
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public VEX set(Context context, V view) {
		_context = new WeakReference<Context>(context);
		_view = new WeakReference<V>(view);
		return (VEX) this;
	}

	public V get()
	{
		return _view.get();
	}

	
	@SuppressWarnings("unchecked")
	public VEX setEnabled(boolean enabled)
	{
		View view = _view.get();
		if(view != null)
			view.setEnabled(enabled);
		return (VEX) this;
	}
}
