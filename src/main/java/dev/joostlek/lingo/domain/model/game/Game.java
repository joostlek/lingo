package dev.joostlek.lingo.domain.model.game;

import dev.joostlek.lingo.domain.Entity;
import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.domain.model.WordLength;

import java.util.Set;

public class Game extends Entity {
    private Set<Turn> turns;

    private WordLength wordLength;

    private Dictionary chosenDictionary;

    private Word answer;

    public Game(WordLength wordLength, Dictionary chosenDictionary) {
        this.wordLength = wordLength;
        this.chosenDictionary = chosenDictionary;
    }
}
