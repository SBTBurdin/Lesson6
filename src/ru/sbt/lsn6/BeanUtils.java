package ru.sbt.lsn6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        if (to == null || from == null) return;
        Method[] toMethods = to.getClass().getDeclaredMethods();
        Method[] fromMethods = from.getClass().getDeclaredMethods();

        Map<String, Method> setMethods = new HashMap<>();
        for (Method m : toMethods) {
            if (m.getParameterTypes().length == 1 && m.getName().startsWith("set")) {
                setMethods.put(m.getName().substring(3), m);
            }
        }

        Map<String, Method> getMethods = new HashMap<>();
        for (Method m : fromMethods) {
            if (m.getParameterTypes().length == 0 && m.getName().startsWith("get")) {
                getMethods.put(m.getName().substring(3), m);
            }
        }

        for (Map.Entry<String, Method> setM : setMethods.entrySet()) {
            if (getMethods.containsKey(setM.getKey())) {
                Method get = getMethods.get(setM.getKey());
                Method set = setM.getValue();
                if (get.getReturnType().equals(set.getParameterTypes()[0]) ||
                        get.getReturnType().getSuperclass().equals(set.getParameterTypes()[0])) {
                    try {
                        get.setAccessible(true);
                        set.setAccessible(true);
                        set.invoke(to, get.invoke(from));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
