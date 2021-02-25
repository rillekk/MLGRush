package net.rillekk.mlgrush;


import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import net.rillekk.mlgrush.arena.Arena;
import net.rillekk.mlgrush.arena.ArenaStore;
import net.rillekk.mlgrush.commands.*;
import net.rillekk.mlgrush.arena.ArenaHandler;
import net.rillekk.mlgrush.game.GameController;
import net.rillekk.mlgrush.listener.*;

import net.rillekk.mlgrush.mysql.MySQL;
import net.rillekk.mlgrush.mysql.MySQLInventory;
import net.rillekk.mlgrush.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import java.io.IOException;
import java.util.HashMap;
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

@Getter
@Setter
public class MLGRush extends JavaPlugin
{
    private static MLGRush instance;

    private String prefix;
    private HashMap<Player, Player> inArenaQueue;
    private HashMap<Player, Player> inGame;
    private Location redPlayerLocation;
    private Location bluePlayerLocation;
    private List<String> arenaList;
    private File lobbyFile;
    private File mysqlFile;
    private FileConfiguration lobbyFileConfiguration;
    private FileConfiguration mysqlFileConfiguration;
    private Inventory mapGui;
    private ArenaHandler arenaHandler;
    private Player[] players;
    private String[] playedArena;
    private Integer[] currentArenaId;
    private ArenaStore arenas;
    private MySQL mySQL;
    private Integer[] inventorySlot;
    private MySQLInventory mySQLInventory;
    private ItemStack stickItemStack;
    private ItemStack pickaxeItemStack;
    private ItemStack blocksItemStack;
    private GameController gameController;

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;

        this.mysqlFile = new File(getDataFolder().toString(), "mysql.yml");
        this.lobbyFile = new File(getDataFolder(), "locations.yml");
        this.lobbyFileConfiguration = YamlConfiguration.loadConfiguration(lobbyFile);
        this.mysqlFileConfiguration = YamlConfiguration.loadConfiguration(mysqlFile);
        this.arenaList = lobbyFileConfiguration.getStringList("arenas");
        setMySQL();
        this.mySQL = new MySQL(getMysqlFileConfiguration().getString("Host"),
                getMysqlFileConfiguration().getString("Port"),
                getMysqlFileConfiguration().getString("Database"),
                getMysqlFileConfiguration().getString("Username"),
                getMysqlFileConfiguration().getString("Password"),
                this);
        this.prefix = "§8» §6OhneLimit §8× ";
        this.arenaHandler = new ArenaHandler(this);
        this.inArenaQueue = new HashMap<>();
        this.mapGui = Bukkit.createInventory(null, 9, "§d§lMapGui");
        this.players = new Player[2];
        this.playedArena = new String[1];
        this.currentArenaId = new Integer[1];
        this.inventorySlot = new Integer[3];
        this.mySQLInventory = new MySQLInventory(this);
        this.stickItemStack = new ItemBuilder(Material.STICK).setName("§6Stick").addEnchant(Enchantment.KNOCKBACK, 1).toItemStack();
        this.pickaxeItemStack = new ItemBuilder(Material.GOLD_PICKAXE).setName("§6Spitzhacke").toItemStack();
        this.blocksItemStack = new ItemBuilder(Material.SANDSTONE).setName("§6Bloecke").toItemStack();
        this.blocksItemStack.setAmount(64);
        this.gameController = new GameController(this);

        super.getCommand("setlobby").setExecutor(new SetLobbyCommand(this));
        super.getCommand("setarenaspawn").setExecutor(new SetArenaSpawnsCommand(this));
        super.getCommand("setarenabed").setExecutor(new SetArenaBedCommand(this));
        super.getCommand("setarenalow").setExecutor(new SetArenaLowCommand(this));
        super.getCommand("setarenabuilder").setExecutor(new SetArenaBuilderCommand(this));
        super.getCommand("setarena").setExecutor(new SetArenaCommand(this));
        super.getCommand("leave").setExecutor(new LobbyCommand(this));

        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventorySortingListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
    }

    public static MLGRush getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
    }


    public void setMySQL() throws IOException {
        if (!getMysqlFileConfiguration().contains("Host"))
            getMysqlFileConfiguration().set("Host", "localhost");

        if (!getMysqlFileConfiguration().contains("Port"))
            getMysqlFileConfiguration().set("Port", Integer.valueOf(3306));

        if (!getMysqlFileConfiguration().contains("Database"))
            getMysqlFileConfiguration().set("Database", "database");

        if (!getMysqlFileConfiguration().contains("Username"))
            getMysqlFileConfiguration().set("Username", "username");

        if (!getMysqlFileConfiguration().contains("Password"))
            getMysqlFileConfiguration().set("Password", "password");

        getMysqlFileConfiguration().save(mysqlFile);
    }
}
