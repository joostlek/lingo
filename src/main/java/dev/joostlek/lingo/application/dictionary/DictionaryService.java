package dev.joostlek.lingo.application.dictionary;

import dev.joostlek.lingo.application.dictionary.commands.CreateDictionaryCommand;
import dev.joostlek.lingo.application.dictionary.commands.NewWordCommand;

public interface DictionaryService {
    void addWord(NewWordCommand newWordCommand);

    String createDictionary(CreateDictionaryCommand createDictionaryCommand);
}
