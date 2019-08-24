package ua.vlad.hg.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import ua.vlad.hg.domain.User;

public interface UserService extends UserDetailsService {

    void deleteById(Long id);

    User getById(Long id);

    User save(User project);

    Page<User> findAll(Pageable pageable);

    User findByUsername(String username);

}
