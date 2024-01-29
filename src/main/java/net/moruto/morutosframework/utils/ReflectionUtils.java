package net.moruto.morutosframework.utils;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public static Method getPrivateMethod(Class<?> clazz, String methodName, Class<?>... parameters) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, parameters);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Field getPrivateField(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }
}
