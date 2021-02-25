package net.rillekk.mlgrush.listener;


import net.rillekk.mlgrush.MLGRush;
import net.rillekk.mlgrush.util.ItemBuilder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
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


public class InventorySortingListener implements Listener {
    private final MLGRush plugin;

    public InventorySortingListener(MLGRush plugin) {
        this.plugin = plugin;
    }

    private final Inventory inventory = Bukkit.createInventory(null, 9, "§6§lInventarsortierung");

    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        if (event.getItem().getType() != Material.REDSTONE_COMPARATOR) return;

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();

            inventory.setItem(0, plugin.getStickItemStack());
            inventory.setItem(1, plugin.getPickaxeItemStack());
            inventory.setItem(2, plugin.getBlocksItemStack());

            player.openInventory(inventory);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) throws SQLException
    {
        Player player = (Player) event.getPlayer();

        if (event.getInventory().getTitle().equalsIgnoreCase("§6§lInventarsortierung"))
        {
            for (int i = 0; i <= 8; i++) {
                if (event.getInventory().getItem(i) != null)
                {
                    System.out.println("Das ist i:" + i);
                    switch (event.getInventory().getItem(i).getType())
                    {
                        case STICK:
                            plugin.getInventorySlot()[0] = i;
                            break;
                        case GOLD_PICKAXE:
                            plugin.getInventorySlot()[1] = i;
                            break;
                        case SANDSTONE:
                            plugin.getInventorySlot()[2] = i;
                            break;
                    }
                }
                System.out.println(i);
            }

            plugin.getMySQLInventory().setItemsSlots(player);
        }
    }
}