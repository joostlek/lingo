package dev.joostlek.lingo.infrastructure.web.controllers;

import dev.joostlek.lingo.application.dictionary.DictionaryService;
import dev.joostlek.lingo.application.dictionary.commands.CreateDictionaryCommand;
import dev.joostlek.lingo.application.dictionary.commands.NewWordCommand;
import dev.joostlek.lingo.infrastructure.web.dto.WordDTO;
import dev.joostlek.lingo.infrastructure.web.requests.AddWordRequest;
import dev.joostlek.lingo.infrastructure.web.requests.CreateDictionaryRequest;
import dev.joostlek.lingo.infrastructure.web.responses.CreateDictionaryResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DictionaryController {

    private final DictionaryService dictionaryService;

    private final ModelMapper modelMapper;

    public DictionaryController(DictionaryService dictionaryService, ModelMapper modelMapper) {
        this.dictionaryService = dictionaryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/dictionaries/{dictionaryId}/words")
    @ResponseStatus(HttpStatus.CREATED)
    public void addWord(@RequestBody AddWordRequest addWordRequest, @PathVariable String dictionaryId) {
        dictionaryService.addWord(new NewWordCommand(dictionaryId, addWordRequest.getWord()));
    }

    @PostMapping("/dictionaries")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateDictionaryResponse createDictionary(@RequestBody CreateDictionaryRequest createDictionaryRequest) {
        String dictionaryId = dictionaryService.createDictionary(new CreateDictionaryCommand(createDictionaryRequest.getLanguage()));
        return new CreateDictionaryResponse(dictionaryId, createDictionaryRequest.getLanguage());
    }
}
