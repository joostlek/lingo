package dev.joostlek.lingo.infrastructure.persistency.memory;

import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.domain.model.dictionary.word.WordId;
import dev.joostlek.lingo.domain.model.dictionary.word.WordRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class InMemoryWordRepository implements WordRepository {
    @Override
    public WordId nextIdentity() {
        UUID uuid = UUID.randomUUID();
        WordId wordId = new WordId(uuid.toString());
        if (InMemoryObject.instance()
                .words()
                .containsKey(wordId)) {
            return nextIdentity();
        }
        return wordId;
    }

    @Override
    public Optional<Word> wordOfIdentity(WordId wordId) {
        return Optional.ofNullable(
                InMemoryObject.instance()
                        .words()
                        .get(wordId)
        );
    }

    @Override
    public Collection<Word> allDictionaryWords(DictionaryId dictionaryId) {
        return InMemoryObject.instance()
                .dictionaries()
                .get(dictionaryId)
                .allWords();
    }
}
