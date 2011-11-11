package se.kingharvest.punchclock;

import se.kingharvest.infrastructure.ui.ActivityBase;
import se.kingharvest.infrastructure.ui.DialogBase;
import se.kingharvest.infrastructure.ui.DialogManager;
import se.kingharvest.infrastructure.ui.annotation.OnActivityResult;
import se.kingharvest.infrastructure.ui.annotation.OnClick;
import se.kingharvest.punchclock.pages.EntriesPage;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;


public class PunchClockActivity extends ActivityBase<PunchClockActivity, PunchClockViewModel> {
    
	@SuppressWarnings("unused")
	private static final String LOG_TAG = PunchClockActivity.class.getSimpleName();
	
	public int getContentView() {
		return R.layout.main;
	}
	
	public PunchClockViewModel createViewModel() {
		return new PunchClockViewModel(this);
	}

	public void bindView()
    {
        getButton(R.id.punchclock_button)
        	.setOnClick(onClick(R.id.punchclock_button));

        getButton(R.id.punchclock_go_to_entries)
        	.setOnClick(onClick(R.id.punchclock_go_to_entries));

        getButton(R.id.punchclock_show_dialog_button)
        	.setOnClick(onClick(R.id.punchclock_show_dialog_button));
    }

	public void bindViewModel() 
	{
        getTextView(R.id.punchclock_text_property)
        	.bindText(_viewModel.CounterText);

        getTextView(R.id.punchclock_just_text)
        	.setText("Just text.");
	}	
	
	@OnClick(R.id.punchclock_button)
	public void punch(View button)
	{
		_viewModel.setCounter(_viewModel.getCounter()+1);
		
		Dialog d = new DialogBase(this);
		d.setTitle("Hello");
		d.show();
		
		getTextView(R.id.punchclock_just_text).setText("This happens while the dialog shows.");
	}

	Dialog a;
	Dialog b;
	Dialog c;
	
	@OnClick(R.id.punchclock_show_dialog_button)
	public void showDialog(View v)
	{
		a = new DialogBase(this);
		a.setTitle("A!");

		b = new DialogBase(this);
		b.setTitle("B!");

		c = new DialogBase(this);
		c.setTitle("C!");

		new Thread(new Runnable() {			
			public void run() {
				dialogWorkflow();
			}
		}).start();
	}
	
	private void dialogWorkflow()
	{
		DialogManager.show(this, a);

		//getTextView(R.id.punchclock_just_text).setText("A done.");

		DialogManager.show(this, b);

		//getTextView(R.id.punchclock_just_text).setText("B done.");

		DialogManager.show(this, c);

		//getTextView(R.id.punchclock_just_text).setText("C done.");
	}
	
	@OnClick(R.id.punchclock_go_to_entries)
	public void goToEntries(View button)
	{
		navigateForResult(EntriesPage.class, EntriesPage.SHOW_ENTRIES);
	}
	
	@OnActivityResult(EntriesPage.SHOW_ENTRIES)
	public void returnFromEntries(int resultCode, Intent result)
	{
		_viewModel.setCounter(_viewModel.getCounter()+resultCode);
	}
	
}
