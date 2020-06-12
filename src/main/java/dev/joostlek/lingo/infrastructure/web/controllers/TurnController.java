package dev.joostlek.lingo.infrastructure.web.controllers;

import dev.joostlek.lingo.application.game.round.RoundService;
import dev.joostlek.lingo.application.game.round.turn.TurnQueryService;
import dev.joostlek.lingo.domain.model.game.round.Turn;
import dev.joostlek.lingo.infrastructure.web.dto.TurnDto;
import dev.joostlek.lingo.infrastructure.web.requests.PerformTurnRequest;
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
@RequestMapping(BaseUrl.V1_API + "/games/{gameId}/rounds/{roundId}/turns")
public class TurnController {
    private final TurnQueryService turnQueryService;

    private final ModelMapper modelMapper;

    private final RoundService roundService;

    public TurnController(TurnQueryService turnQueryService, ModelMapper modelMapper, RoundService roundService) {
        this.turnQueryService = turnQueryService;
        this.modelMapper = modelMapper;
        this.roundService = roundService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<TurnDto> performTurn(@PathVariable String gameId, @PathVariable String roundId, @RequestBody PerformTurnRequest performTurnRequest) {
        String turnId = roundService.performTurn(roundId, performTurnRequest.getGuess());
        Turn turn = turnQueryService.getTurnByTurnId(turnId);
        TurnDto turnDto = modelMapper.map(turn, TurnDto.class);
        return ResponseBuilder.created()
                .data(turnDto)
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<Set<TurnDto>> getTurnsByRoundId(@PathVariable String gameId, @PathVariable String roundId) {
        Collection<Turn> turns = turnQueryService.getTurnsByRoundId(roundId);
        Set<TurnDto> turnSet = new HashSet<>();
        for (Turn turn : turns) {
            TurnDto turnDto = modelMapper.map(turn, TurnDto.class);
            turnSet.add(turnDto);
        }
        return ResponseBuilder.ok()
                .data(turnSet)
                .build();
    }
}
