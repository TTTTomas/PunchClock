package se.kingharvest.infrastructure.data;

public interface IDatabase {

	boolean create();
	boolean upgrade();
}
