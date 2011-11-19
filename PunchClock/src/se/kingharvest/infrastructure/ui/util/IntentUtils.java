package se.kingharvest.infrastructure.ui.util;

import se.kingharvest.infrastructure.system.Reflect;
import android.content.Intent;

public class IntentUtils {

	private static final String SE_KINGHARVEST_INTENTUTILS_ARGUMENT_TYPE_ 	= "SE_KINGHARVEST_INTENTUTILS_ARGUMENT_TYPE_";
	private static final String SE_KINGHARVEST_INTENTUTILS_ARGUMENT_ 		= "SE_KINGHARVEST_INTENTUTILS_ARGUMENT_";

	public static <A1> void putArgumentInIntent(Intent intent, int i, A1 arg1)
	{
        String argTypeLabel	= SE_KINGHARVEST_INTENTUTILS_ARGUMENT_TYPE_ + i;
        String argLabel 	= SE_KINGHARVEST_INTENTUTILS_ARGUMENT_ + i;

        intent.putExtra(argTypeLabel, arg1.getClass().getSimpleName());
        
        Class<?> type = arg1.getClass();
        if(Reflect.isInteger(type))
        	intent.putExtra(argLabel, (Integer)arg1);
        else if(Reflect.isDouble(type))
            intent.putExtra(argLabel, (Double)arg1);
        else if(Reflect.isString(type))
            intent.putExtra(argLabel, (String)arg1);
        else if(Reflect.isBoolean(type))
            intent.putExtra(argLabel, (Boolean)arg1);
		
        else if(Reflect.isIntegerArray(type))
        	intent.putExtra(argLabel, (Integer[])arg1);
        else if(Reflect.isDoubleArray(type))
            intent.putExtra(argLabel, (Double[])arg1);
        else if(Reflect.isStringArray(type))
            intent.putExtra(argLabel, (String[])arg1);
        else if(Reflect.isBooleanArray(type))
            intent.putExtra(argLabel, (Boolean[])arg1);
        else
        	throw new IllegalArgumentException("Type " + type + " is not an allowed type.");
	}

	public static Object getArgumentFromIntent(Intent intent, int i) {
		String type = intent.getStringExtra(SE_KINGHARVEST_INTENTUTILS_ARGUMENT_TYPE_ + i);
		String argLabel = intent.getStringExtra(SE_KINGHARVEST_INTENTUTILS_ARGUMENT_ + i);
		
        if(Reflect.isInteger(type))
        	return intent.getIntExtra(argLabel, 0);
        else if(Reflect.isDouble(type))
        	return intent.getDoubleExtra(argLabel, 0);
        else if(Reflect.isString(type))
        	return intent.getStringExtra(argLabel);
        else if(Reflect.isBoolean(type))
        	return intent.getBooleanExtra(argLabel, false);
		
        else if(Reflect.isIntegerArray(type))
        	return intent.getIntArrayExtra(argLabel);
        else if(Reflect.isDoubleArray(type))
        	return intent.getDoubleArrayExtra(argLabel);
        else if(Reflect.isStringArray(type))
        	return intent.getStringArrayExtra(argLabel);
        else if(Reflect.isBooleanArray(type))
        	return intent.getBooleanArrayExtra(argLabel);
        
        throw new IllegalArgumentException("Type " + type + " is not an allowed type.");
	}
}
