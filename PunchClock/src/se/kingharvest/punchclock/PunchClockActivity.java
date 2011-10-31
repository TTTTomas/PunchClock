package se.kingharvest.punchclock;

import se.kingharvest.infrastructure.ui.ActivityBase;
import se.kingharvest.infrastructure.ui.annotation.OnClick;
import android.view.View;

import com.example.android.google.apis.R;

public class PunchClockActivity extends ActivityBase<PunchClockActivity, PunchClockViewModel> {
    
	@SuppressWarnings("unused")
	private static final String LOG_TAG = PunchClockActivity.class.getSimpleName();
	
	//@LayoutId
	private int _layout = R.layout.main;
	
	public int getContentView() {
		// TODO: method or annotation?
		return _layout;
	}

	public PunchClockViewModel createViewModel() {
		// TODO: method or reflection-based instancing?
		return new PunchClockViewModel(this);
	}

	public void bindView()
    {
		// TODO: unbinding view models?
		// TODO: unbinding ex-objects?
        getButton(R.id.punchclock_button)
        	.setOnClick(onClick(R.id.punchclock_button));
    }

	public void bindViewModel() 
	{
		// TODO: unbinding text properties?
        getTextView(R.id.punchclock_text_property)
        	.bindText(_viewModel.CounterText);

        getTextView(R.id.punchclock_just_text)
        	.setText("Just text: " + _viewModel.getCounter());
	}	
	
	@OnClick(R.id.punchclock_button)
	public void punch(View button)
	{
		_viewModel.setCounter(_viewModel.getCounter()+1);

        getTextView(R.id.punchclock_just_text)
        	.setText("Just text: " + _viewModel.getCounter());
	}
}
