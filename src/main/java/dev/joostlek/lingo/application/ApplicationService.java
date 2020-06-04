package dev.joostlek.lingo.application;

import dev.joostlek.lingo.application.dictionary.DictionaryApplicationService;
import dev.joostlek.lingo.application.dictionary.DictionaryService;
import dev.joostlek.lingo.infrastructure.persistency.memory.InMemoryDictionaryRepository;
import dev.joostlek.lingo.infrastructure.persistency.memory.InMemoryWordRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final InMemoryDictionaryRepository inMemoryDictionaryRepository;

    private final InMemoryWordRepository inMemoryWordRepository;

    public ApplicationService() {
        this.inMemoryDictionaryRepository = new InMemoryDictionaryRepository();
        this.inMemoryWordRepository = new InMemoryWordRepository();
    }

    @Bean
    public DictionaryService dictionaryService() {
        return new DictionaryApplicationService(inMemoryDictionaryRepository, inMemoryWordRepository);
    }
}
