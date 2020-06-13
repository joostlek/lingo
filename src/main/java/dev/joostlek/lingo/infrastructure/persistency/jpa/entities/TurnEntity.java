package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "turn")
public class TurnEntity {
    @Id
    @SequenceGenerator(name = "turn_id_generator", sequenceName = "turn_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turn_id_generator")
    private Long id;

    private Date endedAt;

    private Date startedAt;

    private int count;

    @ManyToOne
    private RoundEntity round;

    @OneToMany(mappedBy = "turn")
    private Set<ResultEntity> results;
}
