package lol.moruto.framework.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {
    private ColorUtils() {}

    public static final String BLACK = "&0";
    public static final String DARK_BLUE = "&1";
    public static final String DARK_GREEN = "&2";
    public static final String DARK_AQUA = "&3";
    public static final String DARK_RED = "&4";
    public static final String DARK_PURPLE = "&5";
    public static final String GOLD = "&6";
    public static final String GRAY = "&7";
    public static final String DARK_GRAY = "&8";
    public static final String BLUE = "&9";
    public static final String GREEN = "&a";
    public static final String AQUA = "&b";
    public static final String RED = "&c";
    public static final String LIGHT_PURPLE = "&d";
    public static final String YELLOW = "&e";
    public static final String WHITE = "&f";

    public static final String OBFUSCATED = "&k";
    public static final String BOLD = "&l";
    public static final String STRIKETHROUGH = "&m";
    public static final String UNDERLINE = "&n";
    public static final String ITALIC = "&o";
    public static final String RESET = "&r";

    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");

    public static String trans(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
    public static String strip(String str) {
        return ChatColor.stripColor(str);
    }

    public static String transHex(String str) {
        Matcher matcher = HEX_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String hexColor = matcher.group(1);
            matcher.appendReplacement(sb, translateHexToLegacy(hexColor));
        }
        matcher.appendTail(sb);
        return ChatColor.translateAlternateColorCodes('&', sb.toString());
    }

    private static String translateHexToLegacy(String hex) {
        return ChatColor.RESET.toString();
    }
}