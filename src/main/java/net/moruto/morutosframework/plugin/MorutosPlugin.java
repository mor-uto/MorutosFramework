package net.moruto.morutosframework.plugin;

import net.moruto.morutosframework.command.CommandsManager;
import net.moruto.morutosframework.command.MCommand;
import net.moruto.morutosframework.listener.ListenersManager;
import net.moruto.morutosframework.listener.MListener;
import net.moruto.morutosframework.menu.MenusManager;
import net.moruto.morutosframework.utils.ReflectionUtils;
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

    public void registerCommand(MCommand command) {
        commandsManager.register(command);
    }
    public void registerListener(MListener listener) {
        listenersManager.register(listener);
    }

    public CommandsManager getCommandsManager() {
        return commandsManager;
    }

    public MenusManager getMenusManager() {
        return menusManager;
    }

    public ListenersManager getListenersManager() {
        return listenersManager;
    }
}
