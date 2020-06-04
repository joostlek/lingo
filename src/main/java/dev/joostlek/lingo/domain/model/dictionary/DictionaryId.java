package dev.joostlek.lingo.domain.model.dictionary;

import dev.joostlek.lingo.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
public class DictionaryId extends ValueObject {
    private String id;

    public DictionaryId(String id) {
        this();

        this.setId(id);
    }

    private DictionaryId() {
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
