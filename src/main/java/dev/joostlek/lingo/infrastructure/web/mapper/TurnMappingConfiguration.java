package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.game.round.Turn;
import dev.joostlek.lingo.infrastructure.web.dto.TurnDto;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.ModelMapper;

public class TurnMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Turn.class, TurnDto.class)
                .addMapping(Turn::turnId, TurnDto::setTurnId)
                .addMapping(Turn::roundId, TurnDto::setRoundId)
                .addMapping(Turn::count, TurnDto::setCount)
                .addMapping(Turn::endedAt, TurnDto::setEndedAt)
                .addMapping(Turn::startedAt, TurnDto::setStartedAt)
                .addMapping(Turn::results, TurnDto::setResults);
    }
}
