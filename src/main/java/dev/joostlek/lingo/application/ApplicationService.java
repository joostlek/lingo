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
import dev.joostlek.lingo.domain.model.game.round.RoundRepository;
import dev.joostlek.lingo.domain.model.game.round.TurnRepository;
import dev.joostlek.lingo.infrastructure.persistency.memory.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final InMemoryDictionaryRepository inMemoryDictionaryRepository;

    private final InMemoryWordRepository inMemoryWordRepository;

    private final InMemoryGameRepository inMemoryGameRepository;

    private final RoundRepository roundRepository;

    private final TurnRepository turnRepository;

    public ApplicationService() {
        this.inMemoryDictionaryRepository = new InMemoryDictionaryRepository();
        this.inMemoryWordRepository = new InMemoryWordRepository();
        this.inMemoryGameRepository = new InMemoryGameRepository();
        this.roundRepository = new InMemoryRoundRepository();
        this.turnRepository = new InMemoryTurnRepository();
    }

    @Bean
    public DictionaryService dictionaryService() {
        return new DictionaryApplicationService(inMemoryDictionaryRepository, inMemoryWordRepository);
    }

    @Bean
    public GameService gameService() {
        return new GameApplicationService(inMemoryGameRepository, inMemoryDictionaryRepository, roundRepository);
    }

    @Bean
    public GameQueryService gameQueryService() {
        return new GameQueryService(inMemoryGameRepository);
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
        return new DictionaryQueryService(inMemoryDictionaryRepository, inMemoryWordRepository);
    }

    @Bean
    public TurnQueryService turnQueryService() {
        return new TurnQueryService(turnRepository, roundRepository);
    }
}
