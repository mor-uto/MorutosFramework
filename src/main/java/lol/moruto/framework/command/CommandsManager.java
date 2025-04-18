package lol.moruto.framework.command;

import lol.moruto.framework.MorutosPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;
import java.util.*;

public class CommandsManager {
    private final CommandMap commandMap;
    private final Map<String, MCommand> registeredCommands = new HashMap<>();

    public CommandsManager() {
        this.commandMap = getCommandMap();
    }

    private CommandMap getCommandMap() {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            return (CommandMap) commandMapField.get(Bukkit.getServer());
        } catch (Exception e) {
            MorutosPlugin.getInstance().getLogger().severe("Failed to access CommandMap: " + e.getMessage());
            return null;
        }
    }

    public void register(MCommand command) {
        if (commandMap == null) {
            MorutosPlugin.getInstance().getLogger().severe("CommandMap is null! Cannot register commands.");
            return;
        }

        String commandName = command.getName();
        unregister(commandName);

        commandMap.register(MorutosPlugin.getInstance().getName().toLowerCase(), command);
        registeredCommands.put(commandName, command);
        MorutosPlugin.getInstance().getLogger().info("Registered command: " + commandName);
    }

    public void unregister(String commandName) {
        if (commandMap == null) return;

        try {
            Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
            knownCommandsField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Map<String, Command> knownCommands = (Map<String, Command>) knownCommandsField.get(commandMap);

            if (knownCommands.containsKey(commandName)) {
                knownCommands.remove(commandName);
                registeredCommands.remove(commandName);
                MorutosPlugin.getInstance().getLogger().info("Unregistered command: " + commandName);
            }
        } catch (Exception e) {
            MorutosPlugin.getInstance().getLogger().severe("Failed to unregister command " + commandName + ": " + e.getMessage());
        }
    }

    public void registerAll(Set<MCommand> commands) {
        for (MCommand command : commands) {
            register(command);
        }
    }

    public List<MCommand> getCommands() {
        return new ArrayList<>(registeredCommands.values());
    }

    public void unregisterAll() {
        new ArrayList<>(registeredCommands.keySet()).forEach(this::unregister);
        registeredCommands.clear();
    }

}
