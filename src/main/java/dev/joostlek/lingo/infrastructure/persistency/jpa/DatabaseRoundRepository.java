package dev.joostlek.lingo.infrastructure.persistency.jpa;

import dev.joostlek.lingo.domain.model.game.GameId;
import dev.joostlek.lingo.domain.model.game.round.Round;
import dev.joostlek.lingo.domain.model.game.round.RoundId;
import dev.joostlek.lingo.domain.model.game.round.RoundRepository;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.RoundEntity;
import dev.joostlek.lingo.infrastructure.persistency.jpa.repositories.RoundJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class DatabaseRoundRepository implements RoundRepository {

    private final RoundJpaRepository roundJpaRepository;
    private final ModelMapper modelMapper;

    public DatabaseRoundRepository(RoundJpaRepository roundJpaRepository, ModelMapper modelMapper) {
        this.roundJpaRepository = roundJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoundId nextIdentity() {
        Long roundId = roundJpaRepository.getNextIdentity();
        return new RoundId(String.valueOf(roundId));
    }

    @Override
    public Optional<Round> roundOfIdentity(RoundId aRoundId) {
        Long roundId = Long.valueOf(aRoundId.id());
        RoundEntity roundEntity = roundJpaRepository.findById(roundId)
                .orElse(null);
        return Optional.ofNullable(modelMapper.map(roundEntity, Round.class));
    }

    @Override
    public void save(Round round) {

    }

    @Override
    public Set<Round> getRoundsByGameId(GameId aGameId) {
        Long gameId = Long.valueOf(aGameId.id());
        Collection<RoundEntity> rounds = roundJpaRepository.findAllByGameId(gameId);
        return rounds.stream()
                .map(roundEntity -> modelMapper.map(roundEntity, Round.class))
                .collect(Collectors.toSet());
    }
}
