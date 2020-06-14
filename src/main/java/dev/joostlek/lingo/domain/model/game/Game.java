package dev.joostlek.lingo.domain.model.game;

import dev.joostlek.lingo.domain.DomainEventPublisher;
import dev.joostlek.lingo.domain.Entity;
import dev.joostlek.lingo.domain.model.WordLength;
import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.domain.model.dictionary.DictionaryId;
import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.domain.model.game.round.Round;
import dev.joostlek.lingo.domain.model.game.round.RoundId;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Game extends Entity {
    private GameId gameId;

    private Date createdAt;

    public static final int ROUNDS_PER_GAME = 5;

    private WordLength wordLength;

    private DictionaryId chosenDictionaryId;
    private Set<Round> rounds;

    public Game() {
    }

    public Game(GameId gameId, WordLength wordLength, DictionaryId chosenDictionaryId) {
        super();

        this.createdAt = new Date();
        this.rounds = new HashSet<>();
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

    public void startRound(RoundId roundId, Dictionary dictionary) {
        if (!dictionary.dictionaryId().equals(this.chosenDictionaryId())) {
            throw new IllegalArgumentException("Wrong dictionary was given.");
        }
        if (this.rounds().size() > ROUNDS_PER_GAME) {
            throw new IllegalStateException("Maximum rounds for this game reached.");
        }
        if (this.hasActiveRounds()) {
            throw new IllegalStateException("A round in this game is still active.");
        }
        Word answer = dictionary.getRandomWord(this.wordLength());
        this.rounds.add(new Round(this.gameId(), roundId, answer, this.rounds().size()));
    }

    public boolean hasActiveRounds() {
        for (Round round : this.rounds()) {
            if (round.isActive()) {
                return true;
            }
        }
        return false;
    }

    public GameId gameId() {
        return gameId;
    }

    public Date createdAt() {
        return createdAt;
    }

    public Set<Round> rounds() {
        return rounds;
    }

    public WordLength wordLength() {
        return wordLength;
    }

    public DictionaryId chosenDictionaryId() {
        return chosenDictionaryId;
    }

    public void setGameId(GameId gameId) {
        assertArgumentNotNull(gameId, "De game id moeten worden meegegeven.");

        this.gameId = gameId;
    }

    public void setCreatedAt(Date createdAt) {
        assertArgumentNotNull(createdAt, "De aanmaakdatum moeten worden meegegeven.");

        this.createdAt = createdAt;
    }

    public void setRounds(Set<Round> rounds) {
        assertArgumentNotNull(rounds, "De rondes moeten worden meegegeven.");

        this.rounds = rounds;
    }

    public void setWordLength(WordLength wordLength) {
        assertArgumentNotNull(wordLength, "De woordlengte moet worden meegegeven.");

        this.wordLength = wordLength;
    }

    public void setChosenDictionaryId(DictionaryId chosenDictionaryId) {
        assertArgumentNotNull(chosenDictionaryId, "De dictionary moet worden meegegeven.");

        this.chosenDictionaryId = chosenDictionaryId;
    }
}
