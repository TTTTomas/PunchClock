package se.kingharvest.infrastructure.property;

import java.io.Serializable;

public interface IProperty<T> extends Serializable
{
	T get();
	void set(T value);
}
