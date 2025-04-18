package lol.moruto.framework.menu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {
    @EventHandler
    private void onMenuClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof Menu) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) {
                return;
            }

            ((Menu) holder).handleMenu(event);
        }
    }
}