package dev.joostlek.lingo.domain.model.dictionary.word;

import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;

import java.util.Collection;
import java.util.Optional;

public interface WordRepository {
    WordId nextIdentity();

    Optional<Word> wordOfIdentity(WordId wordId);

    Collection<Word> allDictionaryWords(DictionaryId dictionaryId);
}
