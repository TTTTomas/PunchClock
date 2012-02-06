package se.kingharvest.infrastructure.property;


public class BooleanProperty extends Property<Boolean>{

	public BooleanProperty() {
		set(false);
	}

	public BooleanProperty(boolean b) {
		set(b);
	}

	private static final long serialVersionUID = 5554241819656353305L;

}
