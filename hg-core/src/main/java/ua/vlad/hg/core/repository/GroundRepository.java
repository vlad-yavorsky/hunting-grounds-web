package ua.vlad.hg.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.vlad.hg.core.entity.Ground;

import java.util.Optional;

@Repository
public interface GroundRepository extends JpaRepository<Ground, Long> {

    Optional<Ground> findByAlias(String alias);

}
