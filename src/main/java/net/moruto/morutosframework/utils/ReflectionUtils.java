package net.moruto.morutosframework.utils;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    private ReflectionUtils() {}

    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... parameters) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, parameters);
            if (!method.isAccessible()) method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Constructor<?> getConstructor(Class<?> clazz, Class<?>... parameters) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(parameters);
            if (!constructor.isAccessible()) constructor.setAccessible(true);
            return constructor;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Field getField(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if (!field.isAccessible()) field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Annotation getAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return clazz.getDeclaredAnnotation(annotationClass);
    }
}
