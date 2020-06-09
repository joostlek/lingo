package dev.joostlek.lingo.application;

import dev.joostlek.lingo.application.dictionary.DictionaryApplicationService;
import dev.joostlek.lingo.application.dictionary.DictionaryService;
import dev.joostlek.lingo.application.game.GameApplicationService;
import dev.joostlek.lingo.application.game.GameQueryService;
import dev.joostlek.lingo.application.game.GameService;
import dev.joostlek.lingo.infrastructure.persistency.memory.InMemoryDictionaryRepository;
import dev.joostlek.lingo.infrastructure.persistency.memory.InMemoryGameRepository;
import dev.joostlek.lingo.infrastructure.persistency.memory.InMemoryWordRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final InMemoryDictionaryRepository inMemoryDictionaryRepository;

    private final InMemoryWordRepository inMemoryWordRepository;

    private final InMemoryGameRepository inMemoryGameRepository;

    public ApplicationService() {
        this.inMemoryDictionaryRepository = new InMemoryDictionaryRepository();
        this.inMemoryWordRepository = new InMemoryWordRepository();
        this.inMemoryGameRepository = new InMemoryGameRepository();
    }

    @Bean
    public DictionaryService dictionaryService() {
        return new DictionaryApplicationService(inMemoryDictionaryRepository, inMemoryWordRepository);
    }

    @Bean
    public GameService gameService() {
        return new GameApplicationService(inMemoryGameRepository, inMemoryDictionaryRepository);
    }

    @Bean
    public GameQueryService gameQueryService() {
        return new GameQueryService(inMemoryGameRepository);
    }
}
