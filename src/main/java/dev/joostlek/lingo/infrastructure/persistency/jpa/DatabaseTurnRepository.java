package dev.joostlek.lingo.infrastructure.persistency.jpa;

import dev.joostlek.lingo.domain.model.game.round.Turn;
import dev.joostlek.lingo.domain.model.game.round.TurnId;
import dev.joostlek.lingo.domain.model.game.round.TurnRepository;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.TurnEntity;
import dev.joostlek.lingo.infrastructure.persistency.jpa.repositories.TurnJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DatabaseTurnRepository implements TurnRepository {

    private final TurnJpaRepository turnJpaRepository;

    private final ModelMapper modelMapper;

    public DatabaseTurnRepository(TurnJpaRepository turnJpaRepository, ModelMapper modelMapper) {
        this.turnJpaRepository = turnJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnId nextIdentity() {
        Long turnId = turnJpaRepository.getNextIdentity();
        return new TurnId(String.valueOf(turnId));
    }

    @Override
    public Optional<Turn> turnOfIdentity(TurnId aTurnId) {
        Long turnId = Long.valueOf(aTurnId.id());
        TurnEntity turnEntity = turnJpaRepository.findById(turnId)
                .orElse(null);
        return Optional.ofNullable(modelMapper.map(turnEntity, Turn.class));
    }
}
