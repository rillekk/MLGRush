package net.rillekk.mlgrush.listener;


import net.rillekk.mlgrush.MLGRush;

import net.rillekk.mlgrush.arena.Arena;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

/***************************************************************
 *                                                             *
 *   @author Rillekk                                           *
 *   @Instagram: rillekk                                       *
 *   @Discord: Rillekk#8642                                    *
 *                                                             *
 *                                                             *
 *   Jede Art der Vervielfältigung, Verbreitung, Verleihung,   *
 *   öffentlich Zugänglichmachung oder andere Nutzung bedarf   *
 *   der ausdrücklichen, schriftlichen Zustimmung von Rillekk. *
 *                                                             *
 ***************************************************************/


public class InventoryClickListener implements Listener
{
    private final MLGRush plugin;
    public InventoryClickListener(MLGRush plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) throws SQLException
    {
        ItemStack clickedItemItemStack = event.getCurrentItem();

        if (event.getInventory().getTitle().equalsIgnoreCase("§d§lMapGui"))
        {
            if (clickedItemItemStack.getType() == Material.SKULL_ITEM)
            {
                Player killer = plugin.getPlayers()[1];
                Player victim = plugin.getPlayers()[0];

                if (plugin.getInArenaQueue().containsKey(killer) && plugin.getInArenaQueue().get(killer) == victim) {


                    String split = clickedItemItemStack.getItemMeta().getDisplayName();
                    String arenaName = ChatColor.stripColor(split);

                    plugin.getPlayedArena()[0] = arenaName;

                    killer.closeInventory();

                    Arena unUsedArena = plugin.getArenaHandler().nextUnUsedArena(plugin.getPlayedArena()[0]);
                    unUsedArena.loadArena(plugin.getCurrentArenaId()[0]);

                    killer.getInventory().clear();

                    killer.teleport(unUsedArena.getBlueArenaLocation());

                    killer.getInventory().setItem(plugin.getMySQLInventory().getStickSlot(killer), plugin.getStickItemStack());
                    killer.getInventory().setItem(plugin.getMySQLInventory().getPickaxeSlot(killer), plugin.getPickaxeItemStack());
                    killer.getInventory().setItem(plugin.getMySQLInventory().getBlocksSlot(killer), plugin.getBlocksItemStack());


                    victim.getInventory().clear();

                    victim.teleport(unUsedArena.getRedArenaLocation());

                    victim.getInventory().setItem(plugin.getMySQLInventory().getStickSlot(victim), plugin.getStickItemStack());
                    victim.getInventory().setItem(plugin.getMySQLInventory().getPickaxeSlot(victim), plugin.getPickaxeItemStack());
                    victim.getInventory().setItem(plugin.getMySQLInventory().getBlocksSlot(victim), plugin.getBlocksItemStack());
                }
            }
        }
    }
}