package me.moruto.framework.listener;

import me.moruto.framework.MorutosPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class ListenersManager {
    private final List<Listener> listeners = new ArrayList<>();

    public void register(Listener listener) {
        listeners.add(listener);
        Bukkit.getPluginManager().registerEvents(listener, MorutosPlugin.getInstance());
    }

    public void unregister(Listener listener) {
        if (listeners.remove(listener)) {
            HandlerList.unregisterAll(listener);
        }
    }

    public void unregisterAll() {
        listeners.forEach(HandlerList::unregisterAll);
        listeners.clear();
    }

    public List<Listener> getListeners() {
        return new ArrayList<>(listeners);
    }
}
