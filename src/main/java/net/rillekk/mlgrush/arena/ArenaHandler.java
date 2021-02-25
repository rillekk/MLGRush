package net.rillekk.mlgrush.arena;


import lombok.Getter;

import net.rillekk.mlgrush.MLGRush;

import net.rillekk.mlgrush.util.LocationName;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

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
public class ArenaHandler
{

    private final MLGRush plugin;
    public ArenaHandler(MLGRush plugin) {
        this.plugin = plugin;
    }

    public void inventorySetter() {
        ItemStack itemstack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) itemstack.getItemMeta();
        skullMeta.setOwner("BlockminersTV");
        itemstack.setItemMeta(skullMeta);
        for (int i = 0; i < plugin.getArenaList().size(); i++) {
            String s = "" + plugin.getArenaList();
            // [map1, map2]
            String maps2 = s.replace("[", "").replace("]", "");
            // [ ]
            String[] maps = maps2.split(",");
            // maps1, maps2

            skullMeta.setDisplayName("§6" + maps[i]);
            itemstack.setItemMeta(skullMeta);

            plugin.getMapGui().setItem(i, itemstack);
        }
    }


    public Location loadArenaLocation(String arenaName, LocationName locationType)
    {
            int arenaId = plugin.getCurrentArenaId()[0];

            switch(locationType)
            {
                case REDSPAWN:
                    double redArenaX = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Red.X");
                    double redArenaY = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Red.Y");
                    double redArenaZ = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Red.Z");
                    float redArenaYaw = (float) plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Red.Yaw");
                    float redArenaPitch = (float) plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Red.Pitch");
                    World redArenaWorld = Bukkit.getWorld(plugin.getLobbyFileConfiguration().getString(arenaName + "." + arenaId + ".Red.World"));

                    return new Location(redArenaWorld, redArenaX, redArenaY, redArenaZ, redArenaYaw, redArenaPitch);

                case BLUESPAWN:
                    double blueArenaX = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Blue.X");
                    double blueArenaY = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Blue.Y");
                    double blueArenaZ = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Blue.Z");
                    float blueArenaYaw = (float) plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Blue.Yaw");
                    float blueArenaPitch = (float) plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Blue.Pitch");
                    World blueArenaWorld = Bukkit.getWorld(plugin.getLobbyFileConfiguration().getString(arenaName + "." + arenaId + ".Blue.World"));

                    return new Location(blueArenaWorld, blueArenaX, blueArenaY, blueArenaZ, blueArenaYaw, blueArenaPitch);

                case REDBED:
                    double redArenaBedX = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".RedBed.X");
                    double redArenaBedY = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".RedBed.Y");
                    double redArenaBedZ = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".RedBed.Z");
                    World redArenaBedWorld = Bukkit.getWorld(plugin.getLobbyFileConfiguration().getString(arenaName + "." + arenaId + ".RedBed.World"));

                    return new Location(redArenaBedWorld, redArenaBedX, redArenaBedY, redArenaBedZ);

                case BLUEBED:
                    double blueArenaBedX = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".BlueBed.X");
                    double blueArenaBedY = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".BlueBed.Y");
                    double blueArenaBedZ = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".BlueBed.Z");
                    World blueArenaBedWorld = Bukkit.getWorld(plugin.getLobbyFileConfiguration().getString(arenaName + "." + arenaId + ".BlueBed.World"));

                    return new Location(blueArenaBedWorld, blueArenaBedX, blueArenaBedY, blueArenaBedZ);
            }
            return null;
        }

    public Arena nextUnUsedArena(String arenaName)
    {
        FileConfiguration config = plugin.getLobbyFileConfiguration();
        ConfigurationSection section = config.getConfigurationSection(arenaName);

        Arena arena = new Arena(arenaName);
        for (Map.Entry<String, Object> entry : section.getValues(false).entrySet())
        {
          int arenaId = Integer.parseInt(entry.getKey());
          plugin.getCurrentArenaId()[0] = arenaId;

          if(!arena.isUsed())
                break;
        }

        arena.use();

        return arena;
    }
}


