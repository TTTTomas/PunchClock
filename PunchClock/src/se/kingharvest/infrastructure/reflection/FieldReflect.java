package se.kingharvest.infrastructure.reflection;

import java.lang.reflect.Field;
import java.util.Date;

import se.kingharvest.infrastructure.collection.HashMapMap;
import se.kingharvest.infrastructure.data.types.Id;
import se.kingharvest.infrastructure.data.types.PrimaryId;
import se.kingharvest.infrastructure.system.ISO8601Date;
import se.kingharvest.infrastructure.system.Types;


/**
 * Contains convenience methods for reflective access to fields and their values.
 * Caches the accessed fields for faster reuse.
 * 
 * @author tomasb
 *
 */
public class FieldReflect {

	private static HashMapMap<Class<?>, String, Field> _fieldCache;
	public static Field getField(String fieldName, Object object)
	{
		Class<?> type = object.getClass();
		
		if(_fieldCache == null)
			_fieldCache = new HashMapMap<Class<?>, String, Field>();
		else
			if(_fieldCache.containsKeys(type, fieldName))
				return _fieldCache.get(type, fieldName);
		
		try {
			Field field = type.getField(fieldName);
			_fieldCache.put(type, fieldName, field);
			return field;
		} catch (Exception e) {
			throw new ReflectException(fieldName, object, e);
		}
	}
	

	public static long getLong(String fieldName, Object object){
		try{
			return getField(fieldName, object).getLong(object);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, e);
		}		
	}

	public static int getInt(String fieldName, Object object){
		try {
			return getField(fieldName, object).getInt(object);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, e);
		}		
	}

	public static byte getByte(String fieldName, Object object){
		try {
			return getField(fieldName, object).getByte(object);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, e);
		}		
	}

	public static char getChar(String fieldName, Object object){
		try {
			return getField(fieldName, object).getChar(object);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, e);
		}		
	}

	public static boolean getBoolean(String fieldName, Object object){
		try {
			return getField(fieldName, object).getBoolean(object);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, e);
		}		
	}

	public static double getDouble(String fieldName, Object object){
		try {
			return getField(fieldName, object).getDouble(object);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, e);
		}		
	}

	public static float getFloat(String fieldName, Object object){
		try {
			return getField(fieldName, object).getFloat(object);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, e);
		}		
	}

	public static String getDateString(String fieldName, Object object){
		try {
			return ISO8601Date.toString((Date)getField(fieldName, object).get(object));
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, e);
		}		
	}

	public static byte[] getByteArray(String fieldName, Object object){
		try {
			return (byte[]) getField(fieldName, object).get(object);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, e);
		}		
	}

	public static long getId(String fieldName, Object object){
		try {
			Object fieldValue = getField(fieldName, object).get(object);
			if(fieldValue == null)
				return 0;
			else
				return ((Id)fieldValue).get();
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, e);
		}		
	}

	public static Object get(String fieldName, Object object){
		try {
			return getField(fieldName, object).get(object);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, e);
		}		
	}

	public static void setBoolean(String fieldName, Object object, boolean value)
	{
		try {
			getField(fieldName, object).setBoolean(object, value);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, value, e);
		}
	}

	public static void setInt(String fieldName, Object object, int value)
	{
		try {
			getField(fieldName, object).setInt(object, value);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, value, e);
		}
	}

	public static void setLong(String fieldName, Object object, long value)
	{
		try {
			getField(fieldName, object).setLong(object, value);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, value, e);
		}
	}

	public static void setShort(String fieldName, Object object, short value)
	{
		try {
			getField(fieldName, object).setShort(object, value);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, value, e);
		}
	}

	public static void setFloat(String fieldName, Object object, Float value)
	{
		try {
			getField(fieldName, object).setFloat(object, value);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, value, e);
		}
	}

	public static void setDouble(String fieldName, Object object, Double value)
	{
		try {
			getField(fieldName, object).setDouble(object, value);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, value, e);
		}
	}

	public static void setByte(String fieldName, Object object, byte value)
	{
		try {
			getField(fieldName, object).setByte(object, value);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, value, e);
		}
	}

	public static void setChar(String fieldName, Object object, char value)
	{
		try {
			getField(fieldName, object).setChar(object, value);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, value, e);
		}
	}

	public static void setDateString(String fieldName, Object object, String value)
	{
		try {
			getField(fieldName, object).set(object, ISO8601Date.parse(value));
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, value, e);
		}
	}

	public static void setId(String fieldName, Object object, long value)
	{
		try {
			Field field = getField(fieldName, object);
			Class<?> fieldType = field.getType();
			Object fieldValue = field.get(object);
			if(fieldValue == null)
			{
				if(Types.isId(fieldType))
					set(fieldName, object, new Id(value));
				else if(Types.isPrimaryId(fieldType))
					set(fieldName, object, new PrimaryId(value));
				else
					throw new ReflectException(fieldName, object, value);
			}
			else
			{
				((Id)fieldValue).set(value);
			}
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, value, e);
		}
	}

	public static void set(String fieldName, Object object, Object value)
	{
		try {
			getField(fieldName, object).set(object, value);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, value, e);
		}
	}

	public static void setString(String fieldName, Object object, String value)
	{
		try {
			getField(fieldName, object).set(object, value);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, value, e);
		}
	}

	public static void setByteArrayClone(String fieldName, Object object, byte[] value)
	{
		try {
			getField(fieldName, object).set(object, value.clone());
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, value, e);
		}
	}

	public static short getShort(String fieldName, Object object){
		try {
			return getField(fieldName, object).getShort(object);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, e);
		}		
	}

	public static String getString(String fieldName, Object object){
		try {
			return (String) getField(fieldName, object).get(object);
		} catch (IllegalAccessException e) {
			throw new ReflectException(fieldName, object, e);
		}		
	}

}
