package net.moruto.morutosframework.menu;

import net.moruto.morutosframework.plugin.MorutosPlugin;
import net.moruto.morutosframework.utils.ColorUtils;
import net.moruto.morutosframework.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class Menu implements InventoryHolder {
    private final MenuHolder menuHolder;
    private Inventory inventory;
    private final ItemStack FILLER_GLASS;
    private final String name;
    private final int size;

    public Menu(MenuHolder menuHolder, String name, int size) {
        this.FILLER_GLASS = ItemBuilder.createBasic(Material.GRAY_STAINED_GLASS_PANE, "");
        this.menuHolder = menuHolder;
        this.name = name;
        this.size = size;
        MorutosPlugin.getInstance().getMenusManager().addMenu(this);
    }

    public abstract void handleMenu(InventoryClickEvent var1);

    public abstract void setMenuItems();

    public void open() {
        this.inventory = Bukkit.createInventory(this, this.size, ColorUtils.trans(this.name));
        this.setMenuItems();
        this.menuHolder.getOwner().openInventory(this.inventory);
    }

    public int getSize() {
        return this.size;
    }

    public void close() {
        this.menuHolder.getOwner().closeInventory();
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setFillerGlass() {
        for(int i = 0; i < this.size; ++i) {
            if (this.inventory.getItem(i) == null) {
                this.inventory.setItem(i, this.FILLER_GLASS);
            }
        }

    }

    public String getName() {
        return ColorUtils.trans(this.name);
    }
}