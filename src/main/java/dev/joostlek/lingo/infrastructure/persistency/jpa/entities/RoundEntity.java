package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "round")
public class RoundEntity {

    @Id
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
