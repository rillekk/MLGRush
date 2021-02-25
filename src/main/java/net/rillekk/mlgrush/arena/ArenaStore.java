package net.rillekk.mlgrush.arena;

import java.util.Optional;

public interface ArenaStore
{
    void insert(Arena arena);

    Optional<Arena> findByName(String name);
}