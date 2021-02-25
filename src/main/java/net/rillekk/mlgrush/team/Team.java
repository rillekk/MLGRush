package net.rillekk.mlgrush.team;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

import java.util.Collection;

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


public final class Team
{
    private final Collection<Player> members = Lists.newArrayList();
    private final TeamColor color;

    private Team(TeamColor color) {
        this.color = color;
    }

    public void addMember(Player player) {
        members.add(player);
    }

    public void removePlayer(Player player) {
        members.remove(player);
    }

    public boolean contains(Player player) {
        return members.contains(player);
    }

    public TeamColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("members", members)
                .add("color", color)
                .toString();
    }

    public static Team ofColor(TeamColor color) {
        Preconditions.checkNotNull(color, "color");
        return new Team(color);
    }
}
