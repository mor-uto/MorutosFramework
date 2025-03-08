package me.moruto.framework.command;

import me.moruto.framework.plugin.MorutosPlugin;
import me.moruto.framework.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class MCommand extends BukkitCommand implements TabCompleter {
    private final boolean requirePlayer;
    public MCommand(String name, boolean requirePlayer) {
        super(name);
        this.requirePlayer = requirePlayer;
    }

    public abstract void execute(CommandSender sender, String[] args);
    public abstract List<String> onTabComplete(CommandSender sender, String[] args);

    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!requirePlayer) {
            this.execute(sender, args);
            return true;
        }

        if (!(sender instanceof Player)) sender.sendMessage(ColorUtils.trans(MorutosPlugin.getInstance().getPrefix() + " &ca player is required to use this command!"));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        return new ArrayList<>(onTabComplete(commandSender, args));
    }
}