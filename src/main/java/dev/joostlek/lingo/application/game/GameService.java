package dev.joostlek.lingo.application.game;

public interface GameService {
    String createGame(int aWordLength, String aDictionaryId);

    String startRound(String aGameId);
}
