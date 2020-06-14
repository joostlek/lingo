package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.dictionary.word.WordId;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class WordIdMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        Converter<WordId, String> wordIdConverter = new AbstractConverter<>() {
            @Override
            protected String convert(WordId source) {
                return source.id();
            }
        };

        modelMapper.addConverter(wordIdConverter);
    }
}
