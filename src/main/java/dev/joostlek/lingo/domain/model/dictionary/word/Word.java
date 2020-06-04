package dev.joostlek.lingo.domain.model.dictionary.word;

import dev.joostlek.lingo.domain.DomainEventPublisher;
import dev.joostlek.lingo.domain.Entity;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;

import java.util.Objects;

public class Word extends Entity {

    private DictionaryId dictionaryId;
    private String word;

    public Word(DictionaryId dictionaryId, String word) {
        this();

        this.setDictionaryId(dictionaryId);
        this.setWord(word);

        DomainEventPublisher
                .instance()
                .publish(new WordCreated(
                        this.dictionaryId(),
                        this.word()));
    }

    private Word() {
        super();
    }

    public DictionaryId dictionaryId() {
        return dictionaryId;
    }

    public String word() {
        return word;
    }

    public void setDictionaryId(DictionaryId dictionaryId) {
        assertArgumentNotNull(dictionaryId, "De dictionaryId moet meegegeven worden.");

        this.dictionaryId = dictionaryId;
    }

    public void setWord(String word) {
        assertArgumentLength(word, 5, 7, "Woord mag niet korter zijn dan 5 en niet langer zijn dan 7 letters.");

        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(dictionaryId, word1.dictionaryId) &&
                Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dictionaryId, word);
    }
}
