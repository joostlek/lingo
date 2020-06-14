package dev.joostlek.lingo.infrastructure.persistency.jpa.repositories;

import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.DictionaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryJpaRepository extends JpaRepository<DictionaryEntity, Long> {
    @Query(value = "SELECT nextval('dictionary_seq')", nativeQuery = true)
    Long getNextIdentity();
}
