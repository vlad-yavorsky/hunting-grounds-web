package ua.vlad.hg.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.vlad.hg.core.entity.User;
import ua.vlad.hg.core.exception.ApplicationException;
import ua.vlad.hg.core.exception.ExceptionCode;
import ua.vlad.hg.core.repository.UserRepository;
import ua.vlad.hg.core.util.Role;

import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
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

    public void create(String email, String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new ApplicationException(ExceptionCode.USERNAME_ALREADY_EXISTS, username);
        }
        userRepository.save(User.builder()
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(password))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .created(new Date())
                .role(Set.of(Role.USER))
                .build());
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
