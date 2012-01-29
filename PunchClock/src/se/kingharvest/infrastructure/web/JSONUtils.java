//package se.kingharvest.infrastructure.web;
//
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.JSONString;
//
//import se.kingharvest.infrastructure.entity.IEntity;
//
//public class JSONUtils {
//
//    /**
//     * JSONObject.NULL is equivalent to the value that JavaScript calls null,
//     * whilst Java's null is equivalent to the value that JavaScript calls
//     * undefined.
//     */
//     private static final class Null {
//
//        /**
//         * There is only intended to be a single instance of the NULL object,
//         * so the clone method returns itself.
//         * @return     NULL.
//         */
//        protected final Object clone() {
//            return this;
//        }
//
//        /**
//         * A Null object is equal to the null value and to itself.
//         * @param object    An object to test for nullness.
//         * @return true if the object parameter is the JSONObject.NULL object
//         *  or null.
//         */
//        public boolean equals(Object object) {
//            return object == null || object == this;
//        }
//
//        /**
//         * Get the "null" string value.
//         * @return The string "null".
//         */
//        public String toString() {
//            return "null";
//        }
//    }
//
//     /**
//      * It is sometimes more convenient and less ambiguous to have a
//      * <code>NULL</code> object than to use Java's <code>null</code> value.
//      * <code>JSONObject.NULL.equals(null)</code> returns <code>true</code>.
//      * <code>JSONObject.NULL.toString()</code> returns <code>"null"</code>.
//      */
//     public static final Object NULL = new Null();
//
//    /**
//	 * 
//	 * @param entity
//	 * @return
//	 */
//    private JSONObject toJsonObject(IEntity entity) {
//        Class<?> klass = entity.getClass();
//
//// If klass is a System class then set includeSuperClass to false.
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        boolean includeSuperClass = klass.getClassLoader() != null;
//
//        Method[] methods = includeSuperClass
//                ? klass.getMethods()
//                : klass.getDeclaredMethods();
//        for (int i = 0; i < methods.length; i += 1) {
//            try {
//                Method method = methods[i];
//                if (Modifier.isPublic(method.getModifiers())) {
//                    String name = method.getName();
//                    String key = "";
//                    if (name.startsWith("get")) {
//                        if ("getClass".equals(name) ||
//                                "getDeclaringClass".equals(name)) {
//                            key = "";
//                        } else {
//                            key = name.substring(3);
//                        }
//                    } else if (name.startsWith("is")) {
//                        key = name.substring(2);
//                    }
//                    if (key.length() > 0 &&
//                            Character.isUpperCase(key.charAt(0)) &&
//                            method.getParameterTypes().length == 0) {
//                        if (key.length() == 1) {
//                            key = key.toLowerCase();
//                        } else if (!Character.isUpperCase(key.charAt(1))) {
//                            key = key.substring(0, 1).toLowerCase() +
//                                key.substring(1);
//                        }
//
//                        Object result = method.invoke(entity, (Object[])null);
//                        if (result != null) {
//                            map.put(key, wrap(result));
//                        }
//                    }
//                }
//            } catch (Exception ignore) {
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Wrap an object, if necessary. If the object is null, return the NULL
//     * object. If it is an array or collection, wrap it in a JSONArray. If
//     * it is a map, wrap it in a JSONObject. If it is a standard property
//     * (Double, String, et al) then it is already wrapped. Otherwise, if it
//     * comes from one of the java packages, turn it into a string. And if
//     * it doesn't, try to wrap it in a JSONObject. If the wrapping fails,
//     * then null is returned.
//     *
//     * @param object The object to wrap
//     * @return The wrapped value
//     */
//    @SuppressWarnings("unchecked")
//	public static Object wrap(Object object) {
//        try {
//            if (object == null) {
//                return NULL;
//            }
//            if (object instanceof JSONObject || object instanceof JSONArray  ||
//                    NULL.equals(object)      || object instanceof JSONString ||
//                    object instanceof Byte   || object instanceof Character  ||
//                    object instanceof Short  || object instanceof Integer    ||
//                    object instanceof Long   || object instanceof Boolean    ||
//                    object instanceof Float  || object instanceof Double     ||
//                    object instanceof String) {
//                return object;
//            }
//
//            if (object instanceof Collection) {
//                return new JSONArray((Collection<Object>)object);
//            }
//            if (object.getClass().isArray()) {
//                return new JSONArray(object);
//            }
//            if (object instanceof Map) {
//                return new JSONObject((Map<String, Object>)object);
//            }
//            Package objectPackage = object.getClass().getPackage();
//            String objectPackageName = objectPackage != null
//                ? objectPackage.getName()
//                : "";
//            if (
//                objectPackageName.startsWith("java.") ||
//                objectPackageName.startsWith("javax.") ||
//                object.getClass().getClassLoader() == null
//            ) {
//                return object.toString();
//            }
//            return new JSONObject(object);
//        } catch(Exception exception) {
//            return null;
//        }
//    }
//}
