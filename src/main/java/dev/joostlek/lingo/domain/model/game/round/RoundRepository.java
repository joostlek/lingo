package dev.joostlek.lingo.domain.model.game.round;

import dev.joostlek.lingo.domain.model.game.GameId;

import java.util.Optional;
import java.util.Set;

public interface RoundRepository {
    RoundId nextIdentity();

    Optional<Round> roundOfIdentity(RoundId roundId);

    void save(Round round);

    Set<Round> getRoundsByGameId(GameId gameId);
}
