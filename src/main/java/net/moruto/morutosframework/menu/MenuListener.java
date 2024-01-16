package net.moruto.morutosframework.menu;

import net.moruto.morutosframework.listener.MListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements MListener {
    @EventHandler
    private void onMenuClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof Menu menu) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) {
                return;
            }
            menu.handleMenu(event);
        }
    }
}
