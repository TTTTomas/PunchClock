package se.kingharvest.infrastructure.ui.util;

import se.kingharvest.infrastructure.system.Types;
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
        if(Types.isInteger(type))
        	intent.putExtra(argLabel, (Integer)arg1);
        else if(Types.isDouble(type))
            intent.putExtra(argLabel, (Double)arg1);
        else if(Types.isString(type))
            intent.putExtra(argLabel, (String)arg1);
        else if(Types.isBoolean(type))
            intent.putExtra(argLabel, (Boolean)arg1);
		
        else if(Types.isIntegerArray(type))
        	intent.putExtra(argLabel, (Integer[])arg1);
        else if(Types.isDoubleArray(type))
            intent.putExtra(argLabel, (Double[])arg1);
        else if(Types.isStringArray(type))
            intent.putExtra(argLabel, (String[])arg1);
        else if(Types.isBooleanArray(type))
            intent.putExtra(argLabel, (Boolean[])arg1);
        else
        	throw new IllegalArgumentException("Type " + type + " is not an allowed type.");
	}

	public static Object getArgumentFromIntent(Intent intent, int i) {
		String type = intent.getStringExtra(SE_KINGHARVEST_INTENTUTILS_ARGUMENT_TYPE_ + i);
		String argLabel = intent.getStringExtra(SE_KINGHARVEST_INTENTUTILS_ARGUMENT_ + i);
		
        if(Types.isInteger(type))
        	return intent.getIntExtra(argLabel, 0);
        else if(Types.isDouble(type))
        	return intent.getDoubleExtra(argLabel, 0);
        else if(Types.isString(type))
        	return intent.getStringExtra(argLabel);
        else if(Types.isBoolean(type))
        	return intent.getBooleanExtra(argLabel, false);
		
        else if(Types.isIntegerArray(type))
        	return intent.getIntArrayExtra(argLabel);
        else if(Types.isDoubleArray(type))
        	return intent.getDoubleArrayExtra(argLabel);
        else if(Types.isStringArray(type))
        	return intent.getStringArrayExtra(argLabel);
        else if(Types.isBooleanArray(type))
        	return intent.getBooleanArrayExtra(argLabel);
        
        throw new IllegalArgumentException("Type " + type + " is not an allowed type.");
	}
}
