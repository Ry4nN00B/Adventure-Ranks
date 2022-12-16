package me.ry4nn00b.Events;

import me.ry4nn00b.SQL.SQLiteConstructs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player player = e.getPlayer();

        if(!SQLiteConstructs.containPlayer(player)){

            SQLiteConstructs.addPlayer(player);

        }

    }

}
