package se.kingharvest.infrastructure.system;

import java.io.Serializable;
import java.util.Date;

import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.data.types.PrimaryId;

/**
 * Contains type detection for all primitive types plus String and Date.
 */
public class Types {

	public static boolean isLong(Class<?> type) {
		return type.equals(Long.class) || type.equals(long.class);
	}

	public static boolean isLong(String type) {
		return type.equals(Long.class.getSimpleName()) || type.equals(long.class.getSimpleName());
	}

	public static boolean isLongArray(Class<?> type) {
		return type.equals(long[].class);
	}

	public static boolean isLongArray(String type) {
		return type.equals(long[].class.getSimpleName());
	}

	public static boolean isBooleanArray(String type) {
		return type.equals(boolean[].class.getSimpleName());
	}

	public static boolean isStringArray(String type) {
		return type.equals(String[].class.getSimpleName());
	}

	public static boolean isDoubleArray(String type) {
		return type.equals(double[].class.getSimpleName());
	}

	public static boolean isIntegerArray(String type) {
		return type.equals(int[].class.getSimpleName());
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
		return type.equals(boolean[].class);
	}

	public static boolean isCharArray(Class<?> type) {
		return type.equals(char[].class);
	}

	public static boolean isCharArray(String type) {
		return type.equals(char[].class.getSimpleName());
	}

	public static boolean isStringArray(Class<?> type) {
		return type.equals(String.class);
	}

	public static boolean isDoubleArray(Class<?> type) {
		return type.equals(double[].class);
	}

	public static boolean isIntegerArray(Class<?> type) {
		return type.equals(int[].class);
	}

	public static boolean isBoolean(Class<?> type) {
		return type.equals(Boolean.class) || type.equals(boolean.class);
	}

	public static boolean isString(Class<?> type) {
		return type.equals(String.class);
	}

	public static boolean isChar(Class<?> type) {
		return type.equals(Character.class) || type.equals(char.class);
	}

	public static boolean isChar(String type) {
		return type.equals(Character.class.getSimpleName()) || type.equals(char.class.getSimpleName());
	}

	public static boolean isByte(Class<?> type) {
		return type.equals(Byte.class) || type.equals(byte.class);
	}

	public static boolean isByte(String type) {
		return type.equals(Byte.class.getSimpleName()) || type.equals(byte.class.getSimpleName());
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

	public static boolean isShort(String type) {
		return type.equals(Short.class.getSimpleName()) || type.equals(short.class.getSimpleName());
	}

	public static boolean isShortArray(Class<?> type) {
		return type.equals(short[].class);
	}

	public static boolean isShortArray(String type) {
		return type.equals(short[].class.getSimpleName());
	}

	public static boolean isFloat(Class<?> type) {
		return type.equals(Float.class) || type.equals(float.class);
	}

	public static boolean isFloat(String type) {
		return type.equals(Float.class.getSimpleName()) || type.equals(float.class.getSimpleName());
	}

	public static boolean isFloatArray(Class<?> type) {
		return type.equals(float[].class);
	}

	public static boolean isFloatArray(String type) {
		return type.equals(float[].class.getSimpleName());
	}

	public static boolean isDate(Class<?> type) {
		return type.equals(Date.class);
	}

	public static boolean isDate(String type) {
		return type.equals(Date.class.getSimpleName());
	}

	public static boolean isDateArray(Class<?> type) {
		return type.equals(Date[].class);
	}

	public static boolean isDateArray(String type) {
		return type.equals(Date[].class.getSimpleName());
	}

	public static boolean isByteArray(Class<?> type) {
		return type.equals(Byte[].class);
	}

	public static boolean isByteArray(String type) {
		return type.equals(Byte[].class.getSimpleName());
	}

	public static boolean isSerializable(Class<?> type) {
		return (type instanceof Serializable);
	}

	public static boolean isSerializable(String type) {
		return type.equals(Serializable.class.getSimpleName());
	}

	public static boolean isVoid(Class<?> type) {
		return type.equals(Void.class);
	}

	public static boolean isVoid(String type) {
		return type.equals(Void.class.getSimpleName());
	}

	public static boolean isId(Class<?> type) {
		return type.equals(Id.class);
	}

	public static boolean isPrimaryId(Class<?> type) {
		return type.equals(PrimaryId.class);
	}
}
