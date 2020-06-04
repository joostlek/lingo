package dev.joostlek.lingo.infrastructure.persistency.memory;

import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryRepository;

import java.util.UUID;

public class InMemoryDictionaryRepository implements DictionaryRepository {

    public InMemoryDictionaryRepository() {
        super();
    }

    @Override
    public Dictionary dictionaryOfIdentity(DictionaryId dictionaryId) {
        return InMemoryObject.instance()
                .dictionaries()
                .get(dictionaryId);
    }

    @Override
    public DictionaryId nextIdentity() {
        UUID uuid = UUID.randomUUID();
        DictionaryId dictionaryId = new DictionaryId(uuid.toString());
        if (InMemoryObject.instance()
                .dictionaries()
                .containsKey(dictionaryId)) {
            return nextIdentity();
        }
        return dictionaryId;
    }

    @Override
    public void save(Dictionary dictionary) {
        InMemoryObject.instance()
                .dictionaries()
                .put(dictionary.dictionaryId(), dictionary);
    }

}
