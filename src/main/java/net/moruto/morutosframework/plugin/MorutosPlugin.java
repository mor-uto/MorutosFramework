package net.moruto.morutosframework.plugin;

import net.moruto.morutosframework.command.CommandsManager;
import net.moruto.morutosframework.command.MCommand;
import net.moruto.morutosframework.exceptions.CommandRegistrationException;
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
        this.listenersManager = new ListenersManager();
        this.menusManager = new MenusManager();
        this.commandsManager = new CommandsManager();
        this.enable();
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