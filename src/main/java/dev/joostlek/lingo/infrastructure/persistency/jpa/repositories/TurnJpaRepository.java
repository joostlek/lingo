package dev.joostlek.lingo.infrastructure.persistency.jpa.repositories;

import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.TurnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnJpaRepository extends JpaRepository<TurnEntity, Long> {
}
