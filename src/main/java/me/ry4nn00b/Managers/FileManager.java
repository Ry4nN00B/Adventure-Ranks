package me.ry4nn00b.Managers;

import me.ry4nn00b.Main;
import me.ry4nn00b.Utils.Config;

public class FileManager {

    public static Config ranks;

    public static void createFiles(){

        ranks = new Config(Main.plugin, "Ranks.yml");

        ranks.saveDefaultConfig();

    }

}
