package dev.joostlek.lingo.domain.model.game.round;

import dev.joostlek.lingo.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
public class RoundId extends ValueObject {
    private String id;

    public RoundId(String id) {
        this();

        this.setId(id);
    }

    private RoundId() {
        super();
    }

    public String id() {
        return this.id;
    }

    public void setId(String id) {
        assertArgumentNotEmpty(id, "Het id mag niet leeg zijn.");
        assertArgumentLength(id, 36, "De id moet minder dan 36 tekens bevatten.");

        this.id = id;
    }
}
