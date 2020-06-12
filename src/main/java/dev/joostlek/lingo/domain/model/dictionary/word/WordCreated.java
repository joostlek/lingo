package dev.joostlek.lingo.domain.model.dictionary.word;

import dev.joostlek.lingo.domain.DomainEvent;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;

import java.util.Date;

public class WordCreated implements DomainEvent {

    private DictionaryId dictionaryId;

    private String word;

    private int eventVersion;

    private Date occuredOn;

    public WordCreated(DictionaryId dictionaryId, String word) {
        super();
        this.dictionaryId = dictionaryId;
        this.word = word;
        this.occuredOn = new Date();
        this.eventVersion = 1;
    }

    public DictionaryId dictionaryId() {
        return dictionaryId;
    }

    public String word() {
        return word;
    }

    public Date occuredOn() {
        return occuredOn;
    }

    @Override
    public int eventVersion() {
        return this.eventVersion;
    }

    @Override
    public Date occurredOn() {
        return this.occuredOn;
    }
}
