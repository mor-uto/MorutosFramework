package net.moruto.morutosframework.command;

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
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void addCommand(MCommand command) {
        commands.add(command);
    }
    public void removeCommand(MCommand command) {
        commands.remove(command);
    }

    public List<MCommand> getCommands() {
        return commands;
    }

    public void register(MCommand command) {
        commands.add(command);
        commandMap.register(command.getName(), command);
    }
}
