package me.ry4nn00b.Managers;

import me.ry4nn00b.Events.AdventureRanks;
import me.ry4nn00b.Events.OnJoin;
import me.ry4nn00b.Main;
import org.bukkit.Bukkit;

public class EventsManager {

    public static void Events(){

        Bukkit.getPluginManager().registerEvents(new AdventureRanks(), Main.plugin);
        Bukkit.getPluginManager().registerEvents(new OnJoin(), Main.plugin);

    }

}
