package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.game.GameId;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameIdMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        Converter<GameId, String> gameIdConverter = new AbstractConverter<>() {
            @Override
            protected String convert(GameId source) {
                return source.id();
            }
        };

        modelMapper.addConverter(gameIdConverter);
    }
}
