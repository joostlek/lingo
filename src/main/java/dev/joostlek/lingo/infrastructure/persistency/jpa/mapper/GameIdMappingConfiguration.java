package dev.joostlek.lingo.infrastructure.persistency.jpa.mapper;

import dev.joostlek.lingo.domain.model.game.GameId;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class GameIdMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        Converter<GameId, Long> gameIdConverter = new AbstractConverter<>() {
            @Override
            protected Long convert(GameId source) {
                return Long.parseLong(source.id());
            }
        };

        modelMapper.addConverter(gameIdConverter);

        Converter<Long, GameId> gameIdLongConverter = new AbstractConverter<>() {
            @Override
            protected GameId convert(Long source) {
                return new GameId(String.valueOf(source));
            }
        };

        modelMapper.addConverter(gameIdLongConverter);
    }
}
