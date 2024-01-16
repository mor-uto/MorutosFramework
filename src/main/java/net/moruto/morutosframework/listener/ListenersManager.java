package net.moruto.morutosframework.listener;

import net.moruto.morutosframework.plugin.MorutosPlugin;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class ListenersManager {
    private final List<MListener> listeners = new ArrayList<>();

    public ListenersManager() {
        for (MListener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, MorutosPlugin.getInstance());
        }
    }

    public void addListener(MListener listener) {
        listeners.add(listener);
    }
    public void removeListener(MListener listener) {
        listeners.remove(listener);
    }
}
