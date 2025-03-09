package me.moruto.framework;

import me.moruto.framework.command.CommandsManager;
import me.moruto.framework.command.MCommand;
import me.moruto.framework.config.ConfigManager;
import me.moruto.framework.listener.ListenersManager;
import me.moruto.framework.menu.MenusManager;
import org.bukkit.event.Listener;
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
        ConfigManager configManager = new ConfigManager(getConfig());
        listenersManager = new ListenersManager();
        menusManager = new MenusManager();
        commandsManager = new CommandsManager();

        onPluginEnable();

        listenersManager.getListeners().forEach(configManager::loadConfig);
        commandsManager.getCommands().forEach(configManager::loadConfig);
    }

    @Override
    public void onDisable() {
        commandsManager.unregisterAll();
        listenersManager.unregisterAll();
        onPluginDisable();
    }

    protected abstract void onPluginEnable();

    protected abstract void onPluginDisable();

    public abstract String getPrefix();

    public void registerCommand(MCommand command) {
        commandsManager.register(command);
    }

    public void registerListener(Listener listener) {
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
