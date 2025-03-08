package me.moruto.framework.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {
    private ItemBuilder() {}

    public static ItemStack createBasic(Material mat, String name) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtils.trans(name));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createBasicWithLore(Material mat, String name, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtils.trans(name));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}