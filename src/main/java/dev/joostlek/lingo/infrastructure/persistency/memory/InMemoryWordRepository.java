package dev.joostlek.lingo.infrastructure.persistency.memory;

import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.domain.model.dictionary.word.WordRepository;

import java.util.Collection;

public class InMemoryWordRepository implements WordRepository {
    @Override
    public Collection<Word> allDictionaryWords(DictionaryId dictionaryId) {
        return InMemoryObject.instance()
                .dictionaries()
                .get(dictionaryId)
                .allWords();
    }
}
