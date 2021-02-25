package net.rillekk.mlgrush.commands;


import lombok.SneakyThrows;

import net.rillekk.mlgrush.MLGRush;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

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


public class SetArenaBuilderCommand implements CommandExecutor
{
    private final MLGRush plugin;
    public SetArenaBuilderCommand(MLGRush plugin) {
        this.plugin = plugin;
    }

    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("setarenabuilder"))
        {
            if(player.hasPermission("mlgrush.setarenabuilder"))
            {
                if(args.length == 3)
                {
                    String arenaName = args[1];
                    String getArenaId = args[2];
                    int arenaId = Integer.parseInt(getArenaId);
                    String getArenaBuilder = args[0];


                    plugin.getLobbyFileConfiguration().set(arenaName + "." + arenaId + ".Builder", getArenaBuilder);
                    plugin.getLobbyFileConfiguration().save(plugin.getLobbyFile());
                    player.sendMessage(plugin.getPrefix() + "§dDer Builder von " + arenaName + " wurde in der Map mit der ArenaId [" + arenaId + "] erfolgreich gesetzt.");
                }
                else
                   {
                       player.sendMessage(plugin.getPrefix() + "§c/setarenabuilder [Builder] [ArenaName] [ArenaId]");
                   }
            }
        }
        return false;
    }
}
