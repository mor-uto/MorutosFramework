package net.moruto.morutosframework.jda.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandsManager extends ListenerAdapter {
    private final List<BotCommand> commands = new ArrayList<>();

    public void addCommand(BotCommand command) {
        commands.add(command);
    }

    public List<BotCommand> getCommands() {
        return commands;
    }

    public BotCommand getCommand(String name) {
        for (BotCommand command : commands) {
            return command.getName().equalsIgnoreCase(name) ? command : null;
        }

        return null;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        for (BotCommand command : commands) {
            if (command.getName().equalsIgnoreCase(event.getName())) {
                BotCommand botCommand = getCommand(event.getName());
                botCommand.execute(event.getJDA());
            }
        }
    }
}
