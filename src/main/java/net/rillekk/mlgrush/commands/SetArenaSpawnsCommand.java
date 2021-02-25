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


public class SetArenaSpawnsCommand implements CommandExecutor {
    private final MLGRush plugin;
    public SetArenaSpawnsCommand(MLGRush plugin) {
        this.plugin = plugin;
    }

    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("mlgrush.setarenaspawns")) {
            Location location = player.getLocation();

            if (command.getName().equalsIgnoreCase("setarenaspawn")) {

                if (args.length == 3) {
                    String arenaName = args[1];
                    String getArenaId = args[2];
                    int arenaId = Integer.parseInt(getArenaId);
                    switch (args[0]) {
                        case "red":
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Red.World", location.getWorld().getName());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Red.X", location.getBlockX());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Red.Y", location.getBlockY());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Red.Z", location.getBlockZ());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Red.Yaw", location.getYaw());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Red.Pitch", location.getPitch());
                            plugin.getLobbyFileConfiguration().save(plugin.getLobbyFile());
                            player.sendMessage(plugin.getPrefix() + "§dDer ArenaSpawn " + arenaName + " von Team Rot, mit der ArenaId [" + arenaId + "] wurde erfolgreich gesetzt.");
                            break;

                        case "blue":
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Blue.World", location.getWorld().getName());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Blue.X", location.getBlockX());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Blue.Y", location.getBlockY());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Blue.Z", location.getBlockZ());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Blue.Yaw", location.getYaw());
                            plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Blue.Pitch", location.getPitch());
                            plugin.getLobbyFileConfiguration().save(plugin.getLobbyFile());
                            player.sendMessage(plugin.getPrefix() + "§dDer ArenaSpawn in " + arenaName + " von Team Blau, mit der ArenaId [" + arenaId + "] wurde erfolgreich gesetzt.");
                            break;

                        default:
                            player.sendMessage(plugin.getPrefix() + "§c/setarenaspawn [blue / red] [ArenaName] [ArenaId]");
                    }
                } else
                    {
                        player.sendMessage(plugin.getPrefix() + "§c/setarenaspawn [blue / red] [ArenaName] [ArenaId]");
                    }
            }
        }
        return false;
    }
}