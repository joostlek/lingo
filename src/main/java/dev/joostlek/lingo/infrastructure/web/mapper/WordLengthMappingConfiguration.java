package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.WordLength;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordLengthMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        Converter<WordLength, Integer> wordLengthConverter = new AbstractConverter<>() {
            @Override
            protected Integer convert(WordLength wordLength) {
                return wordLength.getLength();
            }
        };

        modelMapper.addConverter(wordLengthConverter);
    }
}
