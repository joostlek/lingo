package dev.joostlek.lingo.application.dictionary;

import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryRepository;
import dev.joostlek.lingo.domain.model.dictionary.word.WordId;
import dev.joostlek.lingo.domain.model.dictionary.word.WordRepository;

public class DictionaryApplicationService implements DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    private final WordRepository wordRepository;

    public DictionaryApplicationService(DictionaryRepository dictionaryRepository, WordRepository wordRepository) {
        super();

        this.dictionaryRepository = dictionaryRepository;
        this.wordRepository = wordRepository;
    }

    @Override
    public String addWord(String aDictionaryId, String word) {
        DictionaryId dictionaryId = new DictionaryId(aDictionaryId);

        Dictionary dictionary = this.dictionaryRepository()
                .dictionaryOfIdentity(dictionaryId);

        if (dictionary == null) {
            throw new IllegalStateException("Dictionary voor " + dictionaryId.id() + " niet gevonden.");
        }

        WordId wordId = this.wordRepository().nextIdentity();

        dictionary.addWordToDictionary(wordId, word);

        this.dictionaryRepository().save(dictionary);

        return wordId.id();
    }

    public String createDictionary(String language) {
        DictionaryId dictionaryId = this.dictionaryRepository().nextIdentity();

        Dictionary dictionary = new Dictionary(dictionaryId, language);

        this.dictionaryRepository().save(dictionary);

        return dictionaryId.id();
    }

    public DictionaryRepository dictionaryRepository() {
        return dictionaryRepository;
    }

    public WordRepository wordRepository() {
        return wordRepository;
    }
}
