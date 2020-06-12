package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.game.round.Round;
import dev.joostlek.lingo.infrastructure.web.dto.RoundDto;
import org.modelmapper.ModelMapper;

public class RoundMappingConfiguration implements MapperConfiguration {

    @Override
    public void execute(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Round.class, RoundDto.class)
                .addMapping(Round::gameId, RoundDto::setGameId)
                .addMapping(Round::roundId, RoundDto::setRoundId)
                .addMapping(Round::getWord, RoundDto::setWord)
                .addMapping(Round::endedAt, RoundDto::setEndedAt)
                .addMapping(Round::startedAt, RoundDto::setStartedAt)
                .addMapping(Round::count, RoundDto::setCount)
                .addMapping(Round::guessed, RoundDto::setGuessed);
    }
}
