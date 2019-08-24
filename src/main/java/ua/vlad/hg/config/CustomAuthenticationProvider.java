package ua.vlad.hg.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.vlad.hg.Error;
import ua.vlad.hg.domain.Role;
import ua.vlad.hg.domain.User;
import ua.vlad.hg.repository.UserRepository;
import ua.vlad.hg.service.UserService;

@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    private UserRepository userRepository;

    public CustomAuthenticationProvider(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.setUserDetailsService(userService);
        this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userRepository.findByUsername(authentication.getName());
        if (user == null) {
            throw new BadCredentialsException(Error.INVALID_USERNAME_OR_PASSWORD.toString());
        }
        Authentication result = super.authenticate(authentication);
        if (!(result.getAuthorities().contains(Role.Type.ADMIN) || result.getAuthorities().contains(Role.Type.MODERATOR))) {
            throw new BadCredentialsException(Error.USER_HAVE_NO_ACCESS_TO_ADMIN_SECTION.getMessage());
        }
        return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
