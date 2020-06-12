package dev.joostlek.lingo.domain.model.game.round;

import dev.joostlek.lingo.domain.DomainEventPublisher;
import dev.joostlek.lingo.domain.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Turn extends Entity {
    public static final int SECONDS_PER_TURN = 10;
    private final Date endedAt;
    private TurnId turnId;
    private RoundId roundId;
    private Set<Result> results;
    private Date startedAt;
    private int count;

    public Turn(TurnId turnId, RoundId roundId, int count) {
        this.setTurnId(turnId);
        this.setRoundId(roundId);
        this.setCount(count);
        this.endedAt = new Date();
    }

    public void validate(String guess, String answer) {
        Set<Result> conclusion = new HashSet<>();

        char[] answerChars = answer.toCharArray();
        char[] guessChars = guess.toCharArray();
        if (answer.length() != guess.length() || (endedAt().getTime() - startedAt().getTime()) / 1000 > SECONDS_PER_TURN) {
            for (int i = 0; i < answerChars.length; i++) {
                conclusion.add(Result.invalid(guessChars[i], i));
            }
        } else {
            for (int i = 0; i < answerChars.length; i++) {
                if (answerChars[i] == guessChars[i]) {
                    conclusion.add(Result.correct(guessChars[i], i));
                    answerChars[i] = '0';
                    guessChars[i] = '0';
                }
            }
            if (conclusion.size() == answer.length()) {
                DomainEventPublisher.instance()
                        .publish(new WordIsCorrect(this.roundId(), this.turnId()));
            } else {
                for (int i1 = 0; i1 < guessChars.length; i1++) {
                    if (guessChars[i1] != '0') {
                        for (int i = 0; i < answerChars.length; i++) {
                            if (answerChars[i] != '0' && answerChars[i] == guessChars[i1]) {
                                conclusion.add(Result.present(guessChars[i1], i));
                                answerChars[i] = '0';
                                guessChars[i1] = '0';
                            }
                        }
                    }
                }
                for (int i = 0; i < guessChars.length; i++) {
                    if (guessChars[i] != '0') {
                        conclusion.add(Result.absent(guessChars[i], i));
                    }
                }
            }
        }
        this.setResults(conclusion);
    }

    public TurnId turnId() {
        return turnId;
    }

    public RoundId roundId() {
        return roundId;
    }

    public Set<Result> results() {
        return results;
    }

    public Date startedAt() {
        return startedAt;
    }

    public Date endedAt() {
        return endedAt;
    }

    public int count() {
        return count;
    }

    public void setTurnId(TurnId turnId) {
        assertArgumentNotNull(turnId, "De dictionary moet worden meegegeven.");

        this.turnId = turnId;
    }

    public void setRoundId(RoundId roundId) {
        assertArgumentNotNull(roundId, "De dictionary moet worden meegegeven.");

        this.roundId = roundId;
    }

    public void setCount(int count) {
        assertArgumentNotNull(count, "De dictionary moet worden meegegeven.");

        this.count = count;
    }

    public void setStartedAt(Date startedAt) {
        assertArgumentNotNull(startedAt, "De dictionary moet worden meegegeven.");

        this.startedAt = startedAt;
    }

    public void setResults(Set<Result> results) {
        assertArgumentNotNull(results, "The results may not be empty.");

        this.results = results;
    }
}
