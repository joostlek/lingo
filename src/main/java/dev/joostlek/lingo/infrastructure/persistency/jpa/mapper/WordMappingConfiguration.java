package dev.joostlek.lingo.infrastructure.persistency.jpa.mapper;

import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.WordEntity;
import dev.joostlek.lingo.util.MapperConfiguration;
import org.modelmapper.ModelMapper;

public class WordMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Word.class, WordEntity.class)
                .addMapping(Word::word, WordEntity::setWord)
                .addMapping(Word::wordId, WordEntity::setId);
    }
}
