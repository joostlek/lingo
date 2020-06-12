package dev.joostlek.lingo.application.dictionary;

import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryRepository;
import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.domain.model.dictionary.word.WordId;
import dev.joostlek.lingo.domain.model.dictionary.word.WordRepository;

import java.util.Collection;

public class DictionaryQueryService {
    private final DictionaryRepository dictionaryRepository;

    private final WordRepository wordRepository;

    public DictionaryQueryService(DictionaryRepository dictionaryRepository, WordRepository wordRepository) {
        this.dictionaryRepository = dictionaryRepository;
        this.wordRepository = wordRepository;
    }

    public Word getWordById(String aWordId) {
        WordId wordId = new WordId(aWordId);

        return this.wordRepository.wordOfIdentity(wordId)
                .orElseThrow(() -> new IllegalStateException("Could not find word with id " + aWordId));
    }

    public Dictionary getDictionaryById(String aDictionaryId) {
        DictionaryId dictionaryId = new DictionaryId(aDictionaryId);

        return this.dictionaryRepository.dictionaryOfIdentity(dictionaryId);
    }

    public Collection<Word> getWordsByDictionaryId(String aDictionaryId) {
        DictionaryId dictionaryId = new DictionaryId(aDictionaryId);

        Dictionary dictionary = dictionaryRepository.dictionaryOfIdentity(dictionaryId);

        return dictionary.allWords();
    }

    public Collection<Dictionary> getDictionaries() {
        return dictionaryRepository.getAllDictionaries();
    }
}
