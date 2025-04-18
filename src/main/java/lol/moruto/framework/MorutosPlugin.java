package lol.moruto.framework;

import lol.moruto.framework.command.CommandsManager;
import lol.moruto.framework.command.MCommand;
import lol.moruto.framework.config.ConfigManager;
import lol.moruto.framework.listener.ListenersManager;
import lol.moruto.framework.menu.MenusManager;
import org.bukkit.event.Listener;
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
    public final void onEnable() {
        configManager = new ConfigManager(getConfig());
        listenersManager = new ListenersManager();
        menusManager = new MenusManager();
        commandsManager = new CommandsManager();

        onPluginEnable();

        listenersManager.getListeners().forEach(configManager::loadConfig);
        commandsManager.getCommands().forEach(configManager::loadConfig);
    }

    @Override
    public final void onDisable() {
        commandsManager.unregisterAll();
        listenersManager.unregisterAll();
        onPluginDisable();
    }

    protected void onPluginEnable() {}
    protected void onPluginDisable() {}

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

    public ConfigManager getConfigManager() {
        return configManager;
    }

}
