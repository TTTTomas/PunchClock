package se.kingharvest.infrastructure.system;

public class ReflectException extends IllegalArgumentException
{
	private static final long serialVersionUID = -5842402292282752063L;

	public ReflectException(String message, Throwable t) {
		super(message, t);
	}

	public ReflectException(String fieldName, Object object, Throwable t) {
		super("Failed to read field " + fieldName + " in object of type " + Reflect.getClassSimpleName(object), t);
	}

	public ReflectException(String fieldName, Object object, Object value, Throwable t) {
		super("Failed to write field " + fieldName + " in object of type " + Reflect.getClassSimpleName(object) + " with value " + value, t);
	}

	public ReflectException(String fieldName, Object object, long value) {
		super("Failed to write field " + fieldName + " in object of type " + Reflect.getClassSimpleName(object) + " with value " + value);
	}
}
