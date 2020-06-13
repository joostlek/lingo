package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import dev.joostlek.lingo.domain.model.WordLength;
import dev.joostlek.lingo.domain.model.dictionary.Dictionary;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "game")
public class GameEntity {
    @Id
    @SequenceGenerator(name = "game_id_generator", sequenceName = "game_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_generator")
    private Long id;

    private Date createdAt;

    @Enumerated(EnumType.ORDINAL)
    private WordLength wordLength;

    @ManyToOne
    private DictionaryEntity dictionary;

    @OneToMany(mappedBy = "game")
    private Set<RoundEntity> rounds;
}
