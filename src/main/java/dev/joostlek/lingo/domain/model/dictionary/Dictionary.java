package dev.joostlek.lingo.domain.model.dictionary;

import dev.joostlek.lingo.domain.DomainEventPublisher;
import dev.joostlek.lingo.domain.Entity;
import dev.joostlek.lingo.domain.model.WordLength;
import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.domain.model.dictionary.word.WordId;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Dictionary extends Entity {

    private final Random random = new Random();
    private DictionaryId dictionaryId;
    private Set<Word> words;
    private String language;

    public Dictionary(DictionaryId dictionaryId, String language) {
        this();

        this.setDictionaryId(dictionaryId);
        this.setLanguage(language);

        DomainEventPublisher
                .instance()
                .publish(new DictionaryCreated(
                        dictionaryId(),
                        language()));
    }

    private Dictionary() {
        super();

        this.setWords(new HashSet<>(0));
    }

    // Een draak van een algoritme, maar dit kan altijd nog worden aangepast :)
    public Word getRandomWord(WordLength wordLength) {
        int size = words.size();
        int item = random.nextInt(size);
        int i = 0;
        boolean pickNextWord = false;
        for (Word word : words) {
            if (i == item || pickNextWord) {
                if (word.word().length() == wordLength.getLength()) {
                    return word;
                } else {
                    pickNextWord = true;
                }
            }
            i++;
        }
        return getRandomWord(wordLength);
    }

    public void addWordToDictionary(WordId wordId, String newWord) {
        Word word = new Word(wordId, this.dictionaryId, newWord);
        addWord(word);
    }

    private void addWord(Word newWord) {
        for (Word word : this.words) {
            if (word.equals(newWord)) {
                throw new IllegalStateException("Woord is al toegevoegd aan " + language());
            }
        }
        this.words.add(newWord);
    }

    public DictionaryId dictionaryId() {
        return dictionaryId;
    }

    public Set<Word> allWords() {
        return words;
    }

    public String language() {
        return language;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    public void setLanguage(String language) {
        this.assertArgumentNotNull(language, "De taal moet opgegeven worden.");
        this.assertArgumentIsValidByRegex(language, "^[A-Z].*$", "De taal moet beginnen met een hoofdletter en mag alleen uit letters bestaan.");

        this.language = language;
    }

    public void setDictionaryId(DictionaryId dictionaryId) {
        this.assertArgumentNotNull(dictionaryId, "De dictionaryId moet meegegeven worden.");

        this.dictionaryId = dictionaryId;
    }
}
