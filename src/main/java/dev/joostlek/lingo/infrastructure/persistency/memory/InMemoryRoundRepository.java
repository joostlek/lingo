package dev.joostlek.lingo.infrastructure.persistency.memory;

import dev.joostlek.lingo.domain.model.game.GameId;
import dev.joostlek.lingo.domain.model.game.round.Round;
import dev.joostlek.lingo.domain.model.game.round.RoundId;
import dev.joostlek.lingo.domain.model.game.round.RoundRepository;
import dev.joostlek.lingo.domain.model.game.round.Turn;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class InMemoryRoundRepository implements RoundRepository {
    @Override
    public RoundId nextIdentity() {
        UUID uuid = UUID.randomUUID();
        RoundId roundId = new RoundId(uuid.toString());
        if (InMemoryObject.instance()
                .rounds()
                .containsKey(roundId)) {
            return nextIdentity();
        }
        return roundId;
    }

    @Override
    public Optional<Round> roundOfIdentity(RoundId roundId) {
        return Optional.ofNullable(
                InMemoryObject
                        .instance()
                        .rounds()
                        .get(roundId)
        );
    }

    @Override
    public void save(Round round) {
        InMemoryObject.instance()
                .rounds()
                .put(round.roundId(), round);
        for (Turn turn : round.turns()) {
            InMemoryObject
                    .instance()
                    .turns()
                    .put(turn.turnId(), turn);
        }
    }

    @Override
    public Set<Round> getRoundsByGameId(GameId gameId) {
        return InMemoryObject.instance()
                .games()
                .get(gameId)
                .rounds();
    }
}
