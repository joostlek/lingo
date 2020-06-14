package dev.joostlek.lingo.infrastructure.persistency.jpa.mapper;

import dev.joostlek.lingo.domain.model.game.round.Round;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.RoundEntity;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.ModelMapper;

public class RoundMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Round.class, RoundEntity.class)
                .addMapping(Round::startedAt, RoundEntity::setStartedAt)
                .addMapping(Round::endedAt, RoundEntity::setEndedAt)
                .addMapping(Round::count, RoundEntity::setCount)
                .addMapping(Round::guessed, RoundEntity::setGuessed)
                .addMapping(Round::answer, RoundEntity::setAnswer)
                .addMapping(Round::turns, RoundEntity::setTurns)
                .addMapping(Round::roundId, RoundEntity::setId);

        modelMapper.createTypeMap(RoundEntity.class, Round.class)
                .addMapping(RoundEntity::getStartedAt, Round::setStartedAt)
                .addMapping(RoundEntity::getEndedAt, Round::setEndedAt)
                .addMapping(RoundEntity::getCount, Round::setCount)
                .addMapping(RoundEntity::isGuessed, Round::setGuessed)
                .addMapping(RoundEntity::getAnswer, Round::setAnswer)
                .addMapping(RoundEntity::getTurns, Round::setTurns)
                .addMapping(RoundEntity::getId, Round::setRoundId);
    }
}
