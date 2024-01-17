package net.moruto.morutosframework.plugin;

import net.moruto.morutosframework.listener.ListenersManager;
import net.moruto.morutosframework.menu.MenusManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MorutosPlugin extends JavaPlugin {
    private ListenersManager listenersManager;
    private MenusManager menusManager;

    public static MorutosPlugin getInstance() {
        return JavaPlugin.getPlugin(MorutosPlugin.class);
    }

    @Override
    public void onEnable() {
        enable();
        listenersManager = new ListenersManager();
        menusManager = new MenusManager();
    }

    @Override
    public void onDisable() {
        disable();
    }

    public abstract void enable();
    public abstract void disable();

    public ListenersManager getListenersManager() {
        return listenersManager;
    }

    public MenusManager getMenusManager() {
        return menusManager;
    }
}
