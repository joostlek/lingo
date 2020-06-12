package dev.joostlek.lingo.domain.model.game.round;

import java.util.Optional;

public interface TurnRepository {
    TurnId nextIdentity();

    Optional<Turn> turnOfIdentity(TurnId turnId);
}
