package dev.joostlek.lingo.infrastructure.persistency.memory;

import dev.joostlek.lingo.domain.model.game.round.Turn;
import dev.joostlek.lingo.domain.model.game.round.TurnId;
import dev.joostlek.lingo.domain.model.game.round.TurnRepository;

import java.util.Optional;
import java.util.UUID;

public class InMemoryTurnRepository implements TurnRepository {

    @Override
    public TurnId nextIdentity() {
        UUID uuid = UUID.randomUUID();
        TurnId turnId = new TurnId(uuid.toString());
        if (InMemoryObject.instance()
                .turns()
                .containsKey(turnId)) {
            return nextIdentity();
        }
        return turnId;
    }

    @Override
    public Optional<Turn> turnOfIdentity(TurnId turnId) {
        return Optional.ofNullable(
                InMemoryObject.instance()
                        .turns()
                        .get(turnId)
        );
    }
}
