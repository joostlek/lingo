package dev.joostlek.lingo.infrastructure.persistency.jpa;

import dev.joostlek.lingo.domain.model.game.GameId;
import dev.joostlek.lingo.domain.model.game.round.Round;
import dev.joostlek.lingo.domain.model.game.round.RoundId;
import dev.joostlek.lingo.domain.model.game.round.RoundRepository;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.GameEntity;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.ResultId;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.RoundEntity;
import dev.joostlek.lingo.infrastructure.persistency.jpa.repositories.RoundJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class DatabaseRoundRepository implements RoundRepository {

    private final RoundJpaRepository roundJpaRepository;
    private final ModelMapper modelMapper;

    private final EntityManager entityManager;

    public DatabaseRoundRepository(RoundJpaRepository roundJpaRepository, ModelMapper modelMapper, EntityManager entityManager) {
        this.roundJpaRepository = roundJpaRepository;
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
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
    public void save(Round aRound) {
        RoundEntity round = modelMapper.map(aRound, RoundEntity.class);
        round.setGame(entityManager.getReference(GameEntity.class, Long.parseLong(aRound.gameId().id())));
        round.getTurns().forEach(turnEntity -> {
            turnEntity.setRound(round);
            turnEntity.getResults().forEach(resultEntity -> resultEntity.setResultId(new ResultId(resultEntity.getResultId().getPosition(), turnEntity)));
        });
        this.roundJpaRepository.save(round);
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
