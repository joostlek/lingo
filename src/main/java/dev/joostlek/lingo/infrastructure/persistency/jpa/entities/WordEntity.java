package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "word")
public class WordEntity {

    @Id
    @SequenceGenerator(name = "word_id_generator", sequenceName = "word_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "word_id_generator")
    private Long id;

    private String word;

    @ManyToOne
    @JoinColumn(name = "dictionary_id", nullable = false)
    private DictionaryEntity dictionary;
}
