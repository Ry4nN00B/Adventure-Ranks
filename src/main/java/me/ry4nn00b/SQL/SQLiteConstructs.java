package me.ry4nn00b.SQL;

import me.ry4nn00b.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteConstructs {

    public static Main plugin = Main.plugin;

    public static void CreateTable(){

        PreparedStatement stm;

        try{

            stm = SQLiteConnect.con.prepareStatement("create table if not exists `adventurerank`(Player_UUID varchar(36), Player_Name varchar(24), Ranks text);");
            stm.executeUpdate();
            Bukkit.getConsoleSender().sendMessage("[Adventure-Ranks] A tabela no SQLite foi criada com sucesso!");

            stm.close();

        }catch (SQLException e){

            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("[Adventure-Ranks] Não foi possível criar a tabela no SQLite.");
            plugin.getPluginLoader().disablePlugin(plugin);

        }


    }

    public static void addPlayer(Player player){

        try {

            String setPlayer = "insert into `adventurerank`(`Player_UUID`, `Player_Name`, `Ranks`) values ('" + player.getUniqueId().toString() + "', '" + player.getName() + "', 'Nenhum');";

            Connection con = SQLiteConnect.con;

            PreparedStatement statement = con.prepareStatement(setPlayer);
            statement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static boolean containPlayer(Player player){

        try {

            String hasPlayer = "SELECT `Player_UUID` FROM `adventurerank` WHERE `Player_UUID` = '" + player.getUniqueId().toString() + "';";

            Connection con = SQLiteConnect.con;
            PreparedStatement statement = con.prepareStatement(hasPlayer);
            ResultSet resultSet = statement.executeQuery();

            boolean result = resultSet.next();

            return  result;

        }catch (Exception e){

            e.printStackTrace();

        }

        return  false;
    }

    //Player_RANK------------------------------------------------------------------------------------------------------------------

    public static void setPlayer_Rank(Player p, String rank){

        try {

            String setPlayer_Vila = "update `adventurerank` set `Ranks` = '" + rank + "' where `Player_UUID` = '" + p.getUniqueId().toString() + "';";

            Connection con = SQLiteConnect.con;

            PreparedStatement statement = con.prepareStatement(setPlayer_Vila);
            statement.executeUpdate();


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static String getPlayer_Rank(Player p){

        try {

            String getPlayer_Rank = "select `Ranks` from `adventurerank` where `Player_UUID` = '" + p.getUniqueId().toString() + "';";

            Connection con = SQLiteConnect.con;
            PreparedStatement statement = con.prepareStatement(getPlayer_Rank);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.getString("Ranks");

        }catch (Exception e){

            e.printStackTrace();

        }

        return null;
    }

}
