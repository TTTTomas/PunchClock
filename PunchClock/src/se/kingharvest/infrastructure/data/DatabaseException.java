package se.kingharvest.infrastructure.data;

public class DatabaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1597493163801211271L;

	public DatabaseException(String message, Throwable t) {
		super(message, t);
	}

}
