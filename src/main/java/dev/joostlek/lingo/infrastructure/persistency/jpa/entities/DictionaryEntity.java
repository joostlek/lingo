package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "dictionary")
public class DictionaryEntity {

    @Id
    @SequenceGenerator(name = "dictionary_id_generator", sequenceName = "dictionary_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dictionary_id_generator")
    private Long id;

    private String language;

    @OneToMany(mappedBy = "dictionary")
    private Set<WordEntity> words;
}
