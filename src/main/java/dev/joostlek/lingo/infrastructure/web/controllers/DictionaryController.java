package dev.joostlek.lingo.infrastructure.web.controllers;

import dev.joostlek.lingo.application.dictionary.DictionaryQueryService;
import dev.joostlek.lingo.application.dictionary.DictionaryService;
import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import dev.joostlek.lingo.infrastructure.web.dto.DictionaryDto;
import dev.joostlek.lingo.infrastructure.web.dto.WordDto;
import dev.joostlek.lingo.infrastructure.web.requests.AddWordRequest;
import dev.joostlek.lingo.infrastructure.web.requests.CreateDictionaryRequest;
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
@RequestMapping(BaseUrl.V1_API + "/dictionaries")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    private final DictionaryQueryService dictionaryQueryService;

    private final ModelMapper modelMapper;

    public DictionaryController(DictionaryService dictionaryService, DictionaryQueryService dictionaryQueryService, ModelMapper modelMapper) {
        this.dictionaryService = dictionaryService;
        this.dictionaryQueryService = dictionaryQueryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{dictionaryId}/words")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<WordDto> addWord(@RequestBody AddWordRequest addWordRequest, @PathVariable String dictionaryId) {
        String wordId = dictionaryService.addWord(dictionaryId, addWordRequest.getWord());
        Word word = dictionaryQueryService.getWordById(wordId);
        WordDto wordDto = modelMapper.map(word, WordDto.class);
        return ResponseBuilder.created()
                .data(wordDto)
                .build();
    }

    @GetMapping("/{dictionaryId}/words")
    @ResponseStatus(HttpStatus.OK)
    public Response<Set<WordDto>> getWordsByDictionary(@PathVariable String dictionaryId) {
        Collection<Word> words = dictionaryQueryService.getWordsByDictionaryId(dictionaryId);
        Set<WordDto> wordSet = new HashSet<>();
        for (Word word : words) {
            WordDto wordDto = modelMapper.map(word, WordDto.class);
            wordSet.add(wordDto);
        }
        return ResponseBuilder.ok()
                .data(wordSet)
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Dictionary> createDictionary(@RequestBody CreateDictionaryRequest createDictionaryRequest) {
        String dictionaryId = dictionaryService.createDictionary(createDictionaryRequest.getLanguage());
        Dictionary dictionary = dictionaryQueryService.getDictionaryById(dictionaryId);
        DictionaryDto dictionaryDto = modelMapper.map(dictionary, DictionaryDto.class);
        return ResponseBuilder.created()
                .data(dictionaryDto)
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<Set<Dictionary>> getDictionaries() {
        Collection<Dictionary> dictionaries = dictionaryQueryService.getDictionaries();
        Set<DictionaryDto> dictionarySet = new HashSet<>();
        for (Dictionary dictionary : dictionaries) {
            DictionaryDto dictionaryDto = modelMapper.map(dictionary, DictionaryDto.class);
            dictionarySet.add(dictionaryDto);
        }
        return ResponseBuilder.ok()
                .data(dictionarySet)
                .build();
    }
}
