package net.moruto.morutosframework.menu;

import org.bukkit.entity.Player;

public class MenuHolder {
    private final Player player;

    public MenuHolder(Player player) {
        this.player = player;
    }

    public Player getOwner() {
        return player;
    }
}
