package dev.joostlek.lingo.application.dictionary;

import dev.joostlek.lingo.application.dictionary.commands.CreateDictionaryCommand;
import dev.joostlek.lingo.application.dictionary.commands.NewWordCommand;
import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryRepository;
import dev.joostlek.lingo.domain.model.dictionary.word.WordRepository;

public class DictionaryApplicationService implements DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    private final WordRepository wordRepository;

    public DictionaryApplicationService(DictionaryRepository dictionaryRepository, WordRepository wordRepository) {
        super();

        this.dictionaryRepository = dictionaryRepository;
        this.wordRepository = wordRepository;
    }

    public void addWord(NewWordCommand newWordCommand) {
        this.addWord(newWordCommand.getDictionaryId(), newWordCommand.getWord());
    }

    public String createDictionary(CreateDictionaryCommand aCommand) {
        return this.createDictionary(aCommand.getLanguage());
    }

    public DictionaryRepository dictionaryRepository() {
        return dictionaryRepository;
    }

    public WordRepository wordRepository() {
        return wordRepository;
    }

    private void addWord(
            String aDictionaryId,
            String newWord) {

        DictionaryId dictionaryId = new DictionaryId(aDictionaryId);

        Dictionary dictionary = this.dictionaryRepository()
                .dictionaryOfIdentity(dictionaryId);

        if (dictionary == null) {
            throw new IllegalStateException("Dictionary voor " + dictionaryId.id() + " niet gevonden.");
        }

        dictionary.addWordToDictionary(newWord);

        this.dictionaryRepository().save(dictionary);
    }

    private String createDictionary(String language) {
        DictionaryId dictionaryId = this.dictionaryRepository().nextIdentity();

        Dictionary dictionary = new Dictionary(dictionaryId, language);

        this.dictionaryRepository().save(dictionary);

        return dictionaryId.id();
    }
}
