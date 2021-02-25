package net.rillekk.mlgrush.arena;


import com.google.common.collect.Maps;
import lombok.Getter;

import lombok.Setter;
import net.rillekk.mlgrush.MLGRush;

import net.rillekk.mlgrush.team.TeamColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

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
@Setter
public class Arena
{
    private final Map<TeamColor, Location> spawns = Maps.newEnumMap(TeamColor.class);
    private MLGRush plugin;

    private Location redArenaLocation;
    private Location blueArenaLocation;
    private Location redArenaBedLocation;
    private Location blueArenaBedLocation;
    private double arenaLow;
    private String arenaBuilder;
    private String arenaName;
    private int arenaId;
    private boolean correct = false;
    private volatile State state = State.UNUSED;

    public Arena(String arenaName) {
        this.arenaName = arenaName;
        plugin = MLGRush.getInstance();
    }

    public Arena(Location redArenaLocation, Location blueArenaLocation, Location redArenaBedLocation, Location blueArenaBedLocation, double arenaLow, String arenaBuilder, String arenaName, int arenaId) {
        this.redArenaLocation = redArenaLocation;
        this.blueArenaLocation = blueArenaLocation;
        this.redArenaBedLocation = redArenaBedLocation;
        this.blueArenaBedLocation = blueArenaBedLocation;
        this.arenaLow = arenaLow;
        this.arenaBuilder = arenaBuilder;
        this.arenaName = arenaName;
        this.arenaId = arenaId;
    }

    public void loadArena(int id) {
        arenaId = id;
        plugin.getPlayedArena()[0] = arenaName;
        if (plugin.getLobbyFileConfiguration().get(arenaName+"."+id) == null) {
            plugin.getLogger().info(plugin.getPrefix() + "§dDie Map " + arenaName + " ist nicht richtig aufgesetzt");
            return;
        }

        correct = true;

        // SetArenaSpawnLocation: Red
        double redArenaX = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Red.X");
        double redArenaY = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Red.Y");
        double redArenaZ = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Red.Z");
        float redArenaYaw = (float) plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Red.Yaw");
        float redArenaPitch = (float) plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Red.Pitch");
        World redArenaWorld = Bukkit.getWorld(plugin.getLobbyFileConfiguration().getString(arenaName + "." + arenaId + ".Red.World"));
        redArenaLocation = new Location(redArenaWorld, redArenaX, redArenaY, redArenaZ, redArenaYaw, redArenaPitch);

        // SetArenaSpawnLocation: Blue
        double blueArenaX = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Blue.X");
        double blueArenaY = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Blue.Y");
        double blueArenaZ = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Blue.Z");
        float blueArenaYaw = (float) plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Blue.Yaw");
        float blueArenaPitch = (float) plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Blue.Pitch");
        World blueArenaWorld = Bukkit.getWorld(plugin.getLobbyFileConfiguration().getString(arenaName + "." + arenaId + ".Blue.World"));
        blueArenaLocation = new Location(blueArenaWorld, blueArenaX, blueArenaY, blueArenaZ, blueArenaYaw, blueArenaPitch);


        // SetArenaBed: Red
        double redArenaBedX = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".RedBed.X");
        double redArenaBedY = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".RedBed.Y");
        double redArenaBedZ = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".RedBed.Z");
        World redArenaBedWorld = Bukkit.getWorld(plugin.getLobbyFileConfiguration().getString(arenaName + "." + arenaId + ".RedBed.World"));
        redArenaBedLocation = new Location(redArenaBedWorld, redArenaBedX, redArenaBedY, redArenaBedZ);

        // SetArenaBed: Blue
        double blueArenaBedX = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".BlueBed.X");
        double blueArenaBedY = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".BlueBed.Y");
        double blueArenaBedZ = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".BlueBed.Z");
        World blueArenaBedWorld = Bukkit.getWorld(plugin.getLobbyFileConfiguration().getString(arenaName + "." + arenaId + ".BlueBed.World"));
        blueArenaBedLocation = new Location(blueArenaBedWorld, blueArenaBedX, blueArenaBedY, blueArenaBedZ);


        // SetArenaLow
        arenaLow = plugin.getLobbyFileConfiguration().getDouble(arenaName + "." + arenaId + ".Low");


        // SetArenaBuilder
        arenaBuilder = plugin.getLobbyFileConfiguration().getString(arenaName + "." + arenaId + ".Builder");
    }


    public void use() {
        state = State.USED;
    }

    public void release() {
        state = State.UNUSED;
    }

    public boolean isUsed() {
        return state == State.USED;
    }

    public enum State {
        USED,
        UNUSED
    }
}