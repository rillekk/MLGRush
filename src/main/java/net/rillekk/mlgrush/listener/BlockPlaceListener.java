package net.rillekk.mlgrush.listener;


import net.rillekk.mlgrush.MLGRush;
import net.rillekk.mlgrush.util.LocationName;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

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


public class BlockPlaceListener implements Listener {
    public final MLGRush plugin;

    public BlockPlaceListener(MLGRush plugin) {
        this.plugin = plugin;
    }

    Map<Player, List<Block>> recordedBlocks = new HashMap<>();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        recordedBlocks.get(event.getPlayer()).add(event.getBlock());
    }

    @EventHandler
    public void onBedDestroy(BlockBreakEvent event) {
        Block destroyedBlock = event.getBlock();

        if (destroyedBlock.getType() == Material.BED_BLOCK) {
            Player redPlayer = plugin.getPlayers()[0];
            Player bluePlayer = plugin.getPlayers()[1];

            List<Block> blocks = recordedBlocks.get(event.getPlayer());
            if (blocks == null || blocks.isEmpty()) return;
            blocks.forEach(b -> b.setType(Material.AIR));
            blocks.clear();

            redPlayer.teleport(plugin.getArenaHandler().loadArenaLocation(plugin.getPlayedArena()[0], LocationName.REDSPAWN));
            bluePlayer.teleport(plugin.getArenaHandler().loadArenaLocation(plugin.getPlayedArena()[0], LocationName.BLUESPAWN));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        List<Block> blocks = recordedBlocks.get(event.getPlayer());
        if (blocks == null || blocks.isEmpty()) return;
        blocks.forEach(b -> b.setType(Material.AIR));
        blocks.clear();

    }

}
