package se.kingharvest.infrastructure.model;

public interface IViewModelEditor<M extends IModel<?>> {
	
	public enum EditMode
	{
		NotEditable,
		Editable,
		Editing
	}

	boolean load(M model);

	void edit();
	
	void cancelEdit();

	boolean save();
	
	EditMode getEditMode();
}
