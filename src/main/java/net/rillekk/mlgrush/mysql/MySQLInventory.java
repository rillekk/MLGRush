package net.rillekk.mlgrush.mysql;


import net.rillekk.mlgrush.MLGRush;

import org.bukkit.entity.Player;


import java.sql.ResultSet;
import java.sql.SQLException;


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


public class MySQLInventory {
    private final MLGRush plugin;

    public MySQLInventory(MLGRush plugin) {
        this.plugin = plugin;
        plugin.getMySQL().update("CREATE TABLE IF NOT EXISTS MLGRushInventories (UUID VARCHAR(100), Stick INT(5), Pickaxe INT(5), Blocks INT(5))");
    }


    public boolean userExists(Player player) throws SQLException {
        try {
            ResultSet rs = plugin.getMySQL().query("SELECT Stick FROM MLGRushInventories WHERE UUID = '" + player.getUniqueId().toString() + "'");
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer getStickSlot(Player player) throws SQLException {
        if (userExists(player))
            try {
                ResultSet rs = plugin.getMySQL().query("SELECT Stick FROM MLGRushInventories WHERE UUID = '" + player.getUniqueId().toString() + "'");

                if (rs.next())
                    return rs.getInt("Stick");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        return 0;
    }

    public Integer getPickaxeSlot(Player player) throws SQLException {
        if (userExists(player))
            try {
                ResultSet rs = plugin.getMySQL().query("SELECT Pickaxe FROM MLGRushInventories WHERE UUID = '" + player.getUniqueId().toString() + "'");

                if (rs.next())
                    return rs.getInt("Pickaxe");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        return 1;
    }

    public int getBlocksSlot(Player player) throws SQLException {
        if (userExists(player))
            try {
                ResultSet rs = plugin.getMySQL().query("SELECT Blocks FROM MLGRushInventories WHERE UUID = '" + player.getUniqueId().toString() + "'");

                if (rs.next())
                    return rs.getInt("Blocks");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        return 2;
    }

    public void setItemsSlots(Player player) throws SQLException {
        if (userExists(player)) {
            plugin.getMySQL().update("UPDATE MLGRushInventories SET Stick = '" + plugin.getInventorySlot()[0] + "', Pickaxe = '" + plugin.getInventorySlot()[1] + "', Blocks = '" + plugin.getInventorySlot()[2] + "' WHERE UUID = '" + player.getUniqueId().toString() + "'");
        } else {
            ResultSet insertValuesRs = plugin.getMySQL().query("INSERT INTO MLGRushInventories (UUID, Stick, Pickaxe, Blocks) VALUES ('" + player.getUniqueId().toString() + "', '" + plugin.getInventorySlot()[0] + "', '" + plugin.getInventorySlot()[1] + "', '" + plugin.getInventorySlot()[2] + "')");
        }
    }
}