package dev.joostlek.lingo.infrastructure.persistency.jpa.mapper;

import dev.joostlek.lingo.domain.model.game.round.TurnId;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class TurnIdMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        Converter<TurnId, Long> turnIdConverter = new AbstractConverter<>() {
            @Override
            protected Long convert(TurnId source) {
                return Long.parseLong(source.id());
            }
        };

        modelMapper.addConverter(turnIdConverter);
    }
}
