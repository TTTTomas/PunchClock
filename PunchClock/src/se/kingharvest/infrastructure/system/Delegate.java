package se.kingharvest.infrastructure.system;

public abstract class Delegate<R, T1> {

	public abstract R call(T1 arg);

}
