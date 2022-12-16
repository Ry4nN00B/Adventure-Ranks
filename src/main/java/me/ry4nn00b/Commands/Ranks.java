package me.ry4nn00b.Commands;

import me.ry4nn00b.Managers.FileManager;
import me.ry4nn00b.SQL.SQLiteConstructs;
import me.ry4nn00b.Utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Ranks implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("ranks")){

            Inventory ranks = Bukkit.createInventory(null,
                    9*FileManager.ranks.getConfig().getInt("Inventario.Tamanho"),
                    FileManager.ranks.getConfig().getString("Inventario.Nome"));

            for(String itens : FileManager.ranks.getConfig().getConfigurationSection("Ranks").getKeys(false)){

                ranks.setItem(FileManager.ranks.getConfig().getInt("Ranks." + itens + ".Slot"),
                        new ItemBuilder(Material.getMaterial(FileManager.ranks.getConfig().getInt("Ranks." + itens + ".ItemID")))
                                .setName(FileManager.ranks.getConfig().getString("Ranks." + itens + ".Prefix"))
                                .setLore(FileManager.ranks.getConfig().getStringList("Ranks." + itens + ".Lore")).build());

            }

            p.openInventory(ranks);
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);

        }

        return false;
    }
}
