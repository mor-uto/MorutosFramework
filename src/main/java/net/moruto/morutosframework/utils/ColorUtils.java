package net.moruto.morutosframework.utils;

import net.md_5.bungee.api.ChatColor;

public class ColorUtils {
    public static String trans(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
