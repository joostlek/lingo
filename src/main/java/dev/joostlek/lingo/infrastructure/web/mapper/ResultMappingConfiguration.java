package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.game.round.Result;
import dev.joostlek.lingo.infrastructure.web.dto.ResultDto;
import org.modelmapper.ModelMapper;

public class ResultMappingConfiguration implements MapperConfiguration {

    @Override
    public void execute(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Result.class, ResultDto.class)
                .addMapping(Result::character, ResultDto::setCharacter)
                .addMapping(Result::feedback, ResultDto::setFeedback)
                .addMapping(Result::position, ResultDto::setPosition);
    }
}
