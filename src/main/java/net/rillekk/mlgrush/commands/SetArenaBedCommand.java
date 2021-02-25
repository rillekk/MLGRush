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


public class SetArenaBedCommand implements CommandExecutor
{
    private final MLGRush plugin;
    public SetArenaBedCommand(MLGRush plugin) {
        this.plugin = plugin;
    }


    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;
        Location location = player.getLocation();

        if(command.getName().equalsIgnoreCase("setarenabed"))
        {
            if(player.hasPermission("mlgrush.setarenabed"))
            {
                if(args.length == 3)
                {
                    String arenaName = args[1];
                    String getArenaId = args[2];
                    int arenaId = Integer.parseInt(getArenaId);

                    switch(args[0])
                    {
                        case "red":
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".RedBed.World", location.getWorld().getName());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".RedBed.X", location.getBlockX());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".RedBed.Y", location.getBlockY());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".RedBed.Z", location.getZ());
                            plugin.getLobbyFileConfiguration().save(plugin.getLobbyFile());
                            player.sendMessage(plugin.getPrefix() + "§dDas Bett in " + arenaName + " von Team Rot, mit der ArenaId [" + arenaId + "] wurde erfolgreich gesetzt.");
                            break;

                        case "blue":
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".BlueBed.World", location.getWorld().getName());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".BlueBed.X", location.getBlockX());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".BlueBed.Y", location.getBlockY());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".BlueBed.Z", location.getZ());
                            plugin.getLobbyFileConfiguration().save(plugin.getLobbyFile());
                            player.sendMessage(plugin.getPrefix() + "§dDas Bett in " + arenaName + " von Team Blau, mit der ArenaId [" + arenaId + "] wurde erfolgreich gesetzt.");
                            break;

                        default:
                            player.sendMessage(plugin.getPrefix() + "§c/setarenabed [blue / red] [ArenaName] [ArenaId]");
                    }
                } else
                     {
                         player.sendMessage(plugin.getPrefix() + "§c/setarenabed [blue / red] [ArenaName] [ArenaId]");
                     }
            }
        }
        return false;
    }
}
