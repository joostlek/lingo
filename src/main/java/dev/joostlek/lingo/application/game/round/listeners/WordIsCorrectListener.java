package dev.joostlek.lingo.application.game.round.listeners;

import dev.joostlek.lingo.application.game.round.RoundService;
import dev.joostlek.lingo.domain.DomainEventSubscriber;
import dev.joostlek.lingo.domain.model.game.round.WordIsCorrect;

public class WordIsCorrectListener implements DomainEventSubscriber<WordIsCorrect> {
    private final RoundService roundService;

    public WordIsCorrectListener(RoundService roundService) {
        this.roundService = roundService;
    }

    @Override
    public void handleEvent(WordIsCorrect wordIsCorrect) {
        roundService.endRound(wordIsCorrect.roundId().id());
    }

    @Override
    public Class<WordIsCorrect> subscribedToEventType() {
        return WordIsCorrect.class;
    }
}
