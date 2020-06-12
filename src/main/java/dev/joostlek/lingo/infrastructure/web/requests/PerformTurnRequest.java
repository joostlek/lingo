package dev.joostlek.lingo.infrastructure.web.requests;

import lombok.Data;

@Data
public class PerformTurnRequest {
    private String guess;
}
