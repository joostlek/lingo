package dev.joostlek.lingo.infrastructure.persistency.jpa;

import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryRepository;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.DictionaryEntity;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.WordEntity;
import dev.joostlek.lingo.infrastructure.persistency.jpa.repositories.DictionaryJpaRepository;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.stream.Collectors;

public class DatabaseDictionaryRepository implements DictionaryRepository {

    private final DictionaryJpaRepository dictionaryJpaRepository;

    private final ModelMapper modelMapper;

    public DatabaseDictionaryRepository(DictionaryJpaRepository dictionaryJpaRepository, ModelMapper modelMapper) {
        this.dictionaryJpaRepository = dictionaryJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Dictionary dictionaryOfIdentity(DictionaryId aDictionaryId) {
        Long dictionaryId = Long.parseLong(aDictionaryId.id());
        DictionaryEntity dictionaryEntity = dictionaryJpaRepository.findById(dictionaryId)
                .orElseThrow(() -> new IllegalStateException("Could not find dictionary"));
        return modelMapper.map(dictionaryEntity, Dictionary.class);
    }

    @Override
    public DictionaryId nextIdentity() {
        Long dictionaryId = dictionaryJpaRepository.getNextIdentity();
        return new DictionaryId(String.valueOf(dictionaryId));
    }

    @Override
    public void save(Dictionary aDictionary) {
        DictionaryEntity dictionary = modelMapper.map(aDictionary, DictionaryEntity.class);
        dictionary.getWords().forEach((WordEntity word) -> {
            word.setDictionary(dictionary);
        });
        this.dictionaryJpaRepository.save(dictionary);
    }

    @Override
    public Collection<Dictionary> getAllDictionaries() {
        return this.dictionaryJpaRepository.findAll()
                .stream()
                .parallel()
                .map((dictionary) -> modelMapper.map(dictionary, Dictionary.class))
                .collect(Collectors.toSet());
    }
}
