package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.util.HashSet;

public class DictionarySizeMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        Converter<HashSet<Word>, Integer> dictionaryIdConverter = new AbstractConverter<>() {
            @Override
            protected Integer convert(HashSet<Word> source) {
                return source.size();
            }
        };

        modelMapper.addConverter(dictionaryIdConverter);
    }
}
