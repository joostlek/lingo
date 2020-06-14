package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "turn")
public class TurnEntity {
    @Id
    private Long id;

    private Date endedAt;

    private Date startedAt;

    private int count;

    @ManyToOne
    private RoundEntity round;

    @OneToMany(mappedBy = "turn")
    private Set<ResultEntity> results;
}
