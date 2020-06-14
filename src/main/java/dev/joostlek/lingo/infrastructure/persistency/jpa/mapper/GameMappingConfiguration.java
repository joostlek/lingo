package dev.joostlek.lingo.infrastructure.persistency.jpa.mapper;

import dev.joostlek.lingo.domain.model.game.Game;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.GameEntity;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.ModelMapper;

public class GameMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Game.class, GameEntity.class)
                .addMapping(Game::gameId, GameEntity::setId)
                .addMapping(Game::createdAt, GameEntity::setCreatedAt)
                .addMapping(Game::wordLength, GameEntity::setWordLength)
                .addMapping(Game::rounds, GameEntity::setRounds);

        modelMapper.createTypeMap(GameEntity.class, Game.class)
                .addMapping(GameEntity::getId, Game::setGameId)
                .addMapping(GameEntity::getCreatedAt, Game::setCreatedAt)
                .addMapping(GameEntity::getWordLength, Game::setWordLength)
                .addMapping(GameEntity::getRounds, Game::setRounds)
                .addMapping(source -> source.getDictionary().getId(), Game::setChosenDictionaryId);
    }
}
