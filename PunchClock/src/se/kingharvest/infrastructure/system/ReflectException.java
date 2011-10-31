package se.kingharvest.infrastructure.system;

public class ReflectException extends IllegalArgumentException
{
	private static final long serialVersionUID = -5842402292282752063L;

	public ReflectException(String message, Throwable t) {
		super(message, t);
	}
}
