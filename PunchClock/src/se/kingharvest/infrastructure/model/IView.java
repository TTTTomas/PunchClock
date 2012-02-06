package se.kingharvest.infrastructure.model;

public interface IView<VM extends IViewModel/*<?>*/> {

	VM createViewModel();
	
	//VM getViewModel();
	
	//void setViewModel(VM viewModel);
	
    public void bindView();

    void bindViewModel();
}
