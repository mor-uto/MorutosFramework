package net.moruto.morutosframework.listener;

import net.moruto.morutosframework.plugin.MorutosPlugin;
import net.moruto.morutosframework.utils.ReflectionUtils;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class ListenersManager {
    private final List<MListener> listeners = new ArrayList<>();

    public ListenersManager() {
        getAndRegisterClasses();
    }

    public void addListener(MListener listener) {
        listeners.add(listener);
    }
    public void removeListener(MListener listener) {
        listeners.remove(listener);
    }

    private void getAndRegisterClasses() {
        try {
            ArrayList<Class<?>> classes = ReflectionUtils.getClassesOfType(MListener.class);
            for (Class<?> clazz : classes) {
                listeners.add((MListener) clazz.newInstance());
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        for (MListener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, MorutosPlugin.getInstance());
        }
    }
}
