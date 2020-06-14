package dev.joostlek.lingo.infrastructure.persistency.jpa.mapper;

import dev.joostlek.lingo.domain.model.dictionary.word.WordId;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class WordIdMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        Converter<WordId, Long> wordIdConverter = new AbstractConverter<>() {
            @Override
            protected Long convert(WordId source) {
                return Long.parseLong(source.id());
            }
        };

        modelMapper.addConverter(wordIdConverter);
    }
}
