package dev.joostlek.lingo.infrastructure.web.mapper;

import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.infrastructure.web.dto.WordDto;
import org.modelmapper.ModelMapper;

public class WordMappingConfiguration implements MapperConfiguration {
    @Override
    public void execute(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Word.class, WordDto.class)
                .addMapping(Word::dictionaryId, WordDto::setDictionaryId)
                .addMapping(Word::word, WordDto::setWord)
                .addMapping(Word::wordId, WordDto::setWordId);
    }
}
