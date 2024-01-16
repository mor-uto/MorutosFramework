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
    private final ItemStack FILLER_GLASS = ItemBuilder.createBasic(Material.GRAY_STAINED_GLASS_PANE, "");
    private final String name;
    private final int size;

    public Menu(MenuHolder menuHolder, String name, int size) {
        this.menuHolder = menuHolder;
        this.name = name;
        this.size = size;
        MorutosPlugin.getInstance().getMenusManager().addMenu(this);
    }

    public abstract void handleMenu(InventoryClickEvent event);

    public abstract void setMenuItems();

    public void open() {
        inventory = Bukkit.createInventory(this, size, ColorUtils.trans(name));
        this.setMenuItems();
        menuHolder.getOwner().openInventory(inventory);
    }

    public void close() {
        menuHolder.getOwner().closeInventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setFillerGlass() {
        for (int i = 0; i < size; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, FILLER_GLASS);
            }
        }
    }

    public String getName() {
        return ColorUtils.trans(name);
    }
}
