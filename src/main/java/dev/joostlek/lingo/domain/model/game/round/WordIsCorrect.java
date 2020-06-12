package dev.joostlek.lingo.domain.model.game.round;

import dev.joostlek.lingo.domain.DomainEvent;

import java.util.Date;

public class WordIsCorrect implements DomainEvent {
    private final Date occurredOn;
    private final int eventVersion;
    private final RoundId roundId;
    private final TurnId turnId;

    public WordIsCorrect(RoundId roundId, TurnId turnId) {
        this.roundId = roundId;
        this.turnId = turnId;
        this.eventVersion = 0;
        this.occurredOn = new Date();
    }

    public RoundId roundId() {
        return roundId;
    }

    public TurnId turnId() {
        return turnId;
    }

    @Override
    public int eventVersion() {
        return this.eventVersion;
    }

    @Override
    public Date occurredOn() {
        return this.occurredOn;
    }
}
