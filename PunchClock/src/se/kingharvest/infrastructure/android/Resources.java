package se.kingharvest.infrastructure.android;

import java.lang.reflect.Field;

import se.kingharvest.infrastructure.collection.HashMapMap;
import se.kingharvest.infrastructure.reflection.FieldReflect;
import se.kingharvest.infrastructure.reflection.ReflectException;

public class Resources {
	
	private static HashMapMap<Class<?>, Integer, String> _idNameCache;
	
    /**
     * Determines the Name of a Resource,
     * by passing the <code>R.xyz.class</code> and
     * the <code>resourceID</code> of the class to it.
     * @param resourceClass : like <code>R.drawable.class</code>
     * @param resourceId : like <code>R.drawable.icon</code>
     * @throws IllegalArgumentException if field is not found.
     * @throws NullPointerException if <code>aClass</code>-Parameter is null.
     * <br><br>
     * <b>Example-Call:</b><br>
     * <code>String resName = getResourceNameFromClassByID(R.drawable.class, R.drawable.icon);</code><br>
     * Then <code>resName</code> would be '<b>icon</b>'.*/
    public static String getResourceNameById(Class<?> resourceClass, int resourceId)
    {
    	// Return cached value if possible.
    	if(_idNameCache == null)
    		_idNameCache = new HashMapMap<Class<?>, Integer, String>();
    	else
    		if(_idNameCache.containsKeys(resourceClass, resourceId))
    			return _idNameCache.get(resourceClass, resourceId);
    	
    	// Otherwise, look up field by value, and return name.
    	Field[] fields = FieldReflect.getFields(resourceClass);  
    	for(Field f : fields){
			/* All fields within the subclasses of R
			 * are Integers, so we need no type-check here. */
			/* Compare to the resourceId we are searching. */
			try {
				if (resourceId == f.getInt(null))
				{
					String name = f.getName();
					_idNameCache.put(resourceClass, resourceId, name);
					return f.getName();
				}
			} catch (Exception e) {
				throw new ReflectException("Failed to read int from field " + f + " in resource class " + resourceClass.getName(), e);
			}
    	}
    	throw new IllegalArgumentException("Could not find resource id " + Integer.toHexString(resourceId) + " in resource class " + resourceClass.getName());
    }
}
