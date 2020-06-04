package dev.joostlek.lingo.domain.model.dictionary;

import dev.joostlek.lingo.domain.DomainEvent;

import java.util.Date;

public class DictionaryCreated implements DomainEvent {

    private DictionaryId dictionaryId;

    private String language;

    private int eventVersion;

    private Date occuredOn;

    public DictionaryCreated(DictionaryId dictionaryId, String language) {
        super();
        this.dictionaryId = dictionaryId;
        this.language = language;
        this.occuredOn = new Date();
        this.eventVersion = 1;
    }

    public DictionaryId dictionaryId() {
        return dictionaryId;
    }

    public String language() {
        return language;
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
