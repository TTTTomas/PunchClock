package se.kingharvest.infrastructure.ui.util;

import java.io.Serializable;
import java.util.Date;

import se.kingharvest.infrastructure.system.ISO8601Date;
import se.kingharvest.infrastructure.system.Types;
import android.content.Intent;

public class IntentUtils {

	private static final String SE_KINGHARVEST_INTENTUTILS_ARGUMENT_TYPE_ 	= "SE_KINGHARVEST_INTENTUTILS_ARGUMENT_TYPE_";
	private static final String SE_KINGHARVEST_INTENTUTILS_ARGUMENT_ 		= "SE_KINGHARVEST_INTENTUTILS_ARGUMENT_";

	public static <A1> void putArgumentInIntent(Intent intent, int i, A1 arg1)
	{
        String argType	= SE_KINGHARVEST_INTENTUTILS_ARGUMENT_TYPE_ + i;
        String argLabel 	= SE_KINGHARVEST_INTENTUTILS_ARGUMENT_ + i;

        // Handle void.
        String argClassName = arg1 == null ? Void.class.getSimpleName() : arg1.getClass().getSimpleName();
        Class<?> type = arg1 == null ? Void.class : arg1.getClass();
        
        intent.putExtra(argType, argClassName);
        
        if(Types.isVoid(type))
        {
        	// Do nothing, no point in packaging a null value.
        }        	
        else if(Types.isInteger(type))
        	intent.putExtra(argLabel, (Integer)arg1);
        else if(Types.isLong(type))
        	intent.putExtra(argLabel, (Long)arg1);
        else if(Types.isShort(type))
        	intent.putExtra(argLabel, (Short)arg1);
        else if(Types.isDouble(type))
            intent.putExtra(argLabel, (Double)arg1);
        else if(Types.isFloat(type))
            intent.putExtra(argLabel, (Float)arg1);
        else if(Types.isString(type))
            intent.putExtra(argLabel, (String)arg1);
        else if(Types.isBoolean(type))
            intent.putExtra(argLabel, (Boolean)arg1);
        else if(Types.isChar(type))
            intent.putExtra(argLabel, (Character)arg1);
        else if(Types.isByte(type))
            intent.putExtra(argLabel, (Byte)arg1);
        else if(Types.isDate(type))
            intent.putExtra(argLabel, ISO8601Date.toString((Date)arg1));
		
        else if(Types.isLongArray(type))
        	intent.putExtra(argLabel, (long[])arg1);
        else if(Types.isIntegerArray(type))
        	intent.putExtra(argLabel, (int[])arg1);
        else if(Types.isShortArray(type))
        	intent.putExtra(argLabel, (short[])arg1);
        else if(Types.isCharArray(type))
            intent.putExtra(argLabel, (char[])arg1);
        else if(Types.isByteArray(type))
            intent.putExtra(argLabel, (byte[])arg1);
        else if(Types.isDoubleArray(type))
            intent.putExtra(argLabel, (double[])arg1);
        else if(Types.isFloatArray(type))
        	intent.putExtra(argLabel, (float[])arg1);
        else if(Types.isStringArray(type))
            intent.putExtra(argLabel, (String[])arg1);
        else if(Types.isByteArray(type))
            intent.putExtra(argLabel, (byte[])arg1);
        else if(Types.isDateArray(type))
            intent.putExtra(argLabel, ISO8601Date.toStringArray((Date[])arg1));

        else if(Types.isSerializable(type))
            intent.putExtra(argLabel, (Serializable)arg1);
        else
        	throw new IllegalArgumentException("Type " + type + " is not an allowed type.");
	}

	public static Object getArgumentFromIntent(Intent intent, int i) {
		String type = intent.getStringExtra(SE_KINGHARVEST_INTENTUTILS_ARGUMENT_TYPE_ + i);
		String argLabel = SE_KINGHARVEST_INTENTUTILS_ARGUMENT_ + i;
		
        if(Types.isVoid(type))
        	return null;
        else if(Types.isInteger(type))
        	return intent.getIntExtra(argLabel, 0);
        else if(Types.isLong(type))
        	return intent.getLongExtra(argLabel, 0);
        else if(Types.isShort(type))
        	return intent.getShortExtra(argLabel, (short)0);
        else if(Types.isByte(type))
        	return intent.getByteExtra(argLabel, (byte)0);
        else if(Types.isDouble(type))
        	return intent.getDoubleExtra(argLabel, 0);
        else if(Types.isFloat(type))
        	return intent.getFloatExtra(argLabel, 0);
        else if(Types.isString(type))
        	return intent.getStringExtra(argLabel);
        else if(Types.isChar(type))
        	return intent.getCharExtra(argLabel, (char)0);
        else if(Types.isBoolean(type))
        	return intent.getBooleanExtra(argLabel, false);
        else if(Types.isDate(type))
        	return ISO8601Date.parse(intent.getStringExtra(argLabel));
		
        else if(Types.isIntegerArray(type))
        	return intent.getIntArrayExtra(argLabel);
        else if(Types.isLongArray(type))
        	return intent.getLongArrayExtra(argLabel);
        else if(Types.isShortArray(type))
        	return intent.getShortArrayExtra(argLabel);
        else if(Types.isByteArray(type))
        	return intent.getByteArrayExtra(argLabel);
        else if(Types.isDoubleArray(type))
        	return intent.getDoubleArrayExtra(argLabel);
        else if(Types.isFloatArray(type))
        	return intent.getFloatArrayExtra(argLabel);
        else if(Types.isStringArray(type))
        	return intent.getStringArrayExtra(argLabel);
        else if(Types.isCharArray(type))
        	return intent.getCharArrayExtra(argLabel);
        else if(Types.isBooleanArray(type))
        	return intent.getBooleanArrayExtra(argLabel);
        else if(Types.isDateArray(type))
        	return ISO8601Date.parseArray(intent.getStringArrayExtra(argLabel));

        else if(Types.isSerializable(type))
        	return intent.getSerializableExtra(argLabel);
        
        throw new IllegalArgumentException("Type " + type + " is not an allowed type.");
	}
}
