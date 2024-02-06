package net.moruto.morutosframework.jda.command;

import net.dv8tion.jda.api.JDA;

public abstract class BotCommand {
    private final String name, description;

    public BotCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute(JDA jda);
}
