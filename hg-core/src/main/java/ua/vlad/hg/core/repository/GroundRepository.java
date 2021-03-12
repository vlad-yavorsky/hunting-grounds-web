package ua.vlad.hg.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.vlad.hg.core.entity.Ground;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GroundRepository extends JpaRepository<Ground, Long> {

    @EntityGraph(Ground.Graph.ADDRESS)
    Optional<Ground> findFetchAddressById(Long id);

    @EntityGraph(Ground.Graph.ADDRESS_FULL)
    Optional<Ground> findFetchFullAddressById(Long id);

    @EntityGraph(Ground.Graph.ADDRESS_FULL)
    List<Ground> findAllFetchFullAddressBy();

    Optional<Ground> findByAlias(String alias);

    @EntityGraph(Ground.Graph.ADDRESS_FULL)
    List<Ground> findAllFetchFullAddressByAliasIn(Set<String> aliases);

}
