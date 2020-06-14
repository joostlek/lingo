package dev.joostlek.lingo.infrastructure.persistency.jpa.mapper;

import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.DictionaryEntity;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.ModelMapper;

public class DictionaryMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Dictionary.class, DictionaryEntity.class)
                .addMapping(Dictionary::language, DictionaryEntity::setLanguage)
                .addMapping(Dictionary::allWords, DictionaryEntity::setWords)
                .addMapping(Dictionary::dictionaryId, DictionaryEntity::setId);
    }
}
