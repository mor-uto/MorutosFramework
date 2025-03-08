package me.moruto.framework.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigFile {
    private final YamlConfiguration config;
    private final File file;

    public ConfigFile(File file, boolean createIfNotExist) {
        this.file = file;

        if (createIfNotExist) {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void moveTo(Path path) {
        try {
            Files.move(path, file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getConfig() {
        return config;
    }
}
