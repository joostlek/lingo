package dev.joostlek.lingo.infrastructure.web.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RoundDto {
    private String gameId;
    private String roundId;
    private Date startedAt;
    private Date endedAt;
    private String word;
    private int count;
    private boolean guessed;
}
