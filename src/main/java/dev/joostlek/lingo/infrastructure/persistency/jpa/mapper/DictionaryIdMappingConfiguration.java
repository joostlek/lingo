package dev.joostlek.lingo.infrastructure.persistency.jpa.mapper;

import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class DictionaryIdMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        Converter<DictionaryId, Long> dictionaryIdConverter = new AbstractConverter<>() {
            @Override
            protected Long convert(DictionaryId source) {
                return Long.parseLong(source.id());
            }
        };

        modelMapper.addConverter(dictionaryIdConverter);
    }
}
