package dev.joostlek.lingo.infrastructure.persistency.memory;

import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.domain.model.dictionary.word.WordId;
import dev.joostlek.lingo.domain.model.game.Game;
import dev.joostlek.lingo.domain.model.game.GameId;
import dev.joostlek.lingo.domain.model.game.round.Round;
import dev.joostlek.lingo.domain.model.game.round.RoundId;
import dev.joostlek.lingo.domain.model.game.round.Turn;
import dev.joostlek.lingo.domain.model.game.round.TurnId;

import java.util.HashMap;
import java.util.Map;

public class InMemoryObject {

    private static InMemoryObject instance;

    private final Map<DictionaryId, Dictionary> dictionaries;

    private final Map<GameId, Game> games;

    private final Map<RoundId, Round> rounds;

    private final Map<WordId, Word> words;

    private final Map<TurnId, Turn> turns;

    private InMemoryObject() {
        this.dictionaries = new HashMap<>();
        this.games = new HashMap<>();
        this.rounds = new HashMap<>();
        this.words = new HashMap<>();
        this.turns = new HashMap<>();
    }

    public static InMemoryObject instance() {
        if (instance == null) {
            instance = new InMemoryObject();
        }
        return instance;
    }

    public Map<DictionaryId, Dictionary> dictionaries() {
        return dictionaries;
    }

    public Map<GameId, Game> games() {
        return games;
    }

    public Map<RoundId, Round> rounds() {
        return rounds;
    }

    public Map<WordId, Word> words() {
        return words;
    }

    public Map<TurnId, Turn> turns() {
        return turns;
    }
}
