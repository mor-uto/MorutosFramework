package net.moruto.morutosframework.listener;

import net.moruto.morutosframework.plugin.MorutosPlugin;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class ListenersManager {
    private final List<MListener> listeners = new ArrayList<>();
    public void addListener(MListener listener) {
        listeners.add(listener);
    }
    public void removeListener(MListener listener) {
        listeners.remove(listener);
    }

    public List<MListener> getListeners() {
        return listeners;
    }

    public void register(MListener listener) {
        listeners.add(listener);
        Bukkit.getPluginManager().registerEvents(listener, MorutosPlugin.getInstance());
    }
}
