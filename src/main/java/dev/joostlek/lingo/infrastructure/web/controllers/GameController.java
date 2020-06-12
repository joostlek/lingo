package dev.joostlek.lingo.infrastructure.web.controllers;

import dev.joostlek.lingo.application.game.GameQueryService;
import dev.joostlek.lingo.application.game.GameService;
import dev.joostlek.lingo.domain.model.game.Game;
import dev.joostlek.lingo.infrastructure.web.dto.GameDto;
import dev.joostlek.lingo.infrastructure.web.requests.CreateGameRequest;
import dev.joostlek.lingo.infrastructure.web.util.BaseUrl;
import dev.joostlek.lingo.infrastructure.web.util.Response;
import dev.joostlek.lingo.infrastructure.web.util.ResponseBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(BaseUrl.V1_API + "/games")
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
    @ResponseStatus(HttpStatus.CREATED)
    public Response<GameDto> createGame(@RequestBody CreateGameRequest createGameRequest) {
        String gameId = gameService.createGame(createGameRequest.getWordLength(), createGameRequest.getChosenDictionaryId());
        Game game = gameQueryService.getGameByGameId(gameId);
        GameDto gameDto = modelMapper.map(game, GameDto.class);
        return ResponseBuilder.created()
                .data(gameDto)
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<Set<GameDto>> getAllGames() {
        Collection<Game> games = gameQueryService.getAllGames();
        Set<GameDto> gameSet = new HashSet<>();
        for (Game game : games) {
            GameDto gameDto = modelMapper.map(game, GameDto.class);
            gameSet.add(gameDto);
        }
        return ResponseBuilder.ok()
                .data(gameSet)
                .build();
    }
}
