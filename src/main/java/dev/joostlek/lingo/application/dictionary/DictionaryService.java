package dev.joostlek.lingo.application.dictionary;

public interface DictionaryService {
    String addWord(String aDictionaryId, String word);

    String createDictionary(String language);
}
