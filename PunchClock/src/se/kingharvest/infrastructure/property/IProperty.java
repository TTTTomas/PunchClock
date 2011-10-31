package se.kingharvest.infrastructure.property;

public interface IProperty<T> {

	T get();
	void set(T value);
}
