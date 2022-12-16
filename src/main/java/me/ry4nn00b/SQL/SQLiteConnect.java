package me.ry4nn00b.SQL;

import me.ry4nn00b.Main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnect {

    public static Connection con = null;

    public static void open() {
        if(!Main.plugin.getDataFolder().exists()) {
            Main.plugin.getDataFolder().mkdir();
        }
        File file = new File(Main.plugin.getDataFolder(), "adventureRanks.db");

        String url = "jdbc:sqlite:" + file;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);
            SQLiteConstructs.CreateTable();
            Bukkit.getConsoleSender().sendMessage("[Adventure-Ranks] Conexão com o SQLite foi um sucesso!");
        } catch (Exception e) {
            Main.plugin.getPluginLoader().disablePlugin(JavaPlugin.getPlugin(Main.class));
            Bukkit.getConsoleSender().sendMessage("[Adventure-Ranks] Conexão com SQLite falhou.");
        }
    }

    public static void close() {
        if(con != null) {
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage("[Adventure-Ranks] Conexão com SQLite fechada.");
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("[Adventure-Ranks] Nao foi possível fechar a conexão com o SQLite.");
                e.printStackTrace();
            }
        }
    }

}
