package net.moruto.morutosframework.listener;

import net.moruto.morutosframework.plugin.MorutosPlugin;
import org.bukkit.event.Listener;

public interface MListener extends Listener {
    default void register() {
        MorutosPlugin.getInstance().getListenersManager().addListener(this);
    }
}
