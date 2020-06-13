package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import dev.joostlek.lingo.domain.model.dictionary.word.Word;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "round")
public class RoundEntity {

    @Id
    @SequenceGenerator(name = "round_id_generator", sequenceName = "round_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "round_id_generator")
    private Long id;

    private Date startedAt;

    private Date endedAt;

    private int count;

    private boolean guessed;

    @ManyToOne
    private GameEntity game;

    @OneToMany(mappedBy = "round")
    private Set<TurnEntity> turns;

    @ManyToOne
    private WordEntity answer;
}
