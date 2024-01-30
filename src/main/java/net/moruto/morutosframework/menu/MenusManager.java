package net.moruto.morutosframework.menu;

import java.util.ArrayList;
import java.util.Iterator;
import net.moruto.morutosframework.plugin.MorutosPlugin;
import net.moruto.morutosframework.utils.ColorUtils;

public class MenusManager {
    private final ArrayList<Menu> menus = new ArrayList();

    public MenusManager() {
        if (this.menus.size() != 0) {
            MenuListener menuListener = new MenuListener();
            MorutosPlugin.getInstance().registerListener(menuListener);
        }

    }

    public void addMenu(Menu menu) {
        this.menus.add(menu);
    }

    public Menu getMenu(String name) {
        Iterator var2 = this.menus.iterator();

        Menu menu;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            menu = (Menu)var2.next();
        } while(!ColorUtils.trans(menu.getName()).equalsIgnoreCase(ColorUtils.trans(name)));

        return menu;
    }

    public void removeMenu(Menu menu) {
        this.menus.remove(menu);
    }
}