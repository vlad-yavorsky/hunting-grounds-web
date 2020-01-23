package ua.vlad.hg.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import ua.vlad.hg.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(User.Graph.ROLE)
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

}
