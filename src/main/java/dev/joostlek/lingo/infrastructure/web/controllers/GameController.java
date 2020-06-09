package dev.joostlek.lingo.infrastructure.web.controllers;

import dev.joostlek.lingo.application.game.GameQueryService;
import dev.joostlek.lingo.application.game.GameService;
import dev.joostlek.lingo.domain.model.game.Game;
import dev.joostlek.lingo.infrastructure.web.dto.GameDto;
import dev.joostlek.lingo.infrastructure.web.requests.CreateGameRequest;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameService gameService;

    private final GameQueryService gameQueryService;

    private final ModelMapper modelMapper;

    public GameController(GameService gameService, GameQueryService gameQueryService, ModelMapper modelMapper) {
        this.gameService = gameService;
        this.gameQueryService = gameQueryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public GameDto createGame(@RequestBody CreateGameRequest createGameRequest) {
        String gameId = gameService.createGame(createGameRequest.getWordLength(), createGameRequest.getChosenDictionaryId());
        Game game = gameQueryService.getGameByGameId(gameId);
        return modelMapper.map(game, GameDto.class);
    }
}
