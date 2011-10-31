package se.kingharvest.infrastructure.property;

public interface IPropertyListener<T> {

	void propertyChanged(T value);
}
