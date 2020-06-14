package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ResultId implements Serializable {
    @Column(name = "position")
    private int position;

    @JoinColumn(name = "turn_id")
    @ManyToOne
    private TurnEntity turn;
}
