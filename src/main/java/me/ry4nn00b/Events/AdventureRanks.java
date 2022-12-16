package me.ry4nn00b.Events;

import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;
import me.ry4nn00b.Managers.FileManager;
import me.ry4nn00b.SQL.SQLiteConstructs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdventureRanks implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){

        if (e.getSlot() < 0)
            return;
        if (!e.getCurrentItem().hasItemMeta())
            return;

        Player p = (Player) e.getWhoClicked();

        NBTCompound ForgeData = NBTManager.getInstance().readForgeData(p);
        NBTCompound PlayerPersisted = ForgeData.getCompound("PlayerPersisted");

        int STR = PlayerPersisted.getInt("jrmcStrI");
        int CON = PlayerPersisted.getInt("jrmcCnsI");
        int WIL = PlayerPersisted.getInt("jrmcWilI");
        int SPI = PlayerPersisted.getInt("jrmcCncI");

        if(e.getInventory().getTitle().equalsIgnoreCase(FileManager.ranks.getConfig().getString("Inventario.Nome"))){
            e.setCancelled(true);

            for(String itens : FileManager.ranks.getConfig().getConfigurationSection("Ranks").getKeys(false)){

                int STR1 = Integer.parseInt(String.valueOf(FileManager.ranks.getConfig().getInt("Ranks." + itens + ".Atributos.STR")));
                int CON1 = Integer.parseInt(String.valueOf(FileManager.ranks.getConfig().getInt("Ranks." + itens + ".Atributos.CON")));
                int WIL1 = Integer.parseInt(String.valueOf(FileManager.ranks.getConfig().getInt("Ranks." + itens + ".Atributos.WIL")));
                int SPI1 = Integer.parseInt(String.valueOf(FileManager.ranks.getConfig().getInt("Ranks." + itens + ".Atributos.SPI")));

                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(FileManager.ranks.getConfig().getString("Ranks." + itens + ".Prefix"))){

                    if (STR >= STR1 || CON >= CON1 || WIL >= WIL1 || SPI >= SPI1) {

                        if(!SQLiteConstructs.getPlayer_Rank(p).contains(FileManager.ranks.getConfig().getString("Ranks." + itens + ".Nome"))) {
                        Bukkit.broadcastMessage(FileManager.ranks.getConfig().getString("Mensagens.RankUP")
                                .replace("{player}", p.getName())
                                .replace("{rank}", FileManager.ranks.getConfig().getString("Ranks." + itens + ".Nome")));

                        if(SQLiteConstructs.getPlayer_Rank(p).equalsIgnoreCase("Nenhum")){
                            SQLiteConstructs.setPlayer_Rank(p, FileManager.ranks.getConfig().getString("Ranks." + itens + ".Nome"));
                        }else
                            SQLiteConstructs.setPlayer_Rank(p, SQLiteConstructs.getPlayer_Rank(p) + ", " + FileManager.ranks.getConfig().getString("Ranks." + itens + ".Nome"));

                        }else p.sendMessage("§6§l• §c§l!§6§lゞ §fVocê já possui esse rank!");


                    } else p.sendMessage(FileManager.ranks.getConfig().getString("Mensagens.Sem_Status"));

                }

            }

        }

    }

}
