package ua.vlad.hg.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vlad.hg.core.entity.User;
import ua.vlad.hg.core.exception.ApplicationException;
import ua.vlad.hg.core.exception.ExceptionCode;
import ua.vlad.hg.core.repository.UserRepository;
import ua.vlad.hg.core.util.Role;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.USER_NOT_FOUND, username));
    }

    public User find(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.USER_NOT_FOUND, id));
    }

    public void create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ApplicationException(ExceptionCode.USERNAME_ALREADY_EXISTS, user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setRole(Set.of(Role.USER));
        userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
