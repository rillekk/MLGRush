package net.rillekk.mlgrush.arena;

import com.google.common.collect.Maps;

import java.util.Map;
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


public final class InMemoryArenaStore implements ArenaStore
{
    private final Map<String, Arena> arenas = Maps.newHashMap();

    public static ArenaStore create() {
        return new InMemoryArenaStore();
    }

    public void load() {
        // TODO: 31.12.2020 load and insert arenas
    }

    @Override
    public void insert(Arena arena) {
        arenas.put(arena.getArenaName().toLowerCase(), arena);
    }

    @Override
    public Optional<Arena> findByName(String name) {
        return Optional.ofNullable(arenas.get(name.toLowerCase()));
    }

}