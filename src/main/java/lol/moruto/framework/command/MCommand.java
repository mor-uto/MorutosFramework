package lol.moruto.framework.command;

import lol.moruto.framework.MorutosPlugin;
import lol.moruto.framework.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public abstract class MCommand extends BukkitCommand implements TabCompleter {
    private final boolean requirePlayer;
    private final String permission;

    public MCommand(String name, String permission, boolean requirePlayer) {
        super(name);
        this.permission = permission;
        this.requirePlayer = requirePlayer;
    }

    public abstract void execute(CommandSender sender, String[] args);

    public List<String> tabSuggestions(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    public final void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ColorUtils.trans(MorutosPlugin.getInstance().getPrefix() + " " + message));
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (requirePlayer && !(sender instanceof Player)) {
            sendMessage(sender, "&cA player is required to use this command!");
            return true;
        }

        if (permission != null && !sender.hasPermission(permission)) {
            sendMessage(sender, "&cYou don't have permission for this command!");
            return true;
        }

        execute(sender, args);
        return true;
    }

    @Override
    public final List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return tabSuggestions(sender, args);
    }
}
