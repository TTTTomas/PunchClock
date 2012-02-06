package se.kingharvest.punchclock.pages;

import se.kingharvest.infrastructure.model.ViewModelBase;
import se.kingharvest.infrastructure.property.CursorProperty;
import se.kingharvest.punchclock.data.tables.WorkPeriodTable;

public class EntriesViewModel extends ViewModelBase<EntriesViewModel>{

	private static final long serialVersionUID = -1292605067666201940L;

	public CursorProperty WorkPeriodCursorProperty = new CursorProperty();
	
	public EntriesViewModel() {
		
		WorkPeriodCursorProperty.set(WorkPeriodTable.getTable().cursorSelectAll());
	}

}
