package net.moruto.morutosframework.jda;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.moruto.morutosframework.exceptions.CommandRegistrationException;
import net.moruto.morutosframework.jda.command.BotCommand;
import net.moruto.morutosframework.jda.command.CommandsManager;

public class DiscordBot {
    private JDA jda;
    private final JDABuilder builder;
    private final CommandsManager commandsManager;

    public DiscordBot(String token) {
        this.commandsManager = new CommandsManager();

        builder = JDABuilder.create(token, GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));
        builder.addEventListeners(commandsManager);
    }

    public void registerCommand(BotCommand command) {
        if (jda == null) {
            try {
                throw new CommandRegistrationException("Bot must be built before registering commands");
            } catch (CommandRegistrationException e) {
                e.printStackTrace();
            }
        }

        commandsManager.addCommand(command);
        jda.upsertCommand(command.getName(), command.getDescription()).queue();
    }

    public void build() {
        jda = builder.build();
    }

    public JDA getBot() {
        return jda;
    }
}
