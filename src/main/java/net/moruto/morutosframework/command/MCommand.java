package net.moruto.morutosframework.command;

import net.moruto.morutosframework.plugin.MorutosPlugin;
import net.moruto.morutosframework.utils.ColorUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public abstract class MCommand extends BukkitCommand {
    private boolean requirePlayer;

    public MCommand(String name) {
        super(name);
    }

    public void enableRequiresPlayer() {
        requirePlayer = true;
    }

    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!requirePlayer) {
            execute(sender, args);
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtils.trans(MorutosPlugin.getInstance().getPrefix() + " " + "&ca player is required to use this command!"));
        }
        return true;
    }
}