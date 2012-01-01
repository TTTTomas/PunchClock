package se.kingharvest.punchclock.dialogs;

import se.kingharvest.infrastructure.ui.ActivityBase;
import se.kingharvest.infrastructure.ui.DialogBase;
import se.kingharvest.infrastructure.ui.annotation.OnClick;
import se.kingharvest.punchclock.R;
import se.kingharvest.punchclock.entity.Project;
import android.view.View;

public class NewProjectDialog extends DialogBase
{
	public interface OnNewProjectOkListener {
		
		public void newProjectOk(Project newProject);
	}

	OnNewProjectOkListener _listener;
	
	public NewProjectDialog(ActivityBase<?, ?> context) {
		super(context);
		
		bindView();
	}
	
	private void bindView() {
		
		getButton(R.id.NewProject_OkButton)
			.bindOnClick(this);
		getButton(R.id.NewProject_CancelButton)
			.bindOnClick(this);
	}
	
	@OnClick(R.id.NewProject_OkButton)
	public void onOkButtonClicked(View v)
	{
		if(_listener != null)
		{
			String projectName = getTextView(R.id.NewProject_NameEdit).getText();
			Project newProject = new Project(projectName);
			_listener.newProjectOk(newProject);
		}
		dismiss();
	}

	@OnClick(R.id.NewProject_CancelButton)
	public void onCancelButtonClicked(View v){
		
		dismiss();
	}
	
	@Override
	public void onBackPressed() {
		
		getButton(R.id.NewProject_CancelButton)
			.get().performClick();
	}

	public void setOnNewJobOkListener(OnNewProjectOkListener onNewProjectOkListener) {
		
		_listener = onNewProjectOkListener;
	}
	
	@Override
	public int getContentView() {
		
		return R.layout.new_project_dialog;
	}
}
