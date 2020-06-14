package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import dev.joostlek.lingo.domain.model.game.round.Feedback;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "result")
public class ResultEntity {
    @Id
    private Long id;

    private char character;

    private int position;

    @Enumerated(EnumType.ORDINAL)
    private Feedback feedback;

    @ManyToOne
    private TurnEntity turn;
}
