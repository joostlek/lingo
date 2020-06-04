package dev.joostlek.lingo.infrastructure.web.requests;

import lombok.Data;

@Data
public class CreateDictionaryRequest {
    private String language;
}
