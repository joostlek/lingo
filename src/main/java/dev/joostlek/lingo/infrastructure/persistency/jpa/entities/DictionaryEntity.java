package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity(name = "dictionary")
public class DictionaryEntity {

    @Id
    private Long id;

    private String language;

    @OneToMany(mappedBy = "dictionary")
    private Set<WordEntity> words;
}
