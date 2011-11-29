package se.kingharvest.infrastructure.system;

import java.util.Date;

public class Types {

	public static boolean isLong(Class<?> type) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean isBooleanArray(String type) {
		return type.equals(Boolean[].class.getSimpleName()) || type.equals(boolean[].class.getSimpleName());
	}

	public static boolean isStringArray(String type) {
		return type.equals(String[].class.getSimpleName());
	}

	public static boolean isDoubleArray(String type) {
		return type.equals(Double[].class.getSimpleName()) || type.equals(double[].class.getSimpleName());
	}

	public static boolean isIntegerArray(String type) {
		return type.equals(Integer[].class.getSimpleName()) || type.equals(int[].class.getSimpleName());
	}

	public static boolean isBoolean(String type) {
		return type.equals(Boolean.class.getSimpleName()) || type.equals(boolean.class.getSimpleName());
	}

	public static boolean isString(String type) {
		return type.equals(String.class.getSimpleName());
	}

	public static boolean isDouble(String type) {
		return type.equals(Double.class.getSimpleName()) || type.equals(double.class.getSimpleName());
	}

	public static boolean isInteger(String type) {
		return type.equals(Integer.class.getSimpleName()) || type.equals(int.class.getSimpleName());
	}

	public static boolean isBooleanArray(Class<?> type) {
		return type.equals(Boolean[].class) || type.equals(boolean[].class);
	}

	public static boolean isStringArray(Class<?> type) {
		return type.equals(String.class);
	}

	public static boolean isDoubleArray(Class<?> type) {
		return type.equals(Double[].class) || type.equals(double[].class);
	}

	public static boolean isIntegerArray(Class<?> type) {
		return type.equals(Integer[].class) || type.equals(int[].class);
	}

	public static boolean isBoolean(Class<?> type) {
		return type.equals(Boolean.class) || type.equals(boolean.class);
	}

	public static boolean isString(Class<?> type) {
		return type.equals(String.class);
	}

	public static boolean isDouble(Class<?> type) {
		return type.equals(Double.class) || type.equals(double.class);
	}

	public static boolean isInteger(Class<?> type) {
		return type.equals(Integer.class) || type.equals(int.class);
	}

	public static boolean isShort(Class<?> type) {
		return type.equals(Short.class) || type.equals(short.class);
	}

	public static boolean isFloat(Class<?> type) {
		return type.equals(Float.class) || type.equals(float.class);
	}

	public static boolean isDate(Class<?> type) {
		return type.equals(Date.class);
	}

	public static boolean isByteArray(Class<?> type) {
		return type.equals(byte[].class) || type.equals(Byte[].class);
	}
}
