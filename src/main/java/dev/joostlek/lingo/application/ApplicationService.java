package dev.joostlek.lingo.application;

import dev.joostlek.lingo.application.dictionary.DictionaryApplicationService;
import dev.joostlek.lingo.application.dictionary.DictionaryQueryService;
import dev.joostlek.lingo.application.dictionary.DictionaryService;
import dev.joostlek.lingo.application.game.GameApplicationService;
import dev.joostlek.lingo.application.game.GameQueryService;
import dev.joostlek.lingo.application.game.GameService;
import dev.joostlek.lingo.application.game.round.RoundApplicationService;
import dev.joostlek.lingo.application.game.round.RoundQueryService;
import dev.joostlek.lingo.application.game.round.RoundService;
import dev.joostlek.lingo.application.game.round.turn.TurnQueryService;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryRepository;
import dev.joostlek.lingo.domain.model.dictionary.word.WordRepository;
import dev.joostlek.lingo.domain.model.game.GameRepository;
import dev.joostlek.lingo.domain.model.game.round.RoundRepository;
import dev.joostlek.lingo.domain.model.game.round.TurnRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final DictionaryRepository dictionaryRepository;

    private final WordRepository wordRepository;

    private final GameRepository gameRepository;

    private final RoundRepository roundRepository;

    private final TurnRepository turnRepository;

    public ApplicationService(DictionaryRepository dictionaryRepository, WordRepository wordRepository, GameRepository gameRepository, RoundRepository roundRepository, TurnRepository turnRepository) {
        this.gameRepository = gameRepository;
        this.dictionaryRepository = dictionaryRepository;
        this.wordRepository = wordRepository;
        this.roundRepository = roundRepository;
        this.turnRepository = turnRepository;
    }

    @Bean
    public DictionaryService dictionaryService() {
        return new DictionaryApplicationService(dictionaryRepository, wordRepository);
    }

    @Bean
    public GameService gameService() {
        return new GameApplicationService(gameRepository, dictionaryRepository, roundRepository);
    }

    @Bean
    public GameQueryService gameQueryService() {
        return new GameQueryService(gameRepository);
    }

    @Bean
    public RoundQueryService roundQueryService() {
        return new RoundQueryService(roundRepository);
    }

    @Bean
    public RoundService roundService() {
        return new RoundApplicationService(roundRepository, turnRepository);
    }

    @Bean
    public DictionaryQueryService dictionaryQueryService() {
        return new DictionaryQueryService(dictionaryRepository, wordRepository);
    }

    @Bean
    public TurnQueryService turnQueryService() {
        return new TurnQueryService(turnRepository, roundRepository);
    }
}
