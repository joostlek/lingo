package dev.joostlek.lingo.domain.model.dictionary;

public interface DictionaryRepository {
    Dictionary dictionaryOfIdentity(DictionaryId dictionaryId);

    DictionaryId nextIdentity();

    void save(Dictionary dictionary);
}
