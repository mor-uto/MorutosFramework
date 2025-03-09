package me.moruto.framework.plugin;

import me.moruto.framework.command.CommandsManager;
import me.moruto.framework.command.MCommand;
import me.moruto.framework.config.ConfigManager;
import me.moruto.framework.exceptions.CommandRegistrationException;
import me.moruto.framework.listener.ListenersManager;
import me.moruto.framework.listener.MListener;
import me.moruto.framework.menu.MenusManager;
import org.bukkit.plugin.java.JavaPlugin;


public abstract class MorutosPlugin extends JavaPlugin {
    private ListenersManager listenersManager;
    private MenusManager menusManager;
    private CommandsManager commandsManager;
    private ConfigManager configManager;

    public static MorutosPlugin getInstance() {
        return JavaPlugin.getPlugin(MorutosPlugin.class);
    }

    @Override
    public void onEnable() {
        configManager = new ConfigManager(getConfig());
        this.listenersManager = new ListenersManager();
        this.menusManager = new MenusManager();
        this.commandsManager = new CommandsManager();
        this.enable();
        listenersManager.getListeners().forEach(listener -> configManager.loadConfig(listener));
        commandsManager.getCommands().forEach(command -> configManager.loadConfig(command));
    }

    @Override
    public void onDisable() {
        this.disable();
    }

    public abstract void enable();

    public abstract void disable();

    public abstract String getPrefix();

    public void registerCommand(MCommand command) {
        try {
            this.commandsManager.register(command);
        } catch (CommandRegistrationException e) {
            e.printStackTrace();
        }
    }

    public void registerListener(MListener listener) {
        this.listenersManager.register(listener);
    }

    public ListenersManager getListenersManager() {
        return this.listenersManager;
    }

    public MenusManager getMenusManager() {
        return this.menusManager;
    }

    public CommandsManager getCommandsManager() {
        return this.commandsManager;
    }
}