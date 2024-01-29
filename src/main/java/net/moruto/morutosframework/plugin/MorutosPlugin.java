package net.moruto.morutosframework.plugin;

import net.moruto.morutosframework.command.CommandsManager;
import net.moruto.morutosframework.listener.ListenersManager;
import net.moruto.morutosframework.listener.MListener;
import net.moruto.morutosframework.menu.MenusManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MorutosPlugin extends JavaPlugin {
    private ListenersManager listenersManager;
    private MenusManager menusManager;
    private CommandsManager commandsManager;

    public static MorutosPlugin getInstance() {
        return JavaPlugin.getPlugin(MorutosPlugin.class);
    }

    @Override
    public void onEnable() {
        listenersManager = new ListenersManager();
        menusManager = new MenusManager();
        commandsManager = new CommandsManager();
        enable();
    }

    @Override
    public void onDisable() {
        disable();
    }

    public abstract void enable();
    public abstract void disable();
    public abstract String getPrefix();

    public void registerListener(MListener listener) {
        listenersManager.register(listener);
    }

    public ListenersManager getListenersManager() {
        return listenersManager;
    }

    public MenusManager getMenusManager() {
        return menusManager;
    }

    public CommandsManager getCommandsManager() {
        return commandsManager;
    }
}
