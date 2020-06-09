package dev.joostlek.lingo.infrastructure.web.requests;

import lombok.Data;

@Data
public class CreateGameRequest {
    private int wordLength;

    private String chosenDictionaryId;
}
