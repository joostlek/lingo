package dev.joostlek.lingo.infrastructure.persistency.jpa;

import dev.joostlek.lingo.domain.model.game.Game;
import dev.joostlek.lingo.domain.model.game.GameId;
import dev.joostlek.lingo.domain.model.game.GameRepository;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.DictionaryEntity;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.GameEntity;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.ResultId;
import dev.joostlek.lingo.infrastructure.persistency.jpa.repositories.GameJpaRepository;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabaseGameRepository implements GameRepository {

    private final GameJpaRepository gameJpaRepository;

    private final ModelMapper modelMapper;

    private final EntityManager entityManager;

    public DatabaseGameRepository(GameJpaRepository gameJpaRepository, ModelMapper modelMapper, EntityManager entityManager) {
        this.gameJpaRepository = gameJpaRepository;
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Game> gameOfIdentity(GameId aGameId) {
        Long gameId = Long.parseLong(aGameId.id());
        GameEntity gameEntity = gameJpaRepository.findById(gameId)
                .orElse(null);
        return Optional.ofNullable(modelMapper.map(gameEntity, Game.class));
    }

    @Override
    public GameId nextIdentity() {
        Long gameId = gameJpaRepository.getNextIdentity();
        return new GameId(String.valueOf(gameId));
    }

    @Override
    public void save(Game aGame) {
        GameEntity game = modelMapper.map(aGame, GameEntity.class);
        game.setDictionary(entityManager.getReference(DictionaryEntity.class, Long.parseLong(aGame.chosenDictionaryId().id())));
        game.getRounds().forEach(roundEntity -> {
            roundEntity.setGame(game);
            roundEntity.getTurns().forEach(turnEntity -> {
                turnEntity.setRound(roundEntity);
                turnEntity.getResults().forEach(resultEntity -> resultEntity.setResultId(new ResultId(resultEntity.getResultId().getPosition(), turnEntity)));
            });
        });
        this.gameJpaRepository.save(game);
    }

    @Override
    public Collection<Game> getAllGames() {
        return this.gameJpaRepository.findAll()
                .stream()
                .parallel()
                .map((game) -> modelMapper.map(game, Game.class))
                .collect(Collectors.toSet());
    }
}
