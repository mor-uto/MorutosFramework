package net.moruto.morutosframework.plugin;

import net.moruto.morutosframework.listener.ListenersManager;
import net.moruto.morutosframework.menu.MenusManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MorutosPlugin extends JavaPlugin {
    private final ListenersManager listenersManager;
    private final MenusManager menusManager;

    public static MorutosPlugin getInstance() {
        return JavaPlugin.getPlugin(MorutosPlugin.class);
    }

    public MorutosPlugin() {
        listenersManager = new ListenersManager();
        menusManager = new MenusManager();
    }

    public ListenersManager getListenersManager() {
        return listenersManager;
    }

    public MenusManager getMenusManager() {
        return menusManager;
    }
}
