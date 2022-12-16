package me.ry4nn00b.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Config {

    private final String fileName;
    private final JavaPlugin plugin;
    public static File configFile;
    public static FileConfiguration fileConfiguration;

    @SuppressWarnings("deprecation")
    public Config(JavaPlugin plugin, String fileName) {
        if (plugin == null) {
            throw new IllegalArgumentException("plugin cannot be null");
        }
        if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("plugin must be enabled");
        }
        this.plugin = plugin;
        this.fileName = fileName;
        File dataFolder = plugin.getDataFolder();
        if (dataFolder == null) {
            throw new IllegalStateException();
        }
        configFile = new File(plugin.getDataFolder(), fileName);
    }

    public void saveDefaultConfig() {
        if (!configFile.exists())
            this.plugin.saveResource(this.fileName, false);
    }

    public void saveconfig() {
        try {
            fileConfiguration.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @SuppressWarnings("deprecation")
    public void reloadConfig() {
        fileConfiguration = (FileConfiguration) YamlConfiguration.loadConfiguration(configFile);

        InputStream defConfigStream = this.plugin.getResource(this.fileName);
        if (defConfigStream != null) {
        }
    }


    public FileConfiguration getConfig() {
        if (fileConfiguration == null) {
            reloadConfig();
        }
        return fileConfiguration;
    }

}
