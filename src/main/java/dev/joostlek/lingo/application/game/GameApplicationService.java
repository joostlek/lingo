package dev.joostlek.lingo.application.game;

import dev.joostlek.lingo.domain.model.WordLength;
import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryRepository;
import dev.joostlek.lingo.domain.model.game.Game;
import dev.joostlek.lingo.domain.model.game.GameId;
import dev.joostlek.lingo.domain.model.game.GameRepository;
import dev.joostlek.lingo.domain.model.game.round.RoundId;
import dev.joostlek.lingo.domain.model.game.round.RoundRepository;

public class GameApplicationService implements GameService {

    private final GameRepository gameRepository;

    private final DictionaryRepository dictionaryRepository;

    private final RoundRepository roundRepository;

    public GameApplicationService(GameRepository gameRepository, DictionaryRepository dictionaryRepository, RoundRepository roundRepository) {
        this.gameRepository = gameRepository;
        this.dictionaryRepository = dictionaryRepository;
        this.roundRepository = roundRepository;
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

    @Override
    public String startRound(String aGameId) {
        GameId gameId = new GameId(aGameId);

        Game game = this
                .gameRepository()
                .gameOfIdentity(gameId)
                .orElseThrow(
                        () -> new IllegalStateException("Game not found with id " + aGameId)
                );

        RoundId roundId = roundRepository.nextIdentity();

        DictionaryId dictionaryId = new DictionaryId(game.chosenDictionaryId().id());

        Dictionary dictionary = this.dictionaryRepository().dictionaryOfIdentity(dictionaryId);

        game.startRound(roundId, dictionary);

        this.gameRepository().save(game);

        return roundId.id();
    }

    private GameRepository gameRepository() {
        return gameRepository;
    }

    private DictionaryRepository dictionaryRepository() {
        return dictionaryRepository;
    }
}
