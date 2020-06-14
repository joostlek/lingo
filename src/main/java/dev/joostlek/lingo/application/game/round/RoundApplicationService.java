package dev.joostlek.lingo.application.game.round;

import dev.joostlek.lingo.application.game.round.listeners.WordIsCorrectListener;
import dev.joostlek.lingo.domain.DomainEventPublisher;
import dev.joostlek.lingo.domain.model.game.round.*;

public class RoundApplicationService implements RoundService {

    private final RoundRepository roundRepository;

    private final TurnRepository turnRepository;

    public RoundApplicationService(RoundRepository roundRepository, TurnRepository turnRepository) {
        this.roundRepository = roundRepository;
        this.turnRepository = turnRepository;

        DomainEventPublisher
                .instance()
                .subscribe(new WordIsCorrectListener(this));
    }

    @Override
    public void endRound(String aRoundId) {
        RoundId roundId = new RoundId(aRoundId);

        Round round = this.roundRepository().roundOfIdentity(roundId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find round with id " + aRoundId));

        round.endRound(true);

        this.roundRepository().save(round);
    }

    @Override
    public String performTurn(String aRoundId, String guess) {
        RoundId roundId = new RoundId(aRoundId);

        Round round = this.roundRepository().roundOfIdentity(roundId)
                .orElseThrow(() -> new IllegalStateException("Round not found with id " + aRoundId + "."));

        TurnId turnId = this.turnRepository().nextIdentity();

        round.performTurn(turnId, guess);

        Round aRound = this.roundRepository().roundOfIdentity(roundId)
                .orElseThrow(() -> new IllegalStateException("Round not found with id " + aRoundId + "."));

        if (aRound.guessed()) {
            round.setGuessed(true);
            round.setEndedAt(aRound.endedAt());
        }

        this.roundRepository().save(round);

        return turnId.id();
    }


    public RoundRepository roundRepository() {
        return roundRepository;
    }

    public TurnRepository turnRepository() {
        return turnRepository;
    }
}
