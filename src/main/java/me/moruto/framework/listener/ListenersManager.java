package me.moruto.framework.listener;

import me.moruto.framework.plugin.MorutosPlugin;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class ListenersManager {
    private final List<MListener> listeners = new ArrayList<>();

    public void register(MListener listener) {
        this.listeners.add(listener);
        Bukkit.getPluginManager().registerEvents(listener, MorutosPlugin.getInstance());
    }

    public void unregister(MListener listener) {
        this.listeners.remove(listener);
    }

    public List<MListener> getListeners() {
        return this.listeners;
    }
}