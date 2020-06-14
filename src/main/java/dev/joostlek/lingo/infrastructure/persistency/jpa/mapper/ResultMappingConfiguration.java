package dev.joostlek.lingo.infrastructure.persistency.jpa.mapper;

import dev.joostlek.lingo.domain.model.game.round.Result;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.ResultEntity;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.ModelMapper;

public class ResultMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Result.class, ResultEntity.class)
                .addMapping(Result::character, ResultEntity::setCharacter)
                .addMapping(Result::feedback, ResultEntity::setFeedback)
                .addMapping(Result::position, ResultEntity::setPosition);
    }
}
