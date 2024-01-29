package net.moruto.morutosframework.listener;

import net.moruto.morutosframework.plugin.MorutosPlugin;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public interface MListener extends Listener {
    private void register() {
        MorutosPlugin.getInstance().registerListener(this);
    }

    default void unregister() {
        HandlerList.unregisterAll(this);
    }
}
