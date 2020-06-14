package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import dev.joostlek.lingo.domain.model.WordLength;
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
@Entity(name = "game")
public class GameEntity {
    @Id
    private Long id;

    private Date createdAt;

    @Enumerated(EnumType.ORDINAL)
    private WordLength wordLength;

    @ManyToOne
    private DictionaryEntity dictionary;

    @OneToMany(mappedBy = "game")
    private Set<RoundEntity> rounds;
}
