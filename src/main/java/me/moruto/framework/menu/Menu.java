package me.moruto.framework.menu;

import me.moruto.framework.MorutosPlugin;
import me.moruto.framework.utils.ColorUtils;
import me.moruto.framework.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class Menu implements InventoryHolder {
    private final MenuHolder menuHolder;
    private final Inventory inventory;
    private final ItemStack FILLER_GLASS = ItemBuilder.createBasic(Material.GRAY_STAINED_GLASS_PANE, "");
    private final String name;
    private final int size;

    public Menu(MenuHolder menuHolder, String name, int size) {
        this.menuHolder = menuHolder;
        this.name = name;
        this.size = size;
        this.inventory = Bukkit.createInventory(null, size, ColorUtils.trans(name));

        MorutosPlugin.getInstance().getMenusManager().addMenu(this);
    }

    public abstract void handleMenu(InventoryClickEvent var1);

    public abstract void setMenuItems();

    public void open() {
        setMenuItems();
        menuHolder.getOwner().openInventory(this.inventory);
    }

    public int getSize() {
        return this.size;
    }

    public void close() {
        this.menuHolder.getOwner().closeInventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setFillerGlass() {
        for (int i = 0; i < this.size; ++i) {
            if (this.inventory.getItem(i) == null) {
                this.inventory.setItem(i, this.FILLER_GLASS);
            }
        }
    }

    public String getName() {
        return ColorUtils.trans(this.name);
    }
}