package dev.joostlek.lingo.domain.model.game;

import dev.joostlek.lingo.domain.DomainEventPublisher;
import dev.joostlek.lingo.domain.Entity;
import dev.joostlek.lingo.domain.model.WordLength;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.word.Word;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Game extends Entity {
    private GameId gameId;

    private Date createdAt;

    private Set<Turn> turns;

    private WordLength wordLength;

    private DictionaryId chosenDictionaryId;

    private Word answer;

    public Game(GameId gameId, WordLength wordLength, DictionaryId chosenDictionaryId) {
        super();

        this.createdAt = new Date();
        this.turns = new HashSet<>();
        this.setGameId(gameId);
        this.setWordLength(wordLength);
        this.setChosenDictionaryId(chosenDictionaryId);

        DomainEventPublisher
                .instance()
                .publish(new GameCreated(
                        this.gameId,
                        this.createdAt,
                        this.wordLength,
                        this.chosenDictionaryId
                ));
    }

    public GameId gameId() {
        return gameId;
    }

    public Date createdAt() {
        return createdAt;
    }

    public Set<Turn> turns() {
        return turns;
    }

    public WordLength wordLength() {
        return wordLength;
    }

    public DictionaryId chosenDictionaryId() {
        return chosenDictionaryId;
    }

    public Word answer() {
        return answer;
    }

    public void setGameId(GameId gameId) {
        assertArgumentNotNull(turns, "De game id moeten worden meegegeven.");

        this.gameId = gameId;
    }

    public void setCreatedAt(Date createdAt) {
        assertArgumentNotNull(turns, "De aanmaakdatum moeten worden meegegeven.");

        this.createdAt = createdAt;
    }

    public void setTurns(Set<Turn> turns) {
        assertArgumentNotNull(turns, "De rondes moeten worden meegegeven.");

        this.turns = turns;
    }

    public void setWordLength(WordLength wordLength) {
        assertArgumentNotNull(wordLength, "De woordlengte moet worden meegegeven.");

        this.wordLength = wordLength;
    }

    public void setChosenDictionaryId(DictionaryId chosenDictionaryId) {
        assertArgumentNotNull(wordLength, "De dictionary moet worden meegegeven.");

        this.chosenDictionaryId = chosenDictionaryId;
    }

    public void setAnswer(Word answer) {
        this.answer = answer;
    }
}
