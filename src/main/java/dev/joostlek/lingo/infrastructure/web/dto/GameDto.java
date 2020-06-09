package dev.joostlek.lingo.infrastructure.web.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GameDto {
    private String gameId;

    private Date createdAt;

    private int wordLength;

    private String chosenDictionaryId;
}
