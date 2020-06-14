package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "word")
public class WordEntity {

    @Id
    private Long id;

    private String word;

    @ManyToOne
    @JoinColumn(name = "dictionary_id", nullable = false)
    private DictionaryEntity dictionary;
}
