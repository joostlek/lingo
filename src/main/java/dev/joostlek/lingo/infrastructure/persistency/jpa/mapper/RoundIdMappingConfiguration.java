package dev.joostlek.lingo.infrastructure.persistency.jpa.mapper;

import dev.joostlek.lingo.domain.model.game.round.RoundId;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class RoundIdMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        Converter<RoundId, Long> wordIdConverter = new AbstractConverter<>() {
            @Override
            protected Long convert(RoundId source) {
                return Long.parseLong(source.id());
            }
        };

        modelMapper.addConverter(wordIdConverter);
    }
}
