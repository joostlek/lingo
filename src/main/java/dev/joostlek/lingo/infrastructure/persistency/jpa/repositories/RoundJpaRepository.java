package dev.joostlek.lingo.infrastructure.persistency.jpa.repositories;

import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.RoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RoundJpaRepository extends JpaRepository<RoundEntity, Long> {
    @Query(value = "SELECT nextval('round_seq')", nativeQuery = true)
    Long getNextIdentity();

    Collection<RoundEntity> findAllByGameId(Long gameId);
}
