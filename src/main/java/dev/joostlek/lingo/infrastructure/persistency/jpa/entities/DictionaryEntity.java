package dev.joostlek.lingo.infrastructure.persistency.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "dictionary")
public class DictionaryEntity {

    @Id
    private Long id;

    private String language;

    @OneToMany(mappedBy = "dictionary", cascade = CascadeType.ALL)
    private Set<WordEntity> words;
}
