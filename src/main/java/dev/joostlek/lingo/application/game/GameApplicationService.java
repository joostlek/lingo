package dev.joostlek.lingo.application.game;

import dev.joostlek.lingo.domain.model.WordLength;
import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryRepository;
import dev.joostlek.lingo.domain.model.game.Game;
import dev.joostlek.lingo.domain.model.game.GameId;
import dev.joostlek.lingo.domain.model.game.GameRepository;

public class GameApplicationService implements GameService {

    private final GameRepository gameRepository;

    private final DictionaryRepository dictionaryRepository;

    public GameApplicationService(GameRepository gameRepository, DictionaryRepository dictionaryRepository) {
        this.gameRepository = gameRepository;
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    public String createGame(int aWordLength, String aDictionaryId) {
        GameId gameId = this.gameRepository().nextIdentity();

        WordLength wordLength = WordLength.valueOf(aWordLength);

        DictionaryId dictionaryId = new DictionaryId(aDictionaryId);

        Dictionary dictionary = this.dictionaryRepository().dictionaryOfIdentity(dictionaryId);

        if (dictionary == null) {
            throw new IllegalStateException("Can't start game with unknown dictionary " + aDictionaryId + ".");
        }

        Game game = new Game(gameId, wordLength, dictionaryId);

        this.gameRepository().save(game);

        return gameId.id();
    }

    private GameRepository gameRepository() {
        return gameRepository;
    }

    private DictionaryRepository dictionaryRepository() {
        return dictionaryRepository;
    }
}
