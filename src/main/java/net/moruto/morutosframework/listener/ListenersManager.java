package net.moruto.morutosframework.listener;

import java.util.ArrayList;
import java.util.List;
import net.moruto.morutosframework.plugin.MorutosPlugin;
import org.bukkit.Bukkit;

public class ListenersManager {
    private final List<MListener> listeners = new ArrayList<>();

    public void register(MListener listener) {
        this.listeners.add(listener);
        Bukkit.getPluginManager().registerEvents(listener, MorutosPlugin.getInstance());
    }

    public void unregister(MListener listener) {
        this.listeners.remove(listener);
        listener.unregister();
    }

    public List<MListener> getListeners() {
        return this.listeners;
    }
}