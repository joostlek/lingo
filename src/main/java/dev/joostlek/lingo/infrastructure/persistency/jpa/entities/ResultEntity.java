package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import dev.joostlek.lingo.domain.model.game.round.Feedback;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "result")
public class ResultEntity {
    @Id
    @SequenceGenerator(name = "result_id_generator", sequenceName = "result_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_id_generator")
    private Long id;

    private char character;

    private int position;

    @Enumerated(EnumType.ORDINAL)
    private Feedback feedback;

    @ManyToOne
    private TurnEntity turn;
}
