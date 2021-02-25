package net.rillekk.mlgrush.listener;


import lombok.Getter;

import net.rillekk.mlgrush.MLGRush;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.Map;

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

@Getter
public class EntityDamageByEntityListener implements Listener {
    private final MLGRush plugin;

    public EntityDamageByEntityListener(MLGRush plugin) {
        this.plugin = plugin;
    }

    private final Map<Player, Player> queue = new HashMap<>();
    private Player victim;
    private Player killer;

    @EventHandler
    public void preventEntityDamage(EntityDamageEvent damage) {
        damage.setCancelled(true);
    }

    @EventHandler
    public void onDamageEntityByEntity(EntityDamageByEntityEvent damage) {
        if (!(damage.getEntity() instanceof Player && damage.getDamager() instanceof Player)) {
            return;
        }

        victim = (Player) damage.getEntity();
        killer = (Player) damage.getDamager();

        if (!killer.getItemInHand().getType().equals(Material.DIAMOND_SWORD)) {
            return;
        }

        if (queue.containsKey(killer) && queue.get(killer) == victim) {
            queue.remove(killer);
            queue.remove(victim);

            victim.sendMessage(plugin.getPrefix() + "§a" + killer.getName() + " §dhat deine Herausforderung angenommen.");
            victim.playSound(killer.getLocation(), Sound.LEVEL_UP, 2, 1);

            killer.sendMessage(plugin.getPrefix() + "§a" + victim.getName() + "'s §dHerausforderung wurde angenommen.");
            killer.playSound(victim.getLocation(), Sound.LEVEL_UP, 2, 1);

            this.plugin.getGameController().startGame(killer, victim);

            plugin.getInArenaQueue().put(killer, victim);

            plugin.getArenaHandler().inventorySetter();

            victim.openInventory(plugin.getMapGui());

        } else {
            killer.sendMessage(plugin.getPrefix() + "§a" + victim.getName() + " §dwurde herausgefordert.");
            killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 2, 1);

            victim.sendMessage(plugin.getPrefix() + "§a" + killer.getName() + " §dhat dich herausgefordert.");
            victim.playSound(victim.getLocation(), Sound.LEVEL_UP, 2, 1);

            queue.put(victim, killer);
        }
    }
}