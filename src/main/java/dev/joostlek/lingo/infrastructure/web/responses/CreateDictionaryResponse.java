package dev.joostlek.lingo.infrastructure.web.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateDictionaryResponse {
    private String dictionaryId;
    private String language;
}
