package org.envproperties;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * The wrapper over the System.getenv command
 * reads system variables into the passed class of type T.
 *
 * @version 0.1
 * @author arykin.valera@gmail.com
 */
public class EnvProperties {

    private static Map<String, String> envMap;

    /**
     * Returns an object of class T.
     * Its fields will be filled with the values ​​of the found system variables.
     *
     * @param clazz Сlass<T> whose object will be returned.
     * @param <T> Specified type of object.
     * @return Created object of specified type.
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static <T> T readEnv(Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        envMap = System.getenv();
        T props;
        props = clazz.getDeclaredConstructor().newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            String sysEnvValue = getSysEnvValue(f.getName());
            if (f.isAnnotationPresent(Env.class) && !sysEnvValue.isEmpty()) {
                f.setAccessible(true);
                f.set(props, sysEnvValue);
            }
        }
        return props;
    }

    /**
     * Finds the value of the system environment variable by name.
     *
     * @param envName String name of the environment variable
     * @return String value of the environment variable
     */
    private static String getSysEnvValue(String envName) {
        for (Map.Entry<String, String> entry : envMap.entrySet()) {
            if (envName.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "";
    }
}
