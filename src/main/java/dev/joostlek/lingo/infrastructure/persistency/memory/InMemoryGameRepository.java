package dev.joostlek.lingo.infrastructure.persistency.memory;

import dev.joostlek.lingo.domain.model.game.Game;
import dev.joostlek.lingo.domain.model.game.GameId;
import dev.joostlek.lingo.domain.model.game.GameRepository;
import dev.joostlek.lingo.domain.model.game.round.Round;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class InMemoryGameRepository implements GameRepository {

    public InMemoryGameRepository() {
        super();
    }

    @Override
    public Optional<Game> gameOfIdentity(GameId gameId) {
        return Optional.ofNullable(
                InMemoryObject.instance()
                        .games()
                        .get(gameId)
        );
    }

    @Override
    public GameId nextIdentity() {
        UUID uuid = UUID.randomUUID();
        GameId gameId = new GameId(uuid.toString());
        if (InMemoryObject.instance()
                .games()
                .containsKey(gameId)) {
            return nextIdentity();
        }
        return gameId;
    }

    @Override
    public void save(Game game) {
        InMemoryObject.instance()
                .games()
                .put(game.gameId(), game);
        for (Round round : game.rounds()) {
            InMemoryObject
                    .instance()
                    .rounds()
                    .put(round.roundId(), round);
        }
    }

    @Override
    public Collection<Game> getAllGames() {
        return InMemoryObject
                .instance()
                .games()
                .values();
    }
}
