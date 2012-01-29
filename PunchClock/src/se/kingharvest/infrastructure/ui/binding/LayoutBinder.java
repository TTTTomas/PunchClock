package se.kingharvest.infrastructure.ui.binding;

import java.lang.reflect.Method;

import se.kingharvest.infrastructure.reflection.MethodReflect;
import se.kingharvest.infrastructure.ui.annotation.OnClickAnnotation;
import se.kingharvest.infrastructure.ui.ex.ButtonEx;
import se.kingharvest.infrastructure.ui.ex.SpinnerEx;
import se.kingharvest.infrastructure.ui.ex.TextViewEx;
import se.kingharvest.infrastructure.ui.ex.ViewEx;
import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class LayoutBinder {
	
	public static ViewEx getView(Dialog from, int id)
	{
		ViewEx view = ViewEx.getInstance().set(from.getContext(), from.findViewById(id));
		return view;
	}
	
	public static ViewEx getView(Activity from, int id)
	{
		ViewEx view = ViewEx.getInstance().set(from, from.findViewById(id));
		return view;
	}

	public static TextViewEx getTextView(Dialog from, int id)
	{
		TextViewEx view = TextViewEx.getInstance().set(from.getContext(), (TextView)from.findViewById(id));
		return view;
	}
	
	public static TextViewEx getTextView(Activity from, int id)
	{
		TextViewEx view = TextViewEx.getInstance().set(from, (TextView)from.findViewById(id));
		return view;
	}

	public static ButtonEx getButton(Dialog from, int id)
	{
		ButtonEx view = ButtonEx.getInstance().set(from.getContext(), (Button)from.findViewById(id));
		return view;
	}
	
	public static ButtonEx getButton(Activity from, int id)
	{
		ButtonEx view = ButtonEx.getInstance().set(from, (Button)from.findViewById(id));
		return view;
	}

	public static SpinnerEx getSpinner(Dialog from, int id)
	{
		SpinnerEx view = SpinnerEx.getInstance().set(from.getContext(), (Spinner)from.findViewById(id));
		return view;
	}
	
	public static SpinnerEx getSpinner(Activity from, int id)
	{
		SpinnerEx view = SpinnerEx.getInstance().set(from, (Spinner)from.findViewById(id));
		return view;
	}

	/**
	 * Returns an OnClickListener calling the method annotated with 
	 * OnClick(id) in a given object.
	 * @param id
	 * @return
	 */
	public static View.OnClickListener getOnClick(final Object object, int id)
	{
		final Method method = OnClickAnnotation.getInstance().getAnnotatedMethod(object, id);

		return new View.OnClickListener()
		{					
			public void onClick(View v) {
				MethodReflect.call(object, method, v);
			}
		};
	}
}
