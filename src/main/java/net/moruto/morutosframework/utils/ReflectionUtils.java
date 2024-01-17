package net.moruto.morutosframework.utils;

import net.moruto.morutosframework.listener.MListener;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

public class ReflectionUtils {
    public static Method getPrivateMethod(Class<?> clazz, String methodName, Class<?>... paramters) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, paramters);
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

    public static ArrayList<Class<?>> getClassesOfType(Class<?> targetClass) {
        ArrayList<Class<?>> list = new ArrayList<>();
        try {
            Reflections reflection = new Reflections();
            Set<Class<?>> classes = reflection.getSubTypesOf(targetClass.getClass().newInstance());
            list.addAll(classes);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return list;
    }
}
