package dev.joostlek.lingo.domain.model.dictionary.word;

import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;

import java.util.Collection;

public interface WordRepository {

    Collection<Word> allDictionaryWords(DictionaryId dictionaryId);
}
