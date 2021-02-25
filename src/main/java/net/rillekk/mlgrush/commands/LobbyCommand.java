package net.rillekk.mlgrush.commands;


import lombok.SneakyThrows;

import net.rillekk.mlgrush.MLGRush;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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


public class LobbyCommand implements CommandExecutor
{
    private final MLGRush plugin;
    public LobbyCommand(MLGRush plugin) {
        this.plugin = plugin;
    }

    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;

        double x = plugin.getLobbyFileConfiguration().getDouble("Lobby.X");
        double y = plugin.getLobbyFileConfiguration().getDouble("Lobby.Y");
        double z = plugin.getLobbyFileConfiguration().getDouble("Lobby.Z");
        float yaw = (float) plugin.getLobbyFileConfiguration().getDouble("Lobby.Yaw");
        float pitch = (float) plugin.getLobbyFileConfiguration().getDouble("Lobby.Pitch");
        World world = Bukkit.getWorld(plugin.getLobbyFileConfiguration().getString("Lobby.World"));
        Location location = new Location(world, x, y, z, yaw, pitch);

        if(command.getName().equalsIgnoreCase("leave"))
        {
            player.teleport(location);
        }
        else
            {
                player.sendMessage(plugin.getPrefix() + "§dBitte benutze /leave um zum Spawn zu kommen.");
            }

        return false;
    }
}
