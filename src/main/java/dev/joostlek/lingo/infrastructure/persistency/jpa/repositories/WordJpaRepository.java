package dev.joostlek.lingo.infrastructure.persistency.jpa.repositories;

import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface WordJpaRepository extends JpaRepository<WordEntity, Long> {
    @Query(value = "SELECT nextval('word_seq')", nativeQuery = true)
    Long getNextIdentity();

    Collection<WordEntity> getAllByDictionaryId(Long dictionaryId);
}
