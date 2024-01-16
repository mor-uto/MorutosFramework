package net.moruto.morutosframework.menu;

import net.moruto.morutosframework.utils.ColorUtils;

import java.util.ArrayList;

public class MenusManager {
    private final ArrayList<Menu> menus = new ArrayList<>();

    public MenusManager() {
        if (!menus.isEmpty()) {
            MenuListener menuListener = new MenuListener();
            menuListener.register();
        }
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    public Menu getMenu(String name) {
        for (Menu menu : menus) {
            if (ColorUtils.trans(menu.getName()).equalsIgnoreCase(ColorUtils.trans(name))) {
                return menu;
            }
        }
        return null;
    }

    public void removeMenu(Menu menu) {
        menus.remove(menu);
    }
}
