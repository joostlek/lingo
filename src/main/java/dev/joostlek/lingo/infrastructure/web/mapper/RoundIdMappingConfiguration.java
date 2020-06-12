package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.game.round.RoundId;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class RoundIdMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        Converter<RoundId, String> roundIdConverter = new AbstractConverter<>() {
            @Override
            protected String convert(RoundId source) {
                return source.id();
            }
        };

        modelMapper.addConverter(roundIdConverter);
    }
}
