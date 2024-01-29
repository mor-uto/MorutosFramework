package net.moruto.morutosframework;

import net.moruto.morutosframework.menu.Menu;
import net.moruto.morutosframework.menu.MenuHolder;
import org.bukkit.event.inventory.InventoryClickEvent;

public class menuTest extends Menu {
    MenuHolder holder = new MenuHolder(somePlayerHere);

    public menuTest() {
        super(holder, "Name here", 27);
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

    }

    @Override
    public void setMenuItems() {

    }
}
