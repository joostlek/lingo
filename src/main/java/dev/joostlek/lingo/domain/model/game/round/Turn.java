package dev.joostlek.lingo.domain.model.game.round;

import dev.joostlek.lingo.domain.DomainEventPublisher;
import dev.joostlek.lingo.domain.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static dev.joostlek.lingo.domain.model.game.round.Round.TURNS_PER_ROUND;

public class Turn extends Entity {
    public static final int SECONDS_PER_TURN = 10;
    private Date endedAt;
    private TurnId turnId;
    private RoundId roundId;
    private Set<Result> results;
    private Date startedAt;
    private int count;

    public Turn() {
    }

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
                                conclusion.add(Result.present(guessChars[i1], i1));
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
        assertArgumentNotNull(turnId, "The turn id must be given.");

        this.turnId = turnId;
    }

    public void setRoundId(RoundId roundId) {
        assertArgumentNotNull(roundId, "The round id must be given.");

        this.roundId = roundId;
    }

    public void setCount(int count) {
        assertArgumentNotNull(count, "The turn count must be given.");
        assertArgumentRange(count, 0, TURNS_PER_ROUND, String.format("Turn count must be in between 0 - %d", TURNS_PER_ROUND));

        this.count = count;
    }

    public void setStartedAt(Date startedAt) {
        assertArgumentNotNull(startedAt, "The start date must be given.");

        this.startedAt = startedAt;
    }

    public void setResults(Set<Result> results) {
        assertArgumentNotNull(results, "The results may not be empty.");

        this.results = results;
    }

    public void setEndedAt(Date endedAt) {
        this.endedAt = endedAt;
    }
}
