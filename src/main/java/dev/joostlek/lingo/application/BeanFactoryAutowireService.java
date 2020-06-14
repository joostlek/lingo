package dev.joostlek.lingo.application;

import dev.joostlek.lingo.domain.model.dictionary.DictionaryRepository;
import dev.joostlek.lingo.domain.model.dictionary.word.WordRepository;
import dev.joostlek.lingo.domain.model.game.GameRepository;
import dev.joostlek.lingo.domain.model.game.round.RoundRepository;
import dev.joostlek.lingo.domain.model.game.round.TurnRepository;
import dev.joostlek.lingo.infrastructure.persistency.jpa.*;
import dev.joostlek.lingo.infrastructure.persistency.jpa.repositories.*;
import dev.joostlek.lingo.infrastructure.persistency.memory.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;

@Service
public class BeanFactoryAutowireService {

    private final StorageType storageType;

    private final ApplicationContext applicationContext;

    public BeanFactoryAutowireService(ApplicationContext applicationContext) {
        if (System.getenv().containsKey("storage_type")) {
            storageType = StorageType.valueOf(System.getenv("storage_type"));
        } else {
            storageType = StorageType.IN_MEMORY;
        }
        this.applicationContext = applicationContext;
    }

    @Bean
    public DictionaryRepository dictionaryRepository() {
        switch (storageType) {
            case DATABASE:
                return new DatabaseDictionaryRepository((DictionaryJpaRepository) applicationContext.getBean("dictionaryJpaRepository"), (ModelMapper) applicationContext.getBean("modelMapper"));
            case IN_MEMORY:
            default:
                return new InMemoryDictionaryRepository();
        }
    }

    @Bean
    public WordRepository wordRepository() {
        switch (storageType) {
            case DATABASE:
                return new DatabaseWordRepository((ModelMapper) applicationContext.getBean("modelMapper"), (WordJpaRepository) applicationContext.getBean("wordJpaRepository"));
            case IN_MEMORY:
            default:
                return new InMemoryWordRepository();
        }
    }

    @Bean
    public GameRepository gameRepository() {
        switch (storageType) {
            case DATABASE:
                return new DatabaseGameRepository((GameJpaRepository) applicationContext.getBean("gameJpaRepository"), (ModelMapper) applicationContext.getBean("modelMapper"), ((EntityManagerFactory) applicationContext.getBean("entityManagerFactory")).createEntityManager());
            case IN_MEMORY:
            default:
                return new InMemoryGameRepository();
        }
    }

    @Bean
    public RoundRepository roundRepository() {
        switch (storageType) {
            case DATABASE:
                return new DatabaseRoundRepository((RoundJpaRepository) applicationContext.getBean("roundJpaRepository"), (ModelMapper) applicationContext.getBean("modelMapper"), ((EntityManagerFactory) applicationContext.getBean("entityManagerFactory")).createEntityManager());
            case IN_MEMORY:
            default:
                return new InMemoryRoundRepository();
        }
    }

    @Bean
    public TurnRepository turnRepository() {
        switch (storageType) {
            case DATABASE:
                return new DatabaseTurnRepository((TurnJpaRepository) applicationContext.getBean("turnJpaRepository"), (ModelMapper) applicationContext.getBean("modelMapper"));
            case IN_MEMORY:
            default:
                return new InMemoryTurnRepository();
        }
    }
}
