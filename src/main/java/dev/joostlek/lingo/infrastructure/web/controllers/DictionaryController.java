package dev.joostlek.lingo.infrastructure.web.controllers;

import dev.joostlek.lingo.application.dictionary.DictionaryService;
import dev.joostlek.lingo.application.dictionary.commands.CreateDictionaryCommand;
import dev.joostlek.lingo.application.dictionary.commands.NewWordCommand;
import dev.joostlek.lingo.infrastructure.web.requests.AddWordRequest;
import dev.joostlek.lingo.infrastructure.web.requests.CreateDictionaryRequest;
import dev.joostlek.lingo.infrastructure.web.responses.CreateDictionaryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
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
