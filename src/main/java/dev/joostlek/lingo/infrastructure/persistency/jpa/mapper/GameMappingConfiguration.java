package dev.joostlek.lingo.infrastructure.persistency.jpa.mapper;

import dev.joostlek.lingo.domain.model.game.Game;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.GameEntity;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.ModelMapper;

public class GameMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Game.class, GameEntity.class)
                .addMapping(Game::createdAt, GameEntity::setCreatedAt)
                .addMapping(Game::wordLength, GameEntity::setWordLength)
                .addMapping(Game::rounds, GameEntity::setRounds);
    }
}
