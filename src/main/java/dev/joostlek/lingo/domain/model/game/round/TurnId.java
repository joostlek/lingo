package dev.joostlek.lingo.domain.model.game.round;

import dev.joostlek.lingo.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
public class TurnId extends ValueObject {
    private String id;

    public TurnId(String id) {
        this();

        this.setId(id);
    }

    private TurnId() {
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
