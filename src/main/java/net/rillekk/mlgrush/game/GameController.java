package net.rillekk.mlgrush.game;


import net.rillekk.mlgrush.MLGRush;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


public final class GameController {

    private final MLGRush plugin;
    private final List<Game> activeGames;

    public GameController(MLGRush plugin) {
        this.plugin = plugin;
        this.activeGames = new ArrayList<>();
    }

    public void startGame(Player one, Player two) {
        this.activeGames.add(Game.create(one, two));
    }

    public Optional<Game> findGame(Player player) {
        return this.activeGames
                .stream()
                .filter(game -> game.getPlayers().getLeft().equals(player)
                        || game.getPlayers().getRight().equals(player))
                .findFirst();
    }
}
