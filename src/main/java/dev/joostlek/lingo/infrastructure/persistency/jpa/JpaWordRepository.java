package dev.joostlek.lingo.infrastructure.persistency.jpa;

import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.domain.model.dictionary.word.WordId;
import dev.joostlek.lingo.domain.model.dictionary.word.WordRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class JpaWordRepository implements WordRepository {
    @Override
    public WordId nextIdentity() {
        return null;
    }

    @Override
    public Optional<Word> wordOfIdentity(WordId wordId) {
        return Optional.empty();
    }

    @Override
    public Collection<Word> allDictionaryWords(DictionaryId dictionaryId) {
        return new HashSet<>();
    }
}
