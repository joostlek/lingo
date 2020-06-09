package dev.joostlek.lingo.infrastructure.persistency.memory;

import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.game.Game;
import dev.joostlek.lingo.domain.model.game.GameId;

import java.util.HashMap;
import java.util.Map;

public class InMemoryObject {

    private static InMemoryObject instance;

    private final Map<DictionaryId, Dictionary> dictionaries;

    private final Map<GameId, Game> games;

    private InMemoryObject() {
        this.dictionaries = new HashMap<>();
        this.games = new HashMap<>();
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
}
