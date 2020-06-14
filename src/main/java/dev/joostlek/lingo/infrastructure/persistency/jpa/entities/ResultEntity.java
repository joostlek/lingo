package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import dev.joostlek.lingo.domain.model.game.round.Feedback;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "result")
public class ResultEntity {
    @EmbeddedId
    private ResultId resultId;

    private char character;

    @Enumerated(EnumType.ORDINAL)
    private Feedback feedback;

    public int getPosition() {
        return this.resultId.getPosition();
    }

    public void setPosition(int position) {
        this.resultId = new ResultId(position, null);
    }
}
