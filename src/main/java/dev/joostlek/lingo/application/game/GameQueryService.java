package dev.joostlek.lingo.application.game;

import dev.joostlek.lingo.domain.model.game.Game;
import dev.joostlek.lingo.domain.model.game.GameId;
import dev.joostlek.lingo.domain.model.game.GameRepository;

import java.util.Collection;

public class GameQueryService {
    private final GameRepository gameRepository;

    public GameQueryService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getGameByGameId(String aGameId) {
        GameId gameId = new GameId(aGameId);
        return this.gameRepository()
                .gameOfIdentity(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find game with id " + aGameId + "."));
    }

    public Collection<Game> getAllGames() {
        return this.gameRepository().getAllGames();
    }

    private GameRepository gameRepository() {
        return gameRepository;
    }
}
