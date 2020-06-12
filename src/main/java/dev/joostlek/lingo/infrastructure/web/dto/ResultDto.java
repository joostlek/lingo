package dev.joostlek.lingo.infrastructure.web.dto;

import lombok.Data;

@Data
public class ResultDto {
    private String character;
    private int position;
    private String feedback;
}
