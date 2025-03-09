package me.moruto.framework.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.Field;

public class ConfigManager {
    private final FileConfiguration config;

    public ConfigManager(FileConfiguration config) {
        this.config = config;
    }

    public void loadConfig(Object instance) {
        Class<?> clazz = instance.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigEntry.class)) {
                ConfigEntry entry = field.getAnnotation(ConfigEntry.class);
                String path = entry.path();

                try {
                    field.setAccessible(true);
                    Object defaultValue = field.get(instance);

                    if (!config.contains(path)) config.set(path, defaultValue);
                    Object value = config.get(path);
                    field.set(instance, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
