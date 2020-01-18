package ua.vlad.hg.web.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.vlad.hg.core.exception.ApplicationException;
import ua.vlad.hg.core.exception.ExceptionCode;
import ua.vlad.hg.core.service.UserService;
import ua.vlad.hg.core.util.Role;

import javax.transaction.Transactional;

@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    public CustomAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        setUserDetailsService(userService);
        setPasswordEncoder(passwordEncoder);
    }

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) {
        Authentication result = super.authenticate(authentication);
        if (!result.getAuthorities().contains(Role.ADMIN)) {
            throw new ApplicationException(ExceptionCode.INSUFFICIENT_PRIVILEGES);
        }
        return new UsernamePasswordAuthenticationToken(result.getPrincipal(), result.getCredentials(), result.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
