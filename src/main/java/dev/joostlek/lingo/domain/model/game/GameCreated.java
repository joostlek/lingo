package dev.joostlek.lingo.domain.model.game;

import dev.joostlek.lingo.domain.DomainEvent;
import dev.joostlek.lingo.domain.model.WordLength;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;

import java.util.Date;

public class GameCreated implements DomainEvent {

    private final GameId gameId;

    private final Date createdAt;

    private final WordLength wordLength;

    private final DictionaryId chosenDictionaryId;

    private final int eventVersion;

    public GameCreated(GameId gameId, Date createdAt, WordLength wordLength, DictionaryId chosenDictionaryId) {
        super();
        this.gameId = gameId;
        this.createdAt = createdAt;
        this.wordLength = wordLength;
        this.chosenDictionaryId = chosenDictionaryId;
        this.eventVersion = 0;
    }

    public GameId gameId() {
        return gameId;
    }

    public Date createdAt() {
        return createdAt;
    }

    public WordLength wordLength() {
        return wordLength;
    }

    public DictionaryId chosenDictionaryId() {
        return chosenDictionaryId;
    }

    @Override
    public int eventVersion() {
        return eventVersion;
    }

    @Override
    public Date occurredOn() {
        return createdAt;
    }
}
