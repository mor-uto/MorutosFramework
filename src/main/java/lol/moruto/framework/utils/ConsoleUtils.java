package lol.moruto.framework.utils;

import lol.moruto.framework.MorutosPlugin;
import org.bukkit.Bukkit;

public class ConsoleUtils {
    private ConsoleUtils() {}

    public static void sendMessage(String str, boolean useColor) {
        Bukkit.getConsoleSender().sendMessage(useColor ? ColorUtils.trans(str) : str);
    }

    public static void runCommand(String command) {
        Bukkit.getScheduler().runTask(MorutosPlugin.getInstance(), () -> {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("/", ""));
        });
    }
}