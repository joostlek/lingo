package dev.joostlek.lingo.infrastructure.web.dto;

import lombok.Data;

@Data
public class DictionaryDto {
    private String dictionaryId;
    private String language;
    private int size;
}
