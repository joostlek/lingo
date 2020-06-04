package dev.joostlek.lingo.infrastructure.persistency.jpa;

import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryRepository;

public class JpaDictionaryRepository implements DictionaryRepository {
    @Override
    public Dictionary dictionaryOfIdentity(DictionaryId dictionaryId) {
        return null;
    }

    @Override
    public DictionaryId nextIdentity() {
        return null;
    }

    @Override
    public void save(Dictionary dictionary) {

    }
}
