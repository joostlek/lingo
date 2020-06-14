package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "turn")
public class TurnEntity {
    @Id
    private Long id;

    private Date endedAt;

    private Date startedAt;

    private int count;

    @ManyToOne
    private RoundEntity round;

    @OneToMany(mappedBy = "resultId.turn", cascade = CascadeType.ALL)
    private Set<ResultEntity> results;
}
