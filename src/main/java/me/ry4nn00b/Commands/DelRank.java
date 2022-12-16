package me.ry4nn00b.Commands;

import me.ry4nn00b.Managers.FileManager;
import me.ry4nn00b.SQL.SQLiteConstructs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelRank implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(cmd.getName().equalsIgnoreCase("remrank")){

            for(String ranks : FileManager.ranks.getConfig().getConfigurationSection("Ranks").getKeys(false)) {

                if (sender.hasPermission("adventure.ranks")) {
                    if (args.length == 0) {

                        sender.sendMessage("§6§l• §c§l!§6§lゞ §aUtilize: §a/remrank <rank> <player>");
                        return true;

                    }

                    Player p = Bukkit.getPlayer(args[1]);

                    if (p == null) {

                        sender.sendMessage("§6§l• §c§l!§6§lゞ §aEscolha um jogador válido!");
                        return false;

                    }

                    if (args[0].equalsIgnoreCase(FileManager.ranks.getConfig().getString("Ranks." + ranks + ".Nome"))){

                        if(SQLiteConstructs.getPlayer_Rank(p).contains(FileManager.ranks.getConfig().getString("Ranks." + ranks + ".Nome"))){

                            SQLiteConstructs.setPlayer_Rank(p, SQLiteConstructs.getPlayer_Rank(p).replace(FileManager.ranks.getConfig().getString("Ranks." + ranks + ".Nome"), ""));

                            Bukkit.broadcastMessage(FileManager.ranks.getConfig().getString("Mensagens.Rank_Removido")
                                    .replace("{player}", p.getName())
                                    .replace("{rank}", FileManager.ranks.getConfig().getString("Ranks." + ranks + ".Nome")));

                        }else sender.sendMessage("§6§l• §c§l!§6§lゞ §fO player escolhido não possui esse rank!");

                    }

                }

            }

        }

        return false;
    }
}
