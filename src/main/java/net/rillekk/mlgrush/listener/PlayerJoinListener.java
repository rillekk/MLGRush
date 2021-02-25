package net.rillekk.mlgrush.listener;


import net.rillekk.mlgrush.MLGRush;
import net.rillekk.mlgrush.util.ItemBuilder;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

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

public final class PlayerJoinListener implements Listener
{
    private final MLGRush plugin;
    public PlayerJoinListener(MLGRush plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoin)
    {
        playerJoin.setJoinMessage(null);

        Player player = playerJoin.getPlayer();
        double x = plugin.getLobbyFileConfiguration().getDouble("Lobby.X");
        double y = plugin.getLobbyFileConfiguration().getDouble("Lobby.Y");
        double z = plugin.getLobbyFileConfiguration().getDouble("Lobby.Z");
        float yaw = (float) plugin.getLobbyFileConfiguration().getDouble("Lobby.Yaw");
        float pitch = (float) plugin.getLobbyFileConfiguration().getDouble("Lobby.Pitch");
        World world = Bukkit.getWorld(plugin.getLobbyFileConfiguration().getString("Lobby.World"));
        Location location = new Location(world, x, y, z, yaw, pitch);

        player.teleport(location);

        ItemStack challengerItemStack = new ItemBuilder(Material.DIAMOND_SWORD).setName("§5Herausfordern").toItemStack();
        ItemStack settingsItemStack = new ItemBuilder(Material.REDSTONE_COMPARATOR).setName("§6Inventarsortierung").toItemStack();

        player.getInventory().clear();

        player.getInventory().setItem(0, challengerItemStack);
        player.getInventory().setItem(8, settingsItemStack);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent change) {
        change.setCancelled(true);
    }

    @EventHandler
    public void preventDrop(PlayerDropItemEvent drop)
    {
        drop.setCancelled(drop.getPlayer().getGameMode() != GameMode.CREATIVE);
    }
}
