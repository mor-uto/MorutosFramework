package net.moruto.morutosframework.listener;

import net.moruto.morutosframework.plugin.MorutosPlugin;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class ListenersManager {
    private final List<MListener> listeners = new ArrayList<>();

    public void register(MListener listener) {
        listeners.add(listener);
        Bukkit.getPluginManager().registerEvents(listener, MorutosPlugin.getInstance());
    }

    public void unregister(MListener listener) {
        listeners.remove(listener);
        listener.unregister();
    }

    public List<MListener> getListeners() {
        return listeners;
    }
}
