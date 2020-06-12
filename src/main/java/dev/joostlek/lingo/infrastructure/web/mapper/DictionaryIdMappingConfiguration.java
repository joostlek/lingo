package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class DictionaryIdMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        Converter<DictionaryId, String> dictionaryIdConverter = new AbstractConverter<>() {
            @Override
            protected String convert(DictionaryId source) {
                return source.id();
            }
        };

        modelMapper.addConverter(dictionaryIdConverter);
    }
}
