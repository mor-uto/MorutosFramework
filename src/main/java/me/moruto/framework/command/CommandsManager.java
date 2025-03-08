package me.moruto.framework.command;

import me.moruto.framework.exceptions.CommandRegistrationException;
import me.moruto.framework.plugin.MorutosPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CommandsManager {
    private final List<MCommand> commands = new ArrayList<>();
    CommandMap commandMap;

    public CommandsManager() {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
        } catch(NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void unregister(MCommand command) {
        commands.remove(command);
        command.unregister(commandMap);
    }

    public List<MCommand> getCommands() {
        return commands;
    }

    public void register(MCommand command) throws CommandRegistrationException {
        if (commands.contains(command) && commandMap.getCommand(command.getName().toLowerCase()) != null) throw new CommandRegistrationException("Command with this name is already registered");

        commands.add(command);
        commandMap.register(command.getName(), command);

        if (commandMap.getCommand(command.getName()) == null) throw new CommandRegistrationException("Command registration failed");

        MorutosPlugin.getInstance().getCommand(command.getName()).setTabCompleter(command);
    }
}
