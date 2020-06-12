package dev.joostlek.lingo.domain.model.game;

import java.util.Collection;
import java.util.Optional;

public interface GameRepository {
    Optional<Game> gameOfIdentity(GameId gameId);

    GameId nextIdentity();

    void save(Game game);

    Collection<Game> getAllGames();
}
