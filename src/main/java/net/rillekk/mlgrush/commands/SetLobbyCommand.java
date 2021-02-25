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


public class SetLobbyCommand implements CommandExecutor
{
    private final MLGRush plugin;
    public SetLobbyCommand(MLGRush plugin) {
        this.plugin = plugin;
    }


    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;

            if(player.hasPermission("mlgrush.setlobby"))
            {
                if(args.length == 0)
                {
                    Location location = player.getLocation();
                    plugin.getLobbyFileConfiguration().set("Lobby.World", location.getWorld().getName());
                    plugin.getLobbyFileConfiguration().set("Lobby.X", location.getBlockX());
                    plugin.getLobbyFileConfiguration().set("Lobby.Y", location.getBlockY());
                    plugin.getLobbyFileConfiguration().set("Lobby.Z", location.getZ());
                    plugin.getLobbyFileConfiguration().set("Lobby.Yaw", location.getYaw());
                    plugin.getLobbyFileConfiguration().set("Lobby.Pitch", location.getPitch());
                    plugin.getLobbyFileConfiguration().save(plugin.getLobbyFile());
                    player.sendMessage(plugin.getPrefix() + "§dDie Lobby wurde erfolgreich gesetzt.");
                }
                else
                {
                    player.sendMessage(plugin.getPrefix() + " §dDie Richtige Benutzung ist §a/setlobby§d.");
                }
            }
        }
        return false;
    }
}
