package ua.vlad.hg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.vlad.hg.domain.Ground;

@Repository
public interface GroundRepository extends JpaRepository<Ground, Long> {

    Ground findByAlias(String alias);

}
