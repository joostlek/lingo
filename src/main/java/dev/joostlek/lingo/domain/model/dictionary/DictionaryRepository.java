package dev.joostlek.lingo.domain.model.dictionary;

import java.util.Collection;

public interface DictionaryRepository {
    Dictionary dictionaryOfIdentity(DictionaryId dictionaryId);

    DictionaryId nextIdentity();

    void save(Dictionary dictionary);

    Collection<Dictionary> getAllDictionaries();
}
