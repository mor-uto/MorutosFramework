package me.moruto.framework.menu;

import me.moruto.framework.MorutosPlugin;
import me.moruto.framework.utils.ColorUtils;

import java.util.ArrayList;

public class MenusManager {
    private final ArrayList<Menu> menus = new ArrayList<>();

    public MenusManager() {
        MorutosPlugin.getInstance().registerListener(new MenuListener());
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    public Menu getMenu(String name) {
        for (Menu menu : menus) {
            if (ColorUtils.strip(menu.getName()).equalsIgnoreCase(name)) {
                return menu;
            }
        }

        return null;
    }

    public void removeMenu(Menu menu) {
        menus.remove(menu);
    }
}