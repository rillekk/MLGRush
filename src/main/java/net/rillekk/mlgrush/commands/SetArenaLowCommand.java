package net.rillekk.mlgrush.commands;


import lombok.SneakyThrows;

import net.rillekk.mlgrush.MLGRush;

import org.bukkit.Location;
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


public class SetArenaLowCommand implements CommandExecutor
{
    private final MLGRush plugin;
    public SetArenaLowCommand(MLGRush plugin) {
        this.plugin = plugin;
    }

    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;
        Location location = player.getLocation();

        if(command.getName().equalsIgnoreCase("setarenalow"))
        {
            if(player.hasPermission("mlgrush.setarenalow"))
            {
                if(args.length == 2)
                {
                    String arenaName = args[0];
                    String getArenaId = args[1];
                    int arenaId = Integer.parseInt(getArenaId);

                    plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Low", location.getY());
                    plugin.getLobbyFileConfiguration().save(plugin.getLobbyFile());
                    player.sendMessage(plugin.getPrefix() + "§dDie Sterbehöhe in " + arenaName + " mit der ArenaId [" + arenaId + "] wurde erfolgreich gesetzt.");
                } else
                    {
                        player.sendMessage(plugin.getPrefix() + "§c/setarenalow [ArenaName] [ArenaId]");
                    }
            }
        }
        return false;
    }
}