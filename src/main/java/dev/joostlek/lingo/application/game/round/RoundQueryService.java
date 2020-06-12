package dev.joostlek.lingo.application.game.round;

import dev.joostlek.lingo.domain.model.game.GameId;
import dev.joostlek.lingo.domain.model.game.round.Round;
import dev.joostlek.lingo.domain.model.game.round.RoundId;
import dev.joostlek.lingo.domain.model.game.round.RoundRepository;

import java.util.Set;

public class RoundQueryService {

    private final RoundRepository roundRepository;

    public RoundQueryService(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    public Set<Round> getRoundsByGameId(String aGameId) {
        GameId gameId = new GameId(aGameId);

        return roundRepository.getRoundsByGameId(gameId);
    }

    public Round getRoundById(String aRoundId) {
        RoundId roundId = new RoundId(aRoundId);

        return roundRepository.roundOfIdentity(roundId)
                .orElseThrow(() -> new IllegalStateException("Round not found with id " + aRoundId));
    }
}
