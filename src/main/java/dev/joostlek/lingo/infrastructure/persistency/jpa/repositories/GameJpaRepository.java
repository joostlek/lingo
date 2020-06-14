package dev.joostlek.lingo.infrastructure.persistency.jpa.repositories;

import dev.joostlek.lingo.infrastructure.persistency.jpa.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameJpaRepository extends JpaRepository<GameEntity, Long> {
    @Query(value = "SELECT nextval('game_seq')", nativeQuery = true)
    Long getNextIdentity();
}
