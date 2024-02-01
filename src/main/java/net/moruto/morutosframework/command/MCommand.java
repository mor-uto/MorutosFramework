package net.moruto.morutosframework.command;

import net.moruto.morutosframework.plugin.MorutosPlugin;
import net.moruto.morutosframework.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class MCommand extends BukkitCommand implements TabCompleter {
    private boolean requirePlayer;

    public MCommand(String name) {
        super(name);
    }

    public void enableRequiresPlayer() {
        this.requirePlayer = true;
    }

    public abstract void execute(CommandSender sender, String[] args);
    public abstract List<String> onTabComplete(CommandSender sender, String[] args);

    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!this.requirePlayer) {
            this.execute(sender, args);
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ColorUtils.trans(MorutosPlugin.getInstance().getPrefix() + " &ca player is required to use this command!"));
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        return onTabComplete(commandSender, args);
    }
}