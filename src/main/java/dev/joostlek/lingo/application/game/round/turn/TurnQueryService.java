package dev.joostlek.lingo.application.game.round.turn;

import dev.joostlek.lingo.domain.model.game.round.*;

import java.util.Collection;

public class TurnQueryService {
    private final TurnRepository turnRepository;

    private final RoundRepository roundRepository;

    public TurnQueryService(TurnRepository turnRepository, RoundRepository roundRepository) {
        this.turnRepository = turnRepository;
        this.roundRepository = roundRepository;
    }

    public Collection<Turn> getTurnsByRoundId(String aRoundId) {
        RoundId roundId = new RoundId(aRoundId);

        Round round = roundRepository.roundOfIdentity(roundId)
                .orElseThrow(() -> new IllegalStateException("Round not found with id " + aRoundId));

        return round.turns();
    }

    public Turn getTurnByTurnId(String aTurnId) {
        TurnId turnId = new TurnId(aTurnId);

        return turnRepository.turnOfIdentity(turnId)
                .orElseThrow(() -> new IllegalStateException("Turn not found with id " + aTurnId + "."));
    }
}
