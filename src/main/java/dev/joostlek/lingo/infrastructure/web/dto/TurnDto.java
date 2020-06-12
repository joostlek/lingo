package dev.joostlek.lingo.infrastructure.web.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class TurnDto {
    private String turnId;
    private String roundId;
    private Date endedAt;
    private Date startedAt;
    private int count;
    private Set<ResultDto> results;
}
