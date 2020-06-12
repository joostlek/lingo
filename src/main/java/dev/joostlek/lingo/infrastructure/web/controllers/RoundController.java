package dev.joostlek.lingo.infrastructure.web.controllers;

import dev.joostlek.lingo.application.game.GameService;
import dev.joostlek.lingo.application.game.round.RoundQueryService;
import dev.joostlek.lingo.application.game.round.RoundService;
import dev.joostlek.lingo.domain.model.game.round.Round;
import dev.joostlek.lingo.infrastructure.web.dto.RoundDto;
import dev.joostlek.lingo.infrastructure.web.util.BaseUrl;
import dev.joostlek.lingo.infrastructure.web.util.Response;
import dev.joostlek.lingo.infrastructure.web.util.ResponseBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(BaseUrl.V1_API + "/games/{gameId}/rounds")
public class RoundController {

    private final RoundQueryService roundQueryService;

    private final GameService gameService;

    private final ModelMapper modelMapper;

    private final RoundService roundService;

    public RoundController(RoundQueryService roundQueryService, ModelMapper modelMapper, GameService gameService, RoundService roundService) {
        this.roundQueryService = roundQueryService;
        this.modelMapper = modelMapper;
        this.gameService = gameService;
        this.roundService = roundService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<Set<RoundDto>> getRoundsByGameId(@PathVariable String gameId) {
        Set<RoundDto> roundsSet = new HashSet<>();
        roundQueryService.getRoundsByGameId(gameId)
                .forEach(
                        round -> {
                            RoundDto roundDto = modelMapper.map(round, RoundDto.class);
                            roundsSet.add(roundDto);
                        }
                );
        return ResponseBuilder.ok()
                .data(roundsSet)
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<RoundDto> startNewRound(@PathVariable String gameId) {
        String roundId = gameService.startRound(gameId);
        Round round = roundQueryService.getRoundById(roundId);
        RoundDto roundDto = modelMapper.map(round, RoundDto.class);
        return ResponseBuilder.created()
                .data(roundDto)
                .build();
    }
}
