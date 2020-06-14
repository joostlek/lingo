package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.game.Game;
import dev.joostlek.lingo.infrastructure.web.dto.GameDto;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.ModelMapper;

public class GameMapperConfiguration implements MapperConfiguration {

    @Override
    public void execute(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Game.class, GameDto.class)
                .addMapping(Game::gameId, GameDto::setGameId)
                .addMapping(Game::createdAt, GameDto::setCreatedAt)
                .addMapping(Game::wordLength, GameDto::setWordLength)
                .addMapping(Game::chosenDictionaryId, GameDto::setChosenDictionaryId);
    }
}
