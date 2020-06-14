package dev.joostlek.lingo.infrastructure.persistency.jpa.mapper;

import dev.joostlek.lingo.domain.model.game.round.Turn;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.TurnEntity;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.ModelMapper;

public class TurnMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Turn.class, TurnEntity.class)
                .addMapping(Turn::startedAt, TurnEntity::setStartedAt)
                .addMapping(Turn::endedAt, TurnEntity::setEndedAt)
                .addMapping(Turn::count, TurnEntity::setCount)
                .addMapping(Turn::results, TurnEntity::setResults);
    }
}
