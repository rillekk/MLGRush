package net.rillekk.mlgrush.commands;


import lombok.SneakyThrows;

import net.rillekk.mlgrush.MLGRush;

import net.rillekk.mlgrush.arena.Arena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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


public class SetArenaCommand implements CommandExecutor {
    private final MLGRush plugin;
    public SetArenaCommand(MLGRush plugin) {
        this.plugin = plugin;
    }


    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("setarena"))
        {
            if (player.hasPermission("mlgrush.setarena"))
            {
                if (args.length == 2)
                {
                    String arenaName = args[0];
                    String getArenaId = args[1];
                    int arenaId = Integer.parseInt(getArenaId);

                    Arena arena = new Arena(arenaName);
                    arena.loadArena(arenaId);

                    if (arena.isCorrect())
                    {
                        plugin.getArenaList().add(arenaName);

                        plugin.getLobbyFileConfiguration().set("arenas", plugin.getArenaList());
                        plugin.getLobbyFileConfiguration().save(plugin.getLobbyFile());

                        player.sendMessage(plugin.getPrefix() + "§dDie Arena wurde erfolgreich gesetzt!");
                    } else
                         {
                             player.sendMessage(plugin.getPrefix() + "§cDie Arena wurde nicht korrekt aufgesetzt!");
                         }
                } else
                    {
                        player.sendMessage(plugin.getPrefix() + "§c/setarena [ArenaName] [ArenaId]");
                    }
            }
        }
        return false;
    }
}
