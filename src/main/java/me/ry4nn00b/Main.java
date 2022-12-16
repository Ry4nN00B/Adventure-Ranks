package me.ry4nn00b;

import me.ry4nn00b.Managers.CommandManager;
import me.ry4nn00b.Managers.EventsManager;
import me.ry4nn00b.Managers.FileManager;
import me.ry4nn00b.Managers.PlaceHolders;
import me.ry4nn00b.SQL.SQLiteConnect;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main plugin;

    @Override
    public void onEnable() {

        plugin = this;

        Bukkit.getConsoleSender().sendMessage("§aAdventure-Ranks Iniciado.");

        SQLiteConnect.open();
        FileManager.createFiles();
        CommandManager.commandManager();
        EventsManager.Events();
        PlaceHolders.PlaceHolders_Variables();

    }

    @Override
    public void onDisable() {

        SQLiteConnect.close();
        Bukkit.getConsoleSender().sendMessage("§cAdventure-Ranks Desligado.");

    }

}
