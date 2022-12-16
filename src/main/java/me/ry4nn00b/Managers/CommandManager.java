package me.ry4nn00b.Managers;

import me.ry4nn00b.Commands.DelRank;
import me.ry4nn00b.Commands.Ranks;
import me.ry4nn00b.Commands.SetRank;
import org.bukkit.Bukkit;

public class CommandManager{

    public static void commandManager(){

        Bukkit.getPluginCommand("ranks").setExecutor(new Ranks());
        Bukkit.getPluginCommand("setrank").setExecutor(new SetRank());
        Bukkit.getPluginCommand("remrank").setExecutor(new DelRank());

    }

}
