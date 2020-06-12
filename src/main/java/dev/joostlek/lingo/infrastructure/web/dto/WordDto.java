package dev.joostlek.lingo.infrastructure.web.dto;

import lombok.Data;

@Data
public class WordDto {
    private String dictionaryId;

    private String wordId;

    private String word;
}
