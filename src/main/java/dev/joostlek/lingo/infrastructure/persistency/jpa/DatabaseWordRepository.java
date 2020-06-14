package dev.joostlek.lingo.infrastructure.persistency.jpa;

import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.domain.model.dictionary.word.WordId;
import dev.joostlek.lingo.domain.model.dictionary.word.WordRepository;
import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.WordEntity;
import dev.joostlek.lingo.infrastructure.persistency.jpa.repositories.WordJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DatabaseWordRepository implements WordRepository {

    private final ModelMapper modelMapper;

    private final WordJpaRepository wordJpaRepository;

    public DatabaseWordRepository(ModelMapper modelMapper, WordJpaRepository wordJpaRepository) {
        this.modelMapper = modelMapper;
        this.wordJpaRepository = wordJpaRepository;
    }

    @Override
    public WordId nextIdentity() {
        Long wordId = wordJpaRepository.getNextIdentity();
        return new WordId(String.valueOf(wordId));
    }

    @Override
    public Optional<Word> wordOfIdentity(WordId aWordId) {
        Long wordId = Long.parseLong(aWordId.id());
        WordEntity wordEntity = wordJpaRepository.findById(wordId)
                .orElse(null);
        return Optional.ofNullable(modelMapper.map(wordEntity, Word.class));
    }

    @Override
    public Collection<Word> allDictionaryWords(DictionaryId aDictionaryId) {
        Long dictionaryId = Long.parseLong(aDictionaryId.id());
        Collection<WordEntity> words = wordJpaRepository.getAllByDictionaryId(dictionaryId);
        return words.stream()
                .parallel()
                .map((word) -> modelMapper.map(word, Word.class))
                .collect(Collectors.toSet());
    }
}
