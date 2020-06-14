package dev.joostlek.lingo.domain.model.game.round;

import dev.joostlek.lingo.domain.Entity;
import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.domain.model.game.GameId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static dev.joostlek.lingo.domain.model.game.Game.ROUNDS_PER_GAME;

public class Round extends Entity {
    public static final int TURNS_PER_ROUND = 5;
    private final List<Turn> turns;
    private Date startedAt;
    private GameId gameId;
    private RoundId roundId;
    private Date endedAt;
    private Word answer;
    private int count;
    private boolean guessed;

    public Round() {
        this.turns = new ArrayList<>();
    }

    public Round(GameId gameId, RoundId roundId, Word answer, int count) {
        this.setGameId(gameId);
        this.setRoundId(roundId);
        this.setAnswer(answer);
        this.setCount(count);
        this.startedAt = new Date();
        this.turns = new ArrayList<>();
        this.guessed = false;
    }

    public void performTurn(TurnId turnId, String guess) {
        assertArgumentNotNull(turnId, "Turn must be given to play it.");
        assertArgumentNotNull(guess, "Guess must be given.");

        if (this.endedAt() != null) {
            throw new IllegalStateException("The game already ended.");
        }

        if (this.turns().size() > TURNS_PER_ROUND) {
            throw new IllegalStateException("The maximum of 5 rounds reached.");
        }

        Turn turn = new Turn(turnId, roundId(), this.turns().size());
        turn.setStartedAt(this.getLastEndDate());
        turn.validate(guess, this.answer.word());
        this.turns().add(turn);

        if (this.turns().size() == TURNS_PER_ROUND) {
            this.endRound(false);
        }
    }

    public String getWord() {
        if (this.endedAt == null) {
            return this.getWordFromTurns();
        } else {
            return this.answer().word();
        }
    }

    private String getWordFromTurns() {
        char[] word = new char[answer().word().length()];
        word[0] = answer().word().charAt(0);
        for (Turn turn : turns()) {
            for (Result result : turn.results()) {
                if (result.feedback() == Feedback.CORRECT) {
                    word[result.position()] = result.character();
                }
            }
        }
        for (int i = 0; i < word.length; i++) {
            if (word[i] == 0) {
                word[i] = ' ';
            }
        }
        return new String(word);
    }

    private Date getLastEndDate() {
        if (this.turns().isEmpty()) {
            return this.startedAt();
        }
        Turn turn = this.turns().get(0);
        for (Turn turn1 : turns()) {
            if (turn1.endedAt().after(turn.endedAt())) {
                turn = turn1;
            }
        }
        return turn.endedAt();
    }

    public void endRound(boolean guessed) {
        this.endedAt = new Date();
        this.guessed = guessed;
    }

    public boolean isActive() {
        return this.endedAt() == null;
    }

    public GameId gameId() {
        return gameId;
    }

    public RoundId roundId() {
        return roundId;
    }

    public List<Turn> turns() {
        return turns;
    }

    public Date startedAt() {
        return startedAt;
    }

    public Date endedAt() {
        return endedAt;
    }

    public Word answer() {
        return answer;
    }

    public int count() {
        return count;
    }

    public boolean guessed() {
        return guessed;
    }

    public void setGameId(GameId gameId) {
        assertArgumentNotNull(gameId, "The game id must be given.");

        this.gameId = gameId;
    }

    public void setRoundId(RoundId roundId) {
        assertArgumentNotNull(roundId, "The round id must be given.");

        this.roundId = roundId;
    }

    public void setAnswer(Word answer) {
        assertArgumentNotNull(answer, "The answer must be given.");

        this.answer = answer;
    }

    public void setCount(int count) {
        assertArgumentNotNull(count, "The round count must be given");
        assertArgumentRange(count, 0, ROUNDS_PER_GAME, String.format("Round count must be in between 0 - %d", ROUNDS_PER_GAME));

        this.count = count;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public void setEndedAt(Date endedAt) {
        this.endedAt = endedAt;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }
}
