package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.game.round.TurnId;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class TurnIdMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        Converter<TurnId, String> turnIdConverter = new AbstractConverter<>() {
            @Override
            protected String convert(TurnId source) {
                return source.id();
            }
        };

        modelMapper.addConverter(turnIdConverter);
    }
}

