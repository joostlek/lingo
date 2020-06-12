package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.infrastructure.web.dto.DictionaryDto;
import org.modelmapper.ModelMapper;

public class DictionaryMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Dictionary.class, DictionaryDto.class)
                .addMapping(Dictionary::dictionaryId, DictionaryDto::setDictionaryId)
                .addMapping(Dictionary::language, DictionaryDto::setLanguage);
    }
}
