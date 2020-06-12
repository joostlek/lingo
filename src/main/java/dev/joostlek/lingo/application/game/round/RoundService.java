package dev.joostlek.lingo.application.game.round;

public interface RoundService {
    void endRound(String aRoundId);

    String performTurn(String aRoundId, String guess);
}
