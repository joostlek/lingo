package dev.joostlek.lingo.infrastructure.persistency.jpa;

import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.domain.model.dictionary.word.WordRepository;

import java.util.Collection;
import java.util.HashSet;

public class JpaWordRepository implements WordRepository {
    @Override
    public Collection<Word> allDictionaryWords(DictionaryId dictionaryId) {
        return new HashSet<>();
    }
}
