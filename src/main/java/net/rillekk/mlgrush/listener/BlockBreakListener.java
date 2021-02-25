package net.rillekk.mlgrush.listener;


import net.rillekk.mlgrush.MLGRush;

import net.rillekk.mlgrush.util.LocationName;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

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


public class BlockBreakListener implements Listener {
    private final MLGRush plugin;

    public BlockBreakListener(MLGRush plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBedDestroy(BlockBreakEvent event) {
        Block destroyedBlock = event.getBlock();

        if (destroyedBlock.getType() == Material.BED_BLOCK) {
            Player redPlayer = plugin.getPlayers()[0];
            Player bluePlayer = plugin.getPlayers()[1];

            redPlayer.teleport(plugin.getArenaHandler().loadArenaLocation(plugin.getPlayedArena()[0], LocationName.REDSPAWN));
            bluePlayer.teleport(plugin.getArenaHandler().loadArenaLocation(plugin.getPlayedArena()[0], LocationName.BLUESPAWN));
        }
    }
}