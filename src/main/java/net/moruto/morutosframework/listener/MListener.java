package net.moruto.morutosframework.listener;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public interface MListener extends Listener {
    default void unregister() {
        HandlerList.unregisterAll(this);
    }
}
